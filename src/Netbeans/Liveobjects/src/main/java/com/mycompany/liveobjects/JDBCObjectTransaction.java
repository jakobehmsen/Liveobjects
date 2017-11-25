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
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCObjectTransaction implements ObjectTransaction {
    public static final int SLOT_TYPE_REFERENCE = 0;
    public static final int SLOT_TYPE_INTEGER = 1;
    public static final int SLOT_TYPE_STRING = 2;
    public static final int SLOT_TYPE_BLOCK = 3;
    
    private Connection connection;
    private int id;
    private int parentReferer;
    private int usage;
    private static final int USAGE_UNRESOLVED = 0;
    private static final int USAGE_SINGLE = 1;
    private static final int USAGE_MULTI = 2;
    private Map<Integer, LObject> slots;
    
    private PreparedStatement slotInsertStatement;
    private PreparedStatement slotReferenceValueDeleteStatement;
    private PreparedStatement slotReferenceValueInsertStatement;
    private PreparedStatement slotTypeUpdateStatement;
    private PreparedStatement slotInsertObjectStatement;
    private PreparedStatement slotUpdateObjectUsageStatement;
    private PreparedStatement slotBlobValueDeleteStatement;
    private PreparedStatement slotBlobValueInsertStatement;
    
    private InstructionSet instructionSet;

    public JDBCObjectTransaction(Connection connection, InstructionSet instructionSet, int id) {
        this.connection = connection;
        this.instructionSet = instructionSet;
        this.id = id;
        
        try {
            slotInsertStatement = connection.prepareStatement(
                    "INSERT INTO slot (object_holder_id, symbol, type) VALUES (?, ?, ?)");
            slotReferenceValueDeleteStatement = connection.prepareStatement(
                    "DELETE FROM slot_reference WHERE object_holder_id = ? AND symbol = ?");
            slotReferenceValueInsertStatement = connection.prepareStatement(
                    "INSERT INTO slot_reference (object_holder_id, symbol, object_reference_id) VALUES (?, ?, ?)");
            slotBlobValueDeleteStatement = connection.prepareStatement(
                    "DELETE FROM slot_blob WHERE object_holder_id = ? AND symbol = ?");
            slotBlobValueInsertStatement = connection.prepareStatement(
                    "INSERT INTO slot_blob (object_holder_id, symbol, value) VALUES (?, ?, ?)");
            slotTypeUpdateStatement = connection.prepareStatement(
                    "UPDATE slot SET type = ? WHERE object_holder_id = ? AND symbol = ?");
            slotInsertObjectStatement = connection.prepareStatement(
                    "INSERT INTO object (parent_referer, usage) VALUES (?, 1)", Statement.RETURN_GENERATED_KEYS);
            slotUpdateObjectUsageStatement = connection.prepareStatement(
                    "UPDATE object SET usage = ? WHERE id = ?");
        } catch (SQLException ex) {
            Logger.getLogger(JDBCObjectTransaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Map<Integer, LObject> readSlots(Environment environment) {
        String query = 
                "SELECT s.symbol, s.type, r.object_reference_id, null FROM slot s\n" +
                "   INNER JOIN slot_reference r\n" +
                "       ON r.object_holder_id = s.object_holder_id\n" +
                "       AND r.symbol = s.symbol\n" +
                "   WHERE s.object_holder_id = " + id + "\n" +
                "UNION ALL\n" +
                "SELECT s.symbol, s.type, null, b.value FROM slot s\n" +
                "   INNER JOIN slot_blob b\n" +
                "       ON b.object_holder_id = s.object_holder_id\n" +
                "       AND b.symbol = s.symbol\n" +
                "   WHERE s.object_holder_id = " + id + "\n" +
                "";
        
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            Map<Integer, LObject> slots = new Hashtable<>();
            
            while(resultSet.next()) {
                String selector = resultSet.getString(1);
                int type = resultSet.getInt(2);
                LObject object = null;
                byte[] bytes = resultSet.getBytes(4);
                
                switch(type) {
                    case SLOT_TYPE_REFERENCE:
                        int objectReferenceId = resultSet.getInt(3);
                        object = new DBLObject(new JDBCObjectTransaction(connection, instructionSet, objectReferenceId));
                        break;
                    case SLOT_TYPE_INTEGER: {
                        ByteBuffer wrapped = ByteBuffer.wrap(bytes);
                        int value = wrapped.getInt();
                        object = new IntegerLObject(value);
                        break;
                    } case SLOT_TYPE_STRING: {
                        String value = new String(bytes); //resultSet.getString(5);
                        object = new StringLObject(value);
                        break;
                    } case SLOT_TYPE_BLOCK: {
                        byte[] value = bytes;//resultSet.getBytes(6);
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
                slots.put(symbolCode, object);
            }
            
            return slots;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCObjectTransaction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JDBCObjectTransaction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    @Override
    public LObject getSlot(Environment environment, LObject[] arguments) {
        StringLObject selector = (StringLObject)arguments[0];
        int symbolCode = environment.getSymbolCode(selector.getValue());
        
        if(slots == null) {
            slots = readSlots(environment);
        }
        
        return slots.get(symbolCode);
    }

    @Override
    public LObject setSlot(Environment environment, LObject[] arguments) {
        final StringLObject selector = (StringLObject)arguments[1];
        int symbolCode = environment.getSymbolCode(selector.getValue());
        
        if(slots == null) {
            slots = readSlots(environment);
        }
        
        LObject newValue = arguments[0];
        LObject currentValue = slots.get(symbolCode);
        
        if(id != 0) {
            newValue.nowUsedFrom(id);
        }
        
        ObjectSlotTransaction slotTransaction = new ObjectSlotTransaction() {
            @Override
            public void deleteSlotIntegerValue() {
                deleteSlotBlobValue();
            }

            @Override
            public void setSlotIntegerValue(int value) {
                ByteBuffer buffer = ByteBuffer.allocate(4);
                buffer.putInt(value);
                setSlotBlobValue(SLOT_TYPE_INTEGER, buffer.array());
            }

            @Override
            public void deleteSlotReferenceValue(int id) {
                try {
                    slotReferenceValueDeleteStatement.setInt(1, id);
                    slotReferenceValueDeleteStatement.setString(2, selector.getValue());
                    slotReferenceValueDeleteStatement.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCObjectTransaction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void setSlotReferenceValue(int id) {
                try {
                    slotReferenceValueInsertStatement.setInt(1, id);
                    slotReferenceValueInsertStatement.setString(2, selector.getValue());
                    slotReferenceValueInsertStatement.setInt(3, id);
                    slotReferenceValueInsertStatement.execute();
                    
                    slotTypeUpdateStatement.setInt(1, id);
                    slotTypeUpdateStatement.setString(2, selector.getValue());
                    slotTypeUpdateStatement.setInt(3, SLOT_TYPE_INTEGER);
                    slotTypeUpdateStatement.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCObjectTransaction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void addIntegerSlot(int value) {
                ByteBuffer buffer = ByteBuffer.allocate(4);
                buffer.putInt(value);
                addBlobSlot(SLOT_TYPE_INTEGER, buffer.array());
            }

            @Override
            public void updateIntegerSlot(int value) {
                ByteBuffer buffer = ByteBuffer.allocate(4);
                buffer.putInt(value);
                updateBlobSlot(SLOT_TYPE_INTEGER, buffer.array());
            }

            @Override
            public void addStringSlot(String value) {
                addBlobSlot(SLOT_TYPE_STRING, value.getBytes());
            }

            @Override
            public void deleteStringSlot(String value) {
                deleteSlotBlobValue();
            }

            @Override
            public void updateStringSlot(String value) {
                updateBlobSlot(SLOT_TYPE_STRING, value.getBytes());
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
                        Logger.getLogger(JDBCObjectTransaction.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });

                return outputStream.toByteArray();
            }

            @Override
            public void setSlotBlockValue(int arity, int varCount, List<Instruction> instructions) {
                try {
                    byte[] value = createBlockValue(arity, varCount, instructions);
                    setSlotBlobValue(SLOT_TYPE_BLOCK, value);
                } catch (IOException ex) {
                    Logger.getLogger(JDBCObjectTransaction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void addBlockSlot(int arity, int varCount, List<Instruction> instructions) {
                try {
                    byte[] value = createBlockValue(arity, varCount, instructions);
                    addBlobSlot(SLOT_TYPE_BLOCK, value);
                } catch (IOException ex) {
                    Logger.getLogger(JDBCObjectTransaction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void updateBlockSlot(int arity, int varCount, List<Instruction> instructions) {
                try {
                    byte[] value = createBlockValue(arity, varCount, instructions);
                    updateBlobSlot(SLOT_TYPE_BLOCK, value);
                } catch (IOException ex) {
                    Logger.getLogger(JDBCObjectTransaction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void deleteSlotBlobValue() {
                try {
                    slotBlobValueDeleteStatement.setInt(1, id);
                    slotBlobValueDeleteStatement.setString(2, selector.getValue());
                    slotBlobValueDeleteStatement.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCObjectTransaction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void setSlotBlobValue(int type, byte[] bytes) {
                try {
                    byte[] value = bytes;
                    
                    slotBlobValueInsertStatement.setInt(1, id);
                    slotBlobValueInsertStatement.setString(2, selector.getValue());
                    slotBlobValueInsertStatement.setBytes(3, value);
                    slotBlobValueInsertStatement.execute();
                    
                    slotTypeUpdateStatement.setInt(1, id);
                    slotTypeUpdateStatement.setString(2, selector.getValue());
                    slotTypeUpdateStatement.setInt(3, type);
                    slotTypeUpdateStatement.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCObjectTransaction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void addBlobSlot(int type, byte[] bytes) {
                try {
                    byte[] value = bytes;
                    
                    slotInsertStatement.setInt(1, id);
                    slotInsertStatement.setString(2, selector.getValue());
                    slotInsertStatement.setInt(3, type);
                    slotInsertStatement.execute();
                    
                    slotBlobValueInsertStatement.setInt(1, id);
                    slotBlobValueInsertStatement.setString(2, selector.getValue());
                    slotBlobValueInsertStatement.setBytes(3, value);
                    slotBlobValueInsertStatement.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCObjectTransaction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void updateBlobSlot(int type, byte[] bytes) {
                try {
                    byte[] value = bytes;
                    
                    slotBlobValueInsertStatement.setInt(1, id);
                    slotBlobValueInsertStatement.setString(2, selector.getValue());
                    slotBlobValueInsertStatement.setBytes(3, value);
                    slotBlobValueInsertStatement.execute();
                    
                    slotTypeUpdateStatement.setInt(1, id);
                    slotTypeUpdateStatement.setString(2, selector.getValue());
                    slotTypeUpdateStatement.setInt(3, type);
                    slotTypeUpdateStatement.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCObjectTransaction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        
        if(currentValue != null) {
            currentValue.deleteSlotValue(slotTransaction);
        }
        
        slots.put(symbolCode, newValue);
        
        if(currentValue == null) {
            newValue.addSlot(slotTransaction);
        } else {
            newValue.updateSlot(slotTransaction);
        }
        
        return newValue;
    }

    @Override
    public void deleteSlotValue(ObjectSlotTransaction slotTransaction) {
        slotTransaction.deleteSlotReferenceValue(id);
    }

    @Override
    public void setSlotValue(ObjectSlotTransaction slotTransaction) {
        slotTransaction.setSlotReferenceValue(id);
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCObjectTransaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ObjectTransaction cloneObject() {
        return new JDBCObjectTransaction(connection, instructionSet, 0);
    }

    @Override
    public void nowUsedFrom(int id) {
        if(this.id == 0) {
            this.parentReferer = id;
            
            try {
                // Usage is single initially
                usage = USAGE_SINGLE;
                slotInsertObjectStatement.setInt(usage, parentReferer);
                slotInsertObjectStatement.execute();
                ResultSet generatedKeys = slotInsertObjectStatement.getGeneratedKeys();
                if(generatedKeys.next()) {
                    this.id = generatedKeys.getInt(1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(JDBCObjectTransaction.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if(parentReferer != id) {
            try {
                // Set usage to multi
                usage = USAGE_MULTI;
                slotUpdateObjectUsageStatement.setInt(1, usage);
                slotUpdateObjectUsageStatement.execute();
            } catch (SQLException ex) {
                Logger.getLogger(JDBCObjectTransaction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void nowUnusedFrom(int id) {
        switch(usage) {
            case USAGE_SINGLE: {
                if(parentReferer == id) {
                    // Delete self and declare referenced objects as unused from here
                }
                
                break;
            } 
            case USAGE_MULTI: {
                if(parentReferer == id) {
                    // Mark usage as unresolved;
                    // I.e., it is unknown whether the object can be reached
                    // from the root
                    
                    // Find an arbitrary referencee
                }
                
                break;
            }
        }
        
        if(parentReferer == id && usage == 1) {
        }
    }

    @Override
    public void send(int selector, LObject[] arguments, Environment environment, LObject receiver) {
        if(slots == null) {
            slots = readSlots(environment);
        }
        
        Block behavior = (Block)slots.get(selector);
        behavior.evaluate(receiver, arguments, environment);
    }

    @Override
    public LObject resolve(int selector, Environment environment) {
        if(slots == null) {
            slots = readSlots(environment);
        }
        
        // On cachemiss, what to do...
        return slots.get(selector);
    }
}
