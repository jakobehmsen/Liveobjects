/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author jakob
 */
public class JDBCObject implements LObject {

    public static final int SLOT_TYPE_REFERENCE = 0;
    public static final int SLOT_TYPE_INTEGER = 1;
    public static final int SLOT_TYPE_STRING = 2;
    public static final int SLOT_TYPE_BLOCK = 3;
    
    public static final int REFERENCE_TYPE_NORMAL = 0;
    public static final int REFERENCE_TYPE_PARENT = 1;
    
    private Connection connection;
    private int id;
    private Map<Integer, LObject> slots;
    private Map<Integer, LObject> parentSlots;
    
    private PreparedStatement slotInsertStatement;
    private PreparedStatement slotReferenceValueDeleteStatement;
    private PreparedStatement slotReferenceValueInsertStatement;
    private PreparedStatement slotTypeUpdateStatement;
    private PreparedStatement slotInsertObjectStatement;
    private PreparedStatement slotBlobValueDeleteStatement;
    private PreparedStatement slotBlobValueInsertStatement;
    
    private InstructionSet instructionSet;
    private ObjectLoader objectLoader;

    public JDBCObject(Connection connection, InstructionSet instructionSet, ObjectLoader objectLoader, int id) {
        this.connection = connection;
        this.instructionSet = instructionSet;
        this.objectLoader = objectLoader;
        this.id = id;
        
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
                    "INSERT INTO object (type) VALUES (0)", Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void readSlots(Environment environment) {
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
            slots = new Hashtable<>();
            parentSlots = new Hashtable<>();
            
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
                    case REFERENCE_TYPE_NORMAL:
                        slots.put(symbolCode, object);
                        break;
                    case REFERENCE_TYPE_PARENT:
                        parentSlots.put(symbolCode, object);
                        break;
                }
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(JDBCObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public LObject getSlot(Environment environment, LObject[] arguments) {
        StringLObject selector = (StringLObject)arguments[0];
        int symbolCode = environment.getSymbolCode(selector.getValue());
        
        ensureSlotsRead(environment);
        
        return slots.get(symbolCode);
    }

    @Override
    public LObject setSlot(Environment environment, LObject[] arguments) {
        final StringLObject selector = (StringLObject)arguments[1];
        int symbolCode = environment.getSymbolCode(selector.getValue());
        
        ensureSlotsRead(environment);
        
        LObject newValue = arguments[0];
        
        return setSlot(environment, REFERENCE_TYPE_NORMAL, symbolCode, newValue);
    }
    
    private LObject setSlot(Environment environment, int referenceType, int symbolCode, LObject newValue) {
        /*final StringLObject selector = (StringLObject)arguments[1];
        int symbolCode = environment.getSymbolCode(selector.getValue());*/
        
        ensureSlotsRead(environment);
        
        //LObject newValue = arguments[0];
        String selector = environment.getSymbolString(symbolCode);
        LObject currentValue = slots.get(symbolCode);
        
        if(id != 0) {
            newValue.nowUsedFrom(id, environment);
        }
        
        ObjectSlotTransaction slotTransaction = createObjectSlotTransaction(selector, referenceType);
        
        if(id != 0 && currentValue != null) {
            currentValue.deleteSlotValue(slotTransaction);
        }
        
        switch(referenceType) {
            case REFERENCE_TYPE_NORMAL:
                slots.put(symbolCode, newValue);
                break;
            case REFERENCE_TYPE_PARENT:
                parentSlots.put(symbolCode, newValue);
                break;
        }
        
        if(id != 0) {
            if(currentValue == null) {
                newValue.addSlot(slotTransaction);
            } else {
                newValue.updateSlot(slotTransaction);
            }
        }
        
        return newValue;
    }
    
    private ObjectSlotTransaction createObjectSlotTransaction(String selector, int referenceType) {
        return new ObjectSlotTransaction() {
            @Override
            public void deleteSlotIntegerValue() {
                deleteSlotBlobValue();
            }

            @Override
            public void deleteSlotReferenceValue(int id) {
                try {
                    slotReferenceValueDeleteStatement.setInt(1, JDBCObject.this.id);
                    slotReferenceValueDeleteStatement.setString(2, selector);
                    slotReferenceValueDeleteStatement.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCObject.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void addSlotReference(int id) {
                try {
                    slotInsertStatement.setInt(1, JDBCObject.this.id);
                    slotInsertStatement.setString(2, selector);
                    slotInsertStatement.setInt(3, SLOT_TYPE_REFERENCE);
                    slotInsertStatement.execute();
                    
                    slotReferenceValueInsertStatement.setInt(1, JDBCObject.this.id);
                    slotReferenceValueInsertStatement.setString(2, selector);
                    slotReferenceValueInsertStatement.setInt(3, id);
                    slotReferenceValueInsertStatement.setInt(4, referenceType);
                    slotReferenceValueInsertStatement.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCObject.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void updateSlotReference(int id) {
                try {
                    slotReferenceValueInsertStatement.setInt(1, JDBCObject.this.id);
                    slotReferenceValueInsertStatement.setString(2, selector);
                    slotReferenceValueInsertStatement.setInt(3, id);
                    slotReferenceValueInsertStatement.setInt(4, referenceType);
                    slotReferenceValueInsertStatement.execute();
                    
                    slotTypeUpdateStatement.setInt(1, JDBCObject.this.id);
                    slotTypeUpdateStatement.setString(2, selector);
                    slotTypeUpdateStatement.setInt(3, SLOT_TYPE_REFERENCE);
                    slotTypeUpdateStatement.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCObject.class.getName()).log(Level.SEVERE, null, ex);
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
                        Logger.getLogger(JDBCObject.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });

                return outputStream.toByteArray();
            }

            @Override
            public void addBlockSlot(int arity, int varCount, List<Instruction> instructions) {
                try {
                    byte[] value = createBlockValue(arity, varCount, instructions);
                    addBlobSlot(SLOT_TYPE_BLOCK, value);
                } catch (IOException ex) {
                    Logger.getLogger(JDBCObject.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void updateBlockSlot(int arity, int varCount, List<Instruction> instructions) {
                try {
                    byte[] value = createBlockValue(arity, varCount, instructions);
                    updateBlobSlot(SLOT_TYPE_BLOCK, value);
                } catch (IOException ex) {
                    Logger.getLogger(JDBCObject.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void deleteSlotBlobValue() {
                try {
                    slotBlobValueDeleteStatement.setInt(1, id);
                    slotBlobValueDeleteStatement.setString(2, selector);
                    slotBlobValueDeleteStatement.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCObject.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(JDBCObject.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(JDBCObject.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
    }

    @Override
    public void deleteSlotValue(ObjectSlotTransaction slotTransaction) {
        slotTransaction.deleteSlotReferenceValue(id);
    }

    @Override
    public void addSlot(ObjectSlotTransaction slotTransaction) {
        slotTransaction.addSlotReference(id);
    }

    @Override
    public void updateSlot(ObjectSlotTransaction slotTransaction) {
        slotTransaction.updateSlotReference(id);
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public LObject cloneObject(Environment environment) {
        JDBCObject clone = new JDBCObject(connection, instructionSet, objectLoader, 0);
        
        clone.setParentSlot(environment.getSymbolCode("parent"), this, environment);
        
        return clone;
    }

    @Override
    public void nowUsedFrom(int id, Environment environment) {
        if(this.id == 0) {
            //this.parentReferer = id;
            
            try {
                // Usage is single initially
                //usage = USAGE_SINGLE;
                //slotInsertObjectStatement.setInt(usage, parentReferer);
                slotInsertObjectStatement.execute();
                ResultSet generatedKeys = slotInsertObjectStatement.getGeneratedKeys();
                if(generatedKeys.next()) {
                    this.id = generatedKeys.getInt(1);
                }
                
                // Persist all slots
                slots.forEach((symbolCode, value) -> {
                    String selector = environment.getSymbolString(symbolCode);
                    ObjectSlotTransaction objectSlotTransaction = 
                            createObjectSlotTransaction(selector, REFERENCE_TYPE_NORMAL);
                    value.addSlot(objectSlotTransaction);
                });
                parentSlots.forEach((symbolCode, value) -> {
                    String selector = environment.getSymbolString(symbolCode);
                    ObjectSlotTransaction objectSlotTransaction = 
                            createObjectSlotTransaction(selector, REFERENCE_TYPE_PARENT);
                    value.addSlot(objectSlotTransaction);
                });
            } catch (SQLException ex) {
                Logger.getLogger(JDBCObject.class.getName()).log(Level.SEVERE, null, ex);
            }
        }/* else if(parentReferer != id) {
            try {
                // Set usage to multi
                usage = USAGE_MULTI;
                slotUpdateObjectUsageStatement.setInt(1, usage);
                slotUpdateObjectUsageStatement.execute();
            } catch (SQLException ex) {
                Logger.getLogger(JDBCObject.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
    }

    @Override
    public void nowUnusedFrom(int id) {
        /*switch(usage) {
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
        }*/
    }

    @Override
    public void send(int selector, LObject[] arguments, Environment environment) {
        ensureSlotsRead(environment);
        
        Block behavior = (Block)resolve(selector, environment);
        if(behavior != null) {
            behavior.evaluate(this, arguments, environment);
        } else {
            String symbol = environment.getSymbolString(selector);
            environment.getDispatcher().handlePrimitiveError(environment, new StringLObject("Could not resolve symbol '" + symbol + "'."));
        }
    }

    @Override
    public LObject resolve(int selector, Environment environment) {
        ensureSlotsRead(environment);
        
        LObject value = slots.get(selector);
        if(value != null)
            return value;
        
        for(LObject parent: parentSlots.values()) {
            value = parent.resolve(selector, environment);
            if(value != null)
                return value;
        }
        
        return null;
    }

    private void ensureSlotsRead(Environment environment) {
        if(slots == null) {
            readSlots(environment);
        }
    }

    private LObject setParentSlot(int symbolCode, LObject parent, Environment environment) {
        //ensureSlotsRead(environment);
        
        // Should test that parent isn't child of this
        
        return setSlot(environment, REFERENCE_TYPE_PARENT, symbolCode, parent);
        
        //parentSlots.put(symbolCode, parent);
    }
}
