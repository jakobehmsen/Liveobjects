package com.mycompany.liveobjects;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NativeLObject extends IdentityLObject implements ScalarLObject {
    private Object object;
    
    public NativeLObject(int id, ObjectStore objectStore, Object object) {
        super(id, objectStore);
        this.object = object;
    }

    @Override
    protected String getSelector(Environment environment, int referenceType, int symbolCode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void ensureInitialized(Environment environment) {
        if(object == null) {
            readSlots(environment);
        }
    }

    @Override
    public Object toNative(Environment environment) {
        ensureInitialized(environment);
        return object;
    }

    @Override
    protected LObject getValue(Environment environment, int referenceType, int symbolCode, String selector) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void readSlots(Environment environment, Map<Integer, LObject> slots, Map<Integer, LObject> parentSlots) {
        ArrayLObject serialization = (ArrayLObject) slots.get(environment.getSymbolCode("serialization"));
        byte[] byteArray = new byte[serialization.length(environment)];
        for(int i = 0; i < byteArray.length; i++) {
            byteArray[i] = (byte)((IntegerLObject)serialization.get(environment, i)).getValue();
        }
        
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArray);
            try (ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
                object = objectInputStream.readObject();
            }
        } catch (IOException|ClassNotFoundException ex) {
            environment.getDispatcher().handlePrimitiveError(environment, new StringLObject(ex.getMessage()));
        }
    }

    @Override
    protected void writeSlots(Environment environment, Map<Integer, LObject> slots, Map<Integer, LObject> parentSlots, int writeContext) {
        try {
            ArrayLObject serialization = getSerialization(environment);
            
            slots.put(environment.getSymbolCode("serialization"), serialization);
        } catch (IOException ex) {
            environment.getDispatcher().handlePrimitiveError(environment, new StringLObject(ex.getMessage()));
        }
    }
    
    private ArrayLObject getSerialization(Environment environment) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        LObject[] items;
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(object);
            byte[] byteArray = outputStream.toByteArray();
            items = new LObject[byteArray.length];
            for(int i = 0; i < byteArray.length; i++) {
                items[i] = new IntegerLObject(byteArray[i]);
            }
        }
        return environment.getObjectLoader().newArray(items);
    }

    @Override
    protected int getObjectType() {
        return ObjectStore.OBJECT_TYPE_NATIVE;
    }
    
    @Override
    public String[] getParentAndNonParentSlotSelectors(Environment environment, String parentSelector) {
        return new String[]{parentSelector};
    }

    @Override
    public boolean hasNonParentSlot(Environment environment, String selector) {
        return selector.equals("serialization");
    }

    @Override
    public LObject getNonParentSlot(Environment environment, String selector) {
        if(selector.equals("serialization")) {
            try {
                return getSerialization(environment);
            } catch (IOException ex) {
                // How to indicate that an error occurred here?
                //environment.getDispatcher().handlePrimitiveError(environment, new StringLObject(ex.getMessage()));
            }
        }
        
        return null;
    }

    @Override
    public LObject getProto(Environment environment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString(Environment environment) {
        ensureInitialized(environment);
        
        return object.toString();
    }
}
