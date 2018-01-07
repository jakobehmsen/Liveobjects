package com.mycompany.liveobjects;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCObject implements LObject {
    private int id;
    private Map<Integer, LObject> slots;
    private Map<Integer, LObject> parentSlots;
    private ObjectStore objectStore;

    public JDBCObject(ObjectStore objectStore, int id) {
        this.id = id;
        this.objectStore = objectStore;
    }
    
    private void readSlots(Environment environment) {
        slots = new Hashtable<>();
        parentSlots = new Hashtable<>();
        objectStore.readSlots(environment, id, slots, parentSlots);
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
        
        return setSlot(environment, ObjectStore.REFERENCE_TYPE_NORMAL, symbolCode, newValue);
    }
    
    private LObject setSlot(Environment environment, int referenceType, int symbolCode, LObject newValue) {
        ensureSlotsRead(environment);
        
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
            case ObjectStore.REFERENCE_TYPE_NORMAL:
                slots.put(symbolCode, newValue);
                break;
            case ObjectStore.REFERENCE_TYPE_PARENT:
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
        return objectStore.createObjectSlotTransaction(id, selector, referenceType);
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
            objectStore.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public LObject cloneObject(Environment environment) {
        JDBCObject clone = new JDBCObject(objectStore, 0);
        
        clone.setParentSlot(environment.getSymbolCode("parent"), this, environment);
        
        return clone;
    }

    @Override
    public void nowUsedFrom(int id, Environment environment) {
        if(this.id == 0) {
            this.id = objectStore.nowUsedFrom(id, environment, slots, parentSlots);
        }
    }

    @Override
    public void nowUnusedFrom(int id) {
        
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
        // Should test that parent isn't child of this
        
        return setSlot(environment, ObjectStore.REFERENCE_TYPE_PARENT, symbolCode, parent);
    }
}
