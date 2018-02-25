package com.mycompany.liveobjects;

import static com.mycompany.liveobjects.JDBCObjectStore.SLOT_TYPE_BLOCK;
import static com.mycompany.liveobjects.JDBCObjectStore.SLOT_TYPE_INTEGER;
import static com.mycompany.liveobjects.JDBCObjectStore.SLOT_TYPE_REFERENCE;
import static com.mycompany.liveobjects.JDBCObjectStore.SLOT_TYPE_STRING;
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
    
    private PreparedStatement slotInsertStatement;
    private PreparedStatement slotReferenceValueDeleteStatement;
    private PreparedStatement slotReferenceValueInsertStatement;
    private PreparedStatement slotTypeUpdateStatement;
    private PreparedStatement slotLastUpdateUpdateStatement;
    private PreparedStatement slotBlobValueDeleteStatement;
    private PreparedStatement slotBlobValueInsertStatement;
    private PreparedStatement objectSelectLastUpdateStatement;
    private PreparedStatement objectLastUpdateUpdateStatement;
    private PreparedStatement objectSelectChangedSlotsStatement;

    public JDBCObjectStoreConnection(ObjectStore objectStore, Connection connection, InstructionSet instructionSet, ObjectLoader objectLoader) {
        this.objectStore = objectStore;
        this.connection = connection;
        this.instructionSet = instructionSet;
        this.objectLoader = objectLoader;
        
        try {
            slotInsertStatement = connection.prepareStatement(
                    "INSERT INTO slot (object_holder_id, symbol, type) VALUES (?, ?, ?)");
            slotReferenceValueDeleteStatement = connection.prepareStatement(
                    "DELETE FROM slot_reference WHERE object_holder_id = ? AND symbol = ?");
            slotReferenceValueInsertStatement = connection.prepareStatement(
                    "INSERT INTO slot_reference (object_holder_id, symbol, object_reference_id, type) VALUES (?, ?, ?, ?)");
            slotBlobValueDeleteStatement = connection.prepareStatement(
                    "DELETE FROM slot_blob WHERE object_holder_id = ? AND symbol = ?");
            slotBlobValueInsertStatement = connection.prepareStatement(
                    "INSERT INTO slot_blob (object_holder_id, symbol, value) VALUES (?, ?, ?)");
            slotTypeUpdateStatement = connection.prepareStatement(
                    "UPDATE slot SET type = ? WHERE object_holder_id = ? AND symbol = ?");
            slotLastUpdateUpdateStatement = connection.prepareStatement(
                    "UPDATE slot SET last_update = ? WHERE object_holder_id = ? AND symbol = ?");
            objectSelectLastUpdateStatement = connection.prepareStatement(
                    "SELECT last_update FROM object WHERE id = ?");
            objectLastUpdateUpdateStatement = connection.prepareStatement(
                    "UPDATE object SET last_update = ? WHERE id = ?");
            objectSelectChangedSlotsStatement = connection.prepareStatement(
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
                    "       AND s.last_update > ?\n" +
                    "");
        } catch (SQLException ex) {
            Logger.getLogger(AssociativeArrayLObject.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCObjectStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
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

                connection.commit();
            } else {
                generatedId = -1;
            }
            
            return generatedId;
        } catch (SQLException ex) {
            Logger.getLogger(AssociativeArrayLObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return -1;
    }

    @Override
    public void readSlots(int id, Environment environment, Timestamp lastUpdate, Map<Integer, LObject> slots, Map<Integer, LObject> parentSlots) {
        if(lastUpdate == null) {
            lastUpdate = Timestamp.valueOf(LocalDateTime.MIN);
        }
        
        try {
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
                        throw new RuntimeException("Unsupported type");
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
                }
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(AssociativeArrayLObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Timestamp lastUpdateTime(int id) {
        try {
            objectSelectLastUpdateStatement.setInt(1, id);
            ResultSet rs = objectSelectLastUpdateStatement.executeQuery();
            if(rs.next()) {
                Timestamp actualLastUpdate = rs.getTimestamp(1);
                
                /*if(actualLastUpdate == null) {
                    actualLastUpdate = Timestamp.valueOf(LocalDateTime.MIN);
                }*/
                
                return actualLastUpdate;
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCObjectStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
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
                    slotReferenceValueDeleteStatement.setInt(1, id);
                    slotReferenceValueDeleteStatement.setString(2, selector);
                    slotReferenceValueDeleteStatement.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(AssociativeArrayLObject.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void addSlotReference(int otherId) {
                try {
                    slotInsertStatement.setInt(1, id);
                    slotInsertStatement.setString(2, selector);
                    slotInsertStatement.setInt(3, JDBCObjectStore.SLOT_TYPE_REFERENCE);
                    slotInsertStatement.execute();
                    
                    slotReferenceValueInsertStatement.setInt(1, id);
                    slotReferenceValueInsertStatement.setString(2, selector);
                    slotReferenceValueInsertStatement.setInt(3, otherId);
                    slotReferenceValueInsertStatement.setInt(4, referenceType);
                    slotReferenceValueInsertStatement.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(AssociativeArrayLObject.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void updateSlotReference(int otherId) {
                try {
                    slotReferenceValueInsertStatement.setInt(1, id);
                    slotReferenceValueInsertStatement.setString(2, selector);
                    slotReferenceValueInsertStatement.setInt(3, otherId);
                    slotReferenceValueInsertStatement.setInt(4, referenceType);
                    slotReferenceValueInsertStatement.execute();
                    
                    updateSlotType(id, selector, JDBCObjectStore.SLOT_TYPE_REFERENCE);
                } catch (SQLException ex) {
                    Logger.getLogger(AssociativeArrayLObject.class.getName()).log(Level.SEVERE, null, ex);
                }
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
                instructions.forEach(i -> {
                    try {
                        instructionSet.writeInstruction(i, outputStream);
                    } catch (IOException ex) {
                        Logger.getLogger(AssociativeArrayLObject.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });

                return outputStream.toByteArray();
            }

            @Override
            public void addBlockSlot(int arity, int varCount, List<Instruction> instructions) {
                try {
                    byte[] value = createBlockValue(arity, varCount, instructions);
                    addBlobSlot(JDBCObjectStore.SLOT_TYPE_BLOCK, value);
                } catch (IOException ex) {
                    Logger.getLogger(AssociativeArrayLObject.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void updateBlockSlot(int arity, int varCount, List<Instruction> instructions) {
                try {
                    byte[] value = createBlockValue(arity, varCount, instructions);
                    updateBlobSlot(JDBCObjectStore.SLOT_TYPE_BLOCK, value);
                } catch (IOException ex) {
                    Logger.getLogger(AssociativeArrayLObject.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void deleteSlotBlobValue() {
                try {
                    slotBlobValueDeleteStatement.setInt(1, id);
                    slotBlobValueDeleteStatement.setString(2, selector);
                    slotBlobValueDeleteStatement.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(AssociativeArrayLObject.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void addBlobSlot(int type, byte[] bytes) {
                try {
                    byte[] value = bytes;
                    
                    slotInsertStatement.setInt(1, id);
                    slotInsertStatement.setString(2, selector);
                    slotInsertStatement.setInt(3, type);
                    slotInsertStatement.execute();
                    
                    slotBlobValueInsertStatement.setInt(1, id);
                    slotBlobValueInsertStatement.setString(2, selector);
                    slotBlobValueInsertStatement.setBytes(3, value);
                    slotBlobValueInsertStatement.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(AssociativeArrayLObject.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void updateBlobSlot(int type, byte[] bytes) {
                try {
                    byte[] value = bytes;
                    
                    slotBlobValueInsertStatement.setInt(1, id);
                    slotBlobValueInsertStatement.setString(2, selector);
                    slotBlobValueInsertStatement.setBytes(3, value);
                    slotBlobValueInsertStatement.execute();
                    
                    updateSlotType(id, selector, type);
                } catch (SQLException ex) {
                    Logger.getLogger(AssociativeArrayLObject.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            private void updateSlotType(int id, String selector, int type) throws SQLException {
                slotTypeUpdateStatement.setInt(1, type);
                slotTypeUpdateStatement.setInt(2, id);
                slotTypeUpdateStatement.setString(3, selector);
                slotTypeUpdateStatement.execute();
            }

            @Override
            public void close() throws Exception {
                try {
                    Timestamp lastUpdate = Timestamp.valueOf(LocalDateTime.now());
                    
                    slotLastUpdateUpdateStatement.setTimestamp(1, lastUpdate);
                    slotLastUpdateUpdateStatement.setInt(2, id);
                    slotLastUpdateUpdateStatement.setString(3, selector);
                    slotLastUpdateUpdateStatement.execute();
                    
                    objectLastUpdateUpdateStatement.setTimestamp(1, lastUpdate);
                    objectLastUpdateUpdateStatement.setInt(2, id);
                    objectLastUpdateUpdateStatement.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCObjectStore.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
    }
}
