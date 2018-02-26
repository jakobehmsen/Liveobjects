package com.mycompany.liveobjects.runtime;

import static com.mycompany.liveobjects.runtime.JDBCObjectStore.SLOT_TYPE_BLOCK;
import static com.mycompany.liveobjects.runtime.JDBCObjectStore.SLOT_TYPE_INTEGER;
import static com.mycompany.liveobjects.runtime.JDBCObjectStore.SLOT_TYPE_REFERENCE;
import static com.mycompany.liveobjects.runtime.JDBCObjectStore.SLOT_TYPE_STRING;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class JDBCObjectStoreConnection implements ObjectStoreConnection {
    private ObjectStore objectStore;
    private Connection connection;
    private InstructionSet instructionSet;
    private ObjectLoader objectLoader;

    public JDBCObjectStoreConnection(ObjectStore objectStore, Connection connection, InstructionSet instructionSet, ObjectLoader objectLoader) {
        this.objectStore = objectStore;
        this.connection = connection;
        this.instructionSet = instructionSet;
        this.objectLoader = objectLoader;
    }

    @Override
    public LObject load(int id) {
        try {
            PreparedStatement objectSelectTypeAndLastUpdateStatement = connection.prepareStatement(
                    "SELECT type, last_update FROM object WHERE id = ?");
            objectSelectTypeAndLastUpdateStatement.setInt(1, id);
            ResultSet rs = objectSelectTypeAndLastUpdateStatement.executeQuery();
            if(rs.next()) {
                int type = rs.getInt(1);
                Timestamp lastUpdate = rs.getTimestamp(2);
                
                switch(type) {
                    case ObjectStore.OBJECT_TYPE_ASSOCIATIVE_ARRAY:
                        return new AssociativeArrayLObject(objectStore, id, lastUpdate);
                    case ObjectStore.OBJECT_TYPE_ARRAY:
                        return new ArrayLObject(objectStore, id, lastUpdate);
                    case ObjectStore.OBJECT_TYPE_CONTEXT:
                        return new DefaultFrame(id, lastUpdate, objectStore);
                    case ObjectStore.OBJECT_TYPE_CLOSURE:
                        return new Closure(id, lastUpdate, objectStore);
                    default:
                        throw new IllegalArgumentException("Object with id has unsupported type " + type + ".");
                }
            } else {
                throw new IllegalArgumentException("Could not find object with id " + id + ".");
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Could not load object with id " + id + ".", ex);
        }
    }

    @Override
    public int nowUsedFrom(int id, Environment environment, Map<Integer, LObject> slots, Map<Integer, LObject> parentSlots, int type) {
        try {
            PreparedStatement objectInsertObjectStatement = connection.prepareStatement(
                    "INSERT INTO object (type, last_update) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            int generatedId;
            objectInsertObjectStatement.setInt(1, type);
            objectInsertObjectStatement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            objectInsertObjectStatement.execute();
            ResultSet generatedKeys = objectInsertObjectStatement.getGeneratedKeys();
            if(generatedKeys.next()) {
                generatedId = generatedKeys.getInt(1);
                
                // Persist all slots
                slots.forEach((symbolCode, value) -> {
                    value.nowUsedFrom(generatedId, environment);

                    String selector = environment.getSymbolString(symbolCode);
                    ObjectSlotTransaction objectSlotTransaction = 
                            createObjectSlotTransaction(generatedId, selector, ObjectStore.REFERENCE_TYPE_NORMAL);
                    value.addSlot(objectSlotTransaction);
                });
                parentSlots.forEach((symbolCode, value) -> {
                    value.nowUsedFrom(generatedId, environment);

                    String selector = environment.getSymbolString(symbolCode);
                    ObjectSlotTransaction objectSlotTransaction = 
                            createObjectSlotTransaction(generatedId, selector, ObjectStore.REFERENCE_TYPE_PARENT);
                    value.addSlot(objectSlotTransaction);
                });
            } else {
                throw new RuntimeException("Could not retrieve generated id for initial creation of object.");
            }
            
            return generatedId;
        } catch (SQLException ex) {
            throw new RuntimeException("Could not store initial state of object.", ex);
        }
    }

    @Override
    public void readSlots(int id, Environment environment, Timestamp lastUpdate, Map<Integer, LObject> slots, Map<Integer, LObject> parentSlots) {
        if(lastUpdate == null) {
            lastUpdate = Timestamp.valueOf(LocalDateTime.MIN);
        }
        
        try {
            PreparedStatement objectSelectChangedSlotsStatement = connection.prepareStatement(
                    "SELECT s.symbol, s.type, r.object_reference_id, r.type, null FROM slot s\n" +
                    "   INNER JOIN slot_reference r\n" +
                    "       ON r.object_holder_id = s.object_holder_id\n" +
                    "       AND r.symbol = s.symbol\n" +
                    "   WHERE\n" +
                    "       s.object_holder_id = ?\n" +
                    "       AND s.last_update > ?\n" +
                    "UNION ALL\n" +
                    "SELECT s.symbol, s.type, null, null, b.value FROM slot s\n" +
                    "   INNER JOIN slot_blob b\n" +
                    "       ON b.object_holder_id = s.object_holder_id\n" +
                    "       AND b.symbol = s.symbol\n" +
                    "   WHERE\n" +
                    "       s.object_holder_id = ?\n" +
                    "       AND s.last_update > ?\n");
            
            objectSelectChangedSlotsStatement.setInt(1, id);
            objectSelectChangedSlotsStatement.setTimestamp(2, lastUpdate);
            objectSelectChangedSlotsStatement.setInt(3, id);
            objectSelectChangedSlotsStatement.setTimestamp(4, lastUpdate);
            
            ResultSet resultSet = objectSelectChangedSlotsStatement.executeQuery();
            
            while(resultSet.next()) {
                String selector = resultSet.getString(1);
                int slotType = resultSet.getInt(2);
                LObject object = null;
                byte[] bytes = resultSet.getBytes(5);
                int referenceType = 0;
                
                switch(slotType) {
                    case SLOT_TYPE_REFERENCE:
                        int objectReferenceId = resultSet.getInt(3);
                        referenceType = resultSet.getInt(4);
                        object = objectLoader.load(objectReferenceId);
                        break;
                    case SLOT_TYPE_INTEGER: {
                        ByteBuffer wrapped = ByteBuffer.wrap(bytes);
                        int value = wrapped.getInt();
                        object = new IntegerLObject(value);
                        break;
                    } case SLOT_TYPE_STRING: {
                        String value = new String(bytes);
                        object = new StringLObject(value);
                        break;
                    } case SLOT_TYPE_BLOCK: {
                        byte[] value = bytes;
                        ByteArrayInputStream inputStream = new ByteArrayInputStream(value);
                        DataInputStream dataInputStream = new DataInputStream(inputStream);
                        int arity = dataInputStream.readInt();
                        int varCount = dataInputStream.readInt();
                        ArrayList<Instruction> instructions = new ArrayList<>();
                        while(inputStream.available() > 0) {
                            Instruction instruction = instructionSet.readInstruction(inputStream);
                            instructions.add(instruction);
                        }
                        object = new Block(arity, varCount, instructions);
                        break;
                    } default: {
                        throw new IllegalArgumentException("Slot '" + selector + "' of object with id " + id + " has unsupported slot type " + slotType + ".");
                    }
                }
                
                int symbolCode = environment.getSymbolCode(selector);
                
                switch(referenceType) {
                    case ObjectStore.REFERENCE_TYPE_NORMAL:
                        slots.put(symbolCode, object);
                        break;
                    case ObjectStore.REFERENCE_TYPE_PARENT:
                        parentSlots.put(symbolCode, object);
                        break; 
                    default: {
                        throw new IllegalArgumentException("Slot '" + selector + "' of object with id " + id + " has unsupported reference type " + referenceType + ".");
                    }
                }
            }
        } catch (SQLException | IOException ex) {
            throw new RuntimeException("Could not read slots from object with id " + id + ".", ex);
        }
    }

    @Override
    public Timestamp lastUpdateTime(int id) {
        try {
            PreparedStatement objectSelectLastUpdateStatement = connection.prepareStatement(
                    "SELECT last_update FROM object WHERE id = ?");
            objectSelectLastUpdateStatement.setInt(1, id);
            ResultSet rs = objectSelectLastUpdateStatement.executeQuery();
            if(rs.next()) {
                Timestamp actualLastUpdate = rs.getTimestamp(1);
                
                return actualLastUpdate;
            } else {
                throw new IllegalArgumentException("Could not find object with id " + id + ".");
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Could not read last update time for object with id " + id + ".", ex);
        }
    }
    
    @Override
    public ObjectSlotTransaction createObjectSlotTransaction(int id, String selector, int referenceType) {
        return new ObjectSlotTransaction() {
            @Override
            public void deleteSlotIntegerValue() {
                deleteSlotBlobValue();
            }

            @Override
            public void deleteSlotReferenceValue(int otherId) {
                try {
                    PreparedStatement slotReferenceValueDeleteStatement = connection.prepareStatement(
                            "DELETE FROM slot_reference WHERE object_holder_id = ? AND symbol = ?");
                    slotReferenceValueDeleteStatement.setInt(1, id);
                    slotReferenceValueDeleteStatement.setString(2, selector);
                    slotReferenceValueDeleteStatement.executeUpdate();
                } catch (SQLException ex) {
                    throw new RuntimeException("Could not delete slot reference '" + selector + "' from object with id " + id + " to object with id " + otherId + ".", ex);
                }
            }

            @Override
            public void addSlotReference(int otherId) {
                try {
                    insertSlot(JDBCObjectStore.SLOT_TYPE_REFERENCE);
                    insertSlotReference(otherId);
                } catch (SQLException ex) {
                    throw new RuntimeException("Could not add slot reference '" + selector + "' from object with id " + id + " to object with id " + otherId + ".", ex);
                }
            }

            @Override
            public void updateSlotReference(int otherId) {
                try {
                    insertSlotReference(otherId);
                    updateSlotType(id, selector, JDBCObjectStore.SLOT_TYPE_REFERENCE);
                } catch (SQLException ex) {
                    throw new RuntimeException("Could not update slot reference '" + selector + "' from object with id " + id + " to object with id " + otherId + ".", ex);
                }
            }
            
            private void insertSlotReference(int otherId) throws SQLException {
                PreparedStatement slotReferenceValueInsertStatement = connection.prepareStatement(
                        "INSERT INTO slot_reference (object_holder_id, symbol, object_reference_id, type) VALUES (?, ?, ?, ?)");
                slotReferenceValueInsertStatement.setInt(1, id);
                slotReferenceValueInsertStatement.setString(2, selector);
                slotReferenceValueInsertStatement.setInt(3, otherId);
                slotReferenceValueInsertStatement.setInt(4, referenceType);
                slotReferenceValueInsertStatement.executeUpdate();
            }

            @Override
            public void addIntegerSlot(int value) {
                ByteBuffer buffer = ByteBuffer.allocate(4);
                buffer.putInt(value);
                addBlobSlot(JDBCObjectStore.SLOT_TYPE_INTEGER, buffer.array());
            }

            @Override
            public void updateIntegerSlot(int value) {
                ByteBuffer buffer = ByteBuffer.allocate(4);
                buffer.putInt(value);
                updateBlobSlot(JDBCObjectStore.SLOT_TYPE_INTEGER, buffer.array());
            }

            @Override
            public void addStringSlot(String value) {
                addBlobSlot(JDBCObjectStore.SLOT_TYPE_STRING, value.getBytes());
            }

            @Override
            public void deleteStringSlot(String value) {
                deleteSlotBlobValue();
            }

            @Override
            public void updateStringSlot(String value) {
                updateBlobSlot(JDBCObjectStore.SLOT_TYPE_STRING, value.getBytes());
            }

            @Override
            public void deleteSlotBlockValue() {
                deleteSlotBlobValue();
            }
            
            private byte[] createBlockValue(int arity, int varCount, List<Instruction> instructions) throws IOException {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                    
                dataOutputStream.writeInt(arity);
                dataOutputStream.writeInt(varCount);
                
                for(int i = 0; i < instructions.size(); i++) {
                    Instruction instr = instructions.get(i);
                    instructionSet.writeInstruction(instr, outputStream);
                }

                return outputStream.toByteArray();
            }

            @Override
            public void addBlockSlot(int arity, int varCount, List<Instruction> instructions) {
                try {
                    byte[] value = createBlockValue(arity, varCount, instructions);
                    addBlobSlot(JDBCObjectStore.SLOT_TYPE_BLOCK, value);
                } catch (IOException ex) {
                    throw new RuntimeException("Could not add block slot '" + selector + "' for object with id.", ex);
                }
            }

            @Override
            public void updateBlockSlot(int arity, int varCount, List<Instruction> instructions) {
                try {
                    byte[] value = createBlockValue(arity, varCount, instructions);
                    updateBlobSlot(JDBCObjectStore.SLOT_TYPE_BLOCK, value);
                } catch (IOException ex) {
                    throw new RuntimeException("Could not update block slot '" + selector + "' for object with id.", ex);
                }
            }

            @Override
            public void deleteSlotBlobValue() {
                try {
                    PreparedStatement slotBlobValueDeleteStatement = connection.prepareStatement(
                            "DELETE FROM slot_blob WHERE object_holder_id = ? AND symbol = ?");
                    slotBlobValueDeleteStatement.setInt(1, id);
                    slotBlobValueDeleteStatement.setString(2, selector);
                    slotBlobValueDeleteStatement.executeUpdate();
                } catch (SQLException ex) {
                    throw new RuntimeException("Could not delete slot '" + selector + "' blob value for object with id.", ex);
                }
            }

            @Override
            public void addBlobSlot(int type, byte[] bytes) {
                try {
                    insertSlot(type);
                    insertSlotBlobValue(bytes);
                } catch (SQLException ex) {
                    throw new RuntimeException("Could not add slot '" + selector + "' blob for object with id.", ex);
                }
            }

            @Override
            public void updateBlobSlot(int type, byte[] bytes) {
                try {
                    insertSlotBlobValue(bytes);
                    updateSlotType(id, selector, type);
                } catch (SQLException ex) {
                    throw new RuntimeException("Could not update slot '" + selector + "' blob for object with id.", ex);
                }
            }
            
            private void insertSlotBlobValue(byte[] bytes) throws SQLException {
                byte[] value = bytes;
                    
                PreparedStatement slotBlobValueInsertStatement = connection.prepareStatement(
                        "INSERT INTO slot_blob (object_holder_id, symbol, value) VALUES (?, ?, ?)");
                slotBlobValueInsertStatement.setInt(1, id);
                slotBlobValueInsertStatement.setString(2, selector);
                slotBlobValueInsertStatement.setBytes(3, value);
                slotBlobValueInsertStatement.executeUpdate();
            }
            
            private void updateSlotType(int id, String selector, int type) throws SQLException {
                PreparedStatement slotTypeUpdateStatement = connection.prepareStatement(
                        "UPDATE slot SET type = ? WHERE object_holder_id = ? AND symbol = ?");
                slotTypeUpdateStatement.setInt(1, type);
                slotTypeUpdateStatement.setInt(2, id);
                slotTypeUpdateStatement.setString(3, selector);
                slotTypeUpdateStatement.executeUpdate();
            }

            @Override
            public void close() throws Exception {
                Timestamp lastUpdate = Timestamp.valueOf(LocalDateTime.now());
                    
                PreparedStatement slotLastUpdateUpdateStatement = connection.prepareStatement(
                        "UPDATE slot SET last_update = ? WHERE object_holder_id = ? AND symbol = ?");
                slotLastUpdateUpdateStatement.setTimestamp(1, lastUpdate);
                slotLastUpdateUpdateStatement.setInt(2, id);
                slotLastUpdateUpdateStatement.setString(3, selector);
                slotLastUpdateUpdateStatement.executeUpdate();

                PreparedStatement objectLastUpdateUpdateStatement = connection.prepareStatement(
                        "UPDATE object SET last_update = ? WHERE id = ?");
                objectLastUpdateUpdateStatement.setTimestamp(1, lastUpdate);
                objectLastUpdateUpdateStatement.setInt(2, id);
                objectLastUpdateUpdateStatement.executeUpdate();
            }
            
            private void insertSlot(int referenceType) throws SQLException {
                PreparedStatement slotInsertStatement = connection.prepareStatement(
                        "INSERT INTO slot (object_holder_id, symbol, type) VALUES (?, ?, ?)");
                
                slotInsertStatement.setInt(1, id);
                slotInsertStatement.setString(2, selector);
                slotInsertStatement.setInt(3, referenceType);
                slotInsertStatement.executeUpdate();
            }
        };
    }
}
