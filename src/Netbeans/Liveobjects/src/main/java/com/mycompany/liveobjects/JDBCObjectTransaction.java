package com.mycompany.liveobjects;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
    private PreparedStatement slotIntegerValueDeleteStatement;
    private PreparedStatement slotIntegerValueInsertStatement;
    private PreparedStatement slotStringValueDeleteStatement;
    private PreparedStatement slotStringValueInsertStatement;
    private PreparedStatement slotBlockValueDeleteStatement;
    private PreparedStatement slotBlockValueInsertStatement;
    private PreparedStatement slotTypeUpdateStatement;
    private PreparedStatement slotInsertObjectStatement;
    private PreparedStatement slotUpdateObjectUsageStatement;
    
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
            slotIntegerValueDeleteStatement = connection.prepareStatement(
                    "DELETE FROM slot_integer WHERE object_holder_id = ? AND symbol = ?");
            slotIntegerValueInsertStatement = connection.prepareStatement(
                    "INSERT INTO slot_integer (object_holder_id, symbol, value) VALUES (?, ?, ?)");
            slotStringValueDeleteStatement = connection.prepareStatement(
                    "DELETE FROM slot_string WHERE object_holder_id = ? AND symbol = ?");
            slotStringValueInsertStatement = connection.prepareStatement(
                    "INSERT INTO slot_string (object_holder_id, symbol, value) VALUES (?, ?, ?)");
            slotBlockValueDeleteStatement = connection.prepareStatement(
                    "DELETE FROM slot_block WHERE object_holder_id = ? AND symbol = ?");
            slotBlockValueInsertStatement = connection.prepareStatement(
                    "INSERT INTO slot_block (object_holder_id, symbol, value) VALUES (?, ?, ?)");
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
                "SELECT s.symbol, s.type, r.object_reference_id, null, null, null FROM slot s\n" +
                "   INNER JOIN slot_reference r\n" +
                "       ON r.object_holder_id = s.object_holder_id\n" +
                "       AND r.symbol = s.symbol\n" +
                "   WHERE s.object_holder_id = " + id + "\n" +
                "UNION ALL\n" +
                "SELECT s.symbol, s.type, null, i.value, null, null FROM slot s\n" +
                "   INNER JOIN slot_integer i\n" +
                "       ON i.object_holder_id = s.object_holder_id\n" +
                "       AND i.symbol = s.symbol\n" +
                "   WHERE s.object_holder_id = " + id + "\n" +
                "UNION ALL\n" +
                "SELECT s.symbol, s.type, null, null, z.value, null FROM slot s\n" +
                "   INNER JOIN slot_string z\n" +
                "       ON z.object_holder_id = s.object_holder_id\n" +
                "       AND z.symbol = s.symbol\n" +
                "   WHERE s.object_holder_id = " + id + "\n" +
                "UNION ALL\n" +
                "SELECT s.symbol, s.type, null, null, null, b.value FROM slot s\n" +
                "   INNER JOIN slot_block b\n" +
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
                
                switch(type) {
                    case SLOT_TYPE_REFERENCE:
                        int objectReferenceId = resultSet.getInt(3);
                        object = new DBLObject(new JDBCObjectTransaction(connection, instructionSet, objectReferenceId));
                        break;
                    case SLOT_TYPE_INTEGER: {
                        int value = resultSet.getInt(4);
                        object = new IntegerLObject(value);
                        break;
                    } case SLOT_TYPE_STRING: {
                        String value = resultSet.getString(5);
                        object = new StringLObject(value);
                        break;
                    } case SLOT_TYPE_BLOCK: {
                        byte[] value = resultSet.getBytes(6);
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
                try {
                    slotIntegerValueDeleteStatement.setInt(1, id);
                    slotIntegerValueDeleteStatement.setString(2, selector.getValue());
                    slotIntegerValueDeleteStatement.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCObjectTransaction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void setSlotIntegerValue(int value) {
                try {
                    slotIntegerValueInsertStatement.setInt(1, id);
                    slotIntegerValueInsertStatement.setString(2, selector.getValue());
                    slotIntegerValueInsertStatement.setInt(3, value);
                    slotIntegerValueInsertStatement.execute();
                    
                    slotTypeUpdateStatement.setInt(1, id);
                    slotTypeUpdateStatement.setString(2, selector.getValue());
                    slotTypeUpdateStatement.setInt(3, SLOT_TYPE_INTEGER);
                    slotTypeUpdateStatement.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCObjectTransaction.class.getName()).log(Level.SEVERE, null, ex);
                }
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
                try {
                    slotInsertStatement.setInt(1, id);
                    slotInsertStatement.setString(2, selector.getValue());
                    slotInsertStatement.setInt(3, SLOT_TYPE_INTEGER);
                    slotInsertStatement.execute();
                    
                    slotIntegerValueInsertStatement.setInt(1, id);
                    slotIntegerValueInsertStatement.setString(2, selector.getValue());
                    slotIntegerValueInsertStatement.setInt(3, value);
                    slotIntegerValueInsertStatement.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCObjectTransaction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void updateIntegerSlot(int value) {
                try {
                    slotIntegerValueInsertStatement.setInt(1, id);
                    slotIntegerValueInsertStatement.setString(2, selector.getValue());
                    slotIntegerValueInsertStatement.setInt(3, value);
                    slotIntegerValueInsertStatement.execute();
                    
                    slotTypeUpdateStatement.setInt(1, id);
                    slotTypeUpdateStatement.setString(2, selector.getValue());
                    slotTypeUpdateStatement.setInt(3, SLOT_TYPE_INTEGER);
                    slotTypeUpdateStatement.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCObjectTransaction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void addStringSlot(String value) {
                try {
                    slotInsertStatement.setInt(1, id);
                    slotInsertStatement.setString(2, selector.getValue());
                    slotInsertStatement.setInt(3, SLOT_TYPE_STRING);
                    slotInsertStatement.execute();
                    
                    slotStringValueInsertStatement.setInt(1, id);
                    slotStringValueInsertStatement.setString(2, selector.getValue());
                    slotStringValueInsertStatement.setString(3, value);
                    slotStringValueInsertStatement.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCObjectTransaction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void deleteStringSlot(String value) {
                try {
                    slotStringValueDeleteStatement.setInt(1, id);
                    slotStringValueDeleteStatement.setString(2, selector.getValue());
                    slotStringValueDeleteStatement.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCObjectTransaction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void updateStringSlot(String value) {
                try {
                    slotStringValueInsertStatement.setInt(1, id);
                    slotStringValueInsertStatement.setString(2, selector.getValue());
                    slotStringValueInsertStatement.setString(3, value);
                    slotStringValueInsertStatement.execute();
                    
                    slotTypeUpdateStatement.setInt(1, id);
                    slotTypeUpdateStatement.setString(2, selector.getValue());
                    slotTypeUpdateStatement.setInt(3, SLOT_TYPE_STRING);
                    slotTypeUpdateStatement.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCObjectTransaction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void deleteSlotBlockValue() {
                try {
                    slotBlockValueDeleteStatement.setInt(1, id);
                    slotBlockValueDeleteStatement.setString(2, selector.getValue());
                    slotBlockValueDeleteStatement.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCObjectTransaction.class.getName()).log(Level.SEVERE, null, ex);
                }
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
                    
                    slotBlockValueInsertStatement.setInt(1, id);
                    slotBlockValueInsertStatement.setString(2, selector.getValue());
                    slotBlockValueInsertStatement.setBytes(3, value);
                    slotBlockValueInsertStatement.execute();
                    
                    slotTypeUpdateStatement.setInt(1, id);
                    slotTypeUpdateStatement.setString(2, selector.getValue());
                    slotTypeUpdateStatement.setInt(3, SLOT_TYPE_BLOCK);
                    slotTypeUpdateStatement.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCObjectTransaction.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(JDBCObjectTransaction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void addBlockSlot(int arity, int varCount, List<Instruction> instructions) {
                try {
                    byte[] value = createBlockValue(arity, varCount, instructions);
                    
                    slotInsertStatement.setInt(1, id);
                    slotInsertStatement.setString(2, selector.getValue());
                    slotInsertStatement.setInt(3, SLOT_TYPE_BLOCK);
                    slotInsertStatement.execute();
                    
                    slotBlockValueInsertStatement.setInt(1, id);
                    slotBlockValueInsertStatement.setString(2, selector.getValue());
                    slotBlockValueInsertStatement.setBytes(3, value);
                    slotBlockValueInsertStatement.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCObjectTransaction.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(JDBCObjectTransaction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void updateBlockSlot(int arity, int varCount, List<Instruction> instructions) {
                try {
                    byte[] value = createBlockValue(arity, varCount, instructions);
                    
                    slotBlockValueInsertStatement.setInt(1, id);
                    slotBlockValueInsertStatement.setString(2, selector.getValue());
                    slotBlockValueInsertStatement.setBytes(3, value);
                    slotBlockValueInsertStatement.execute();
                    
                    slotTypeUpdateStatement.setInt(1, id);
                    slotTypeUpdateStatement.setString(2, selector.getValue());
                    slotTypeUpdateStatement.setInt(3, SLOT_TYPE_BLOCK);
                    slotTypeUpdateStatement.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCObjectTransaction.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
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
