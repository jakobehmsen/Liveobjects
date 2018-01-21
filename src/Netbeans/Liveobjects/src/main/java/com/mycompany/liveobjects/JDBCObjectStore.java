package com.mycompany.liveobjects;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCObjectStore implements ObjectStore {
    public static final int SLOT_TYPE_REFERENCE = 0;
    public static final int SLOT_TYPE_INTEGER = 1;
    public static final int SLOT_TYPE_STRING = 2;
    public static final int SLOT_TYPE_BLOCK = 3;
    
    private Connection connection;
    private PreparedStatement slotInsertStatement;
    private PreparedStatement slotReferenceValueDeleteStatement;
    private PreparedStatement slotReferenceValueInsertStatement;
    private PreparedStatement slotTypeUpdateStatement;
    private PreparedStatement slotInsertObjectStatement;
    private PreparedStatement slotBlobValueDeleteStatement;
    private PreparedStatement slotBlobValueInsertStatement;
    private PreparedStatement slotSelectObjectTypeStatement;
    private InstructionSet instructionSet;
    private ObjectLoader objectLoader;
    
    public JDBCObjectStore(Connection connection, InstructionSet instructionSet, ObjectLoader objectLoader) {
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
            slotInsertObjectStatement = connection.prepareStatement(
                    "INSERT INTO object (type) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            slotSelectObjectTypeStatement = connection.prepareStatement(
                    "SELECT type FROM object WHERE id = ?");
        } catch (SQLException ex) {
            Logger.getLogger(AssociativeArrayObject.class.getName()).log(Level.SEVERE, null, ex);
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
            public void deleteSlotReferenceValue(int id) {
                try {
                    slotReferenceValueDeleteStatement.setInt(1, id);
                    slotReferenceValueDeleteStatement.setString(2, selector);
                    slotReferenceValueDeleteStatement.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(AssociativeArrayObject.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(AssociativeArrayObject.class.getName()).log(Level.SEVERE, null, ex);
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
                    
                    slotTypeUpdateStatement.setInt(1, id);
                    slotTypeUpdateStatement.setString(2, selector);
                    slotTypeUpdateStatement.setInt(3, JDBCObjectStore.SLOT_TYPE_REFERENCE);
                    slotTypeUpdateStatement.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(AssociativeArrayObject.class.getName()).log(Level.SEVERE, null, ex);
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
                        Logger.getLogger(AssociativeArrayObject.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(AssociativeArrayObject.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void updateBlockSlot(int arity, int varCount, List<Instruction> instructions) {
                try {
                    byte[] value = createBlockValue(arity, varCount, instructions);
                    updateBlobSlot(JDBCObjectStore.SLOT_TYPE_BLOCK, value);
                } catch (IOException ex) {
                    Logger.getLogger(AssociativeArrayObject.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void deleteSlotBlobValue() {
                try {
                    slotBlobValueDeleteStatement.setInt(1, id);
                    slotBlobValueDeleteStatement.setString(2, selector);
                    slotBlobValueDeleteStatement.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(AssociativeArrayObject.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(AssociativeArrayObject.class.getName()).log(Level.SEVERE, null, ex);
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
                    
                    slotTypeUpdateStatement.setInt(1, id);
                    slotTypeUpdateStatement.setString(2, selector);
                    slotTypeUpdateStatement.setInt(3, type);
                    slotTypeUpdateStatement.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(AssociativeArrayObject.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
    }

    @Override
    public void readSlots(Environment environment, int id, Map<Integer, LObject> slots, Map<Integer, LObject> parentSlots) {
        String query = 
                "SELECT s.symbol, s.type, r.object_reference_id, r.type, null FROM slot s\n" +
                "   INNER JOIN slot_reference r\n" +
                "       ON r.object_holder_id = s.object_holder_id\n" +
                "       AND r.symbol = s.symbol\n" +
                "   WHERE s.object_holder_id = " + id + "\n" +
                "UNION ALL\n" +
                "SELECT s.symbol, s.type, null, null, b.value FROM slot s\n" +
                "   INNER JOIN slot_blob b\n" +
                "       ON b.object_holder_id = s.object_holder_id\n" +
                "       AND b.symbol = s.symbol\n" +
                "   WHERE s.object_holder_id = " + id + "\n" +
                "";
        
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            
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
            Logger.getLogger(AssociativeArrayObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }

    @Override
    public int nowUsedFrom(int id, Environment environment, Map<Integer, LObject> slots, Map<Integer, LObject> parentSlots, int type) {
        try {
            int generatedId;
            slotInsertObjectStatement.setInt(1, type);
            slotInsertObjectStatement.execute();
            ResultSet generatedKeys = slotInsertObjectStatement.getGeneratedKeys();
            if(generatedKeys.next()) {
                generatedId = generatedKeys.getInt(1);
            } else {
                generatedId = -1;
            }

            // Persist all slots
            slots.forEach((symbolCode, value) -> {
                value.nowUsedFrom(id, environment);
                
                String selector = environment.getSymbolString(symbolCode);
                ObjectSlotTransaction objectSlotTransaction = 
                        createObjectSlotTransaction(generatedId, selector, ObjectStore.REFERENCE_TYPE_NORMAL);
                value.addSlot(objectSlotTransaction);
            });
            parentSlots.forEach((symbolCode, value) -> {
                value.nowUsedFrom(id, environment);
                
                String selector = environment.getSymbolString(symbolCode);
                ObjectSlotTransaction objectSlotTransaction = 
                        createObjectSlotTransaction(generatedId, selector, ObjectStore.REFERENCE_TYPE_PARENT);
                value.addSlot(objectSlotTransaction);
            });
            
            return generatedId;
        } catch (SQLException ex) {
            Logger.getLogger(AssociativeArrayObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return -1;
    }

    @Override
    public LObject load(int id) {
        try {
            slotSelectObjectTypeStatement.setInt(1, id);
            ResultSet rs = slotSelectObjectTypeStatement.executeQuery();
            if(rs.next()) {
                int type = rs.getInt(1);
                
                switch(type) {
                    case ObjectStore.OBJECT_TYPE_ASSOCIATIVE_ARRAY:
                        return new AssociativeArrayObject(this, id);
                    case ObjectStore.OBJECT_TYPE_ARRAY:
                        return new ArrayLObject(this, id);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCObjectStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @Override
    public ArrayLObject newArray(int value) {
        return new ArrayLObject(this, 0, new LObject[value]);
    }
}
