package com.mycompany.liveobjects;

import java.util.Hashtable;
import java.util.Map;

public abstract class IdentityLObject implements LObject {
    public static final int WRITE_CREATE = 0;
    public static final int WRITE_UPDATE = 1;
    
    protected int id;
    protected ObjectStore objectStore;
    
    protected IdentityLObject(int id, ObjectStore objectStore) {
        this.id = id;
        this.objectStore = objectStore;
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

    @Override
    public LObject cloneObject(Environment environment) {
        AssociativeArrayLObject clone = objectStore.newAssociativeArray();
        
        clone.setParentSlot(environment.getSymbolCode(PrimitiveSelectors.PARENT), this, environment);
        
        return clone;
    }

    @Override
    public String toString() {
        if(id != 0) {
            return "#" + id;
        } else {
            return super.toString();
        }
    }
    
    protected void writeSlot(Environment environment, int referenceType, int symbolCode, LObject newValue) {
        if(id != 0) {
            newValue.nowUsedFrom(id, environment);
            
            String selector = getSelector(environment, referenceType, symbolCode);
            
            ObjectSlotTransaction slotTransaction = createObjectSlotTransaction(selector, referenceType);
            
            LObject currentValue = getValue(environment, referenceType, symbolCode, selector);
        
            if(currentValue != null) {
                currentValue.deleteSlotValue(slotTransaction);
            }
            
            if(currentValue == null) {
                newValue.addSlot(slotTransaction);
            } else {
                newValue.updateSlot(slotTransaction);
            }
            
            slotTransaction.commit();
        }
    }
    
    protected abstract String getSelector(Environment environment, int referenceType, int symbolCode);
    
    protected abstract LObject getValue(Environment environment, int referenceType, int symbolCode, String selector);
    
    protected ObjectSlotTransaction createObjectSlotTransaction(String selector, int referenceType) {
        return objectStore.createObjectSlotTransaction(id, selector, referenceType);
    }

    @Override
    public void nowUsedFrom(int id, Environment environment) {
        if(this.id == 0) {
            Hashtable<Integer, LObject> slots = new Hashtable<>();
            Hashtable<Integer, LObject> parentSlots = new Hashtable<>();
            writeSlots(environment, slots, parentSlots, WRITE_CREATE);
            this.id = objectStore.nowUsedFrom(id, environment, slots, parentSlots, getObjectType());
        }
    }
    
    protected void writeSlots(Environment environment) {
        if(id != 0) {
            Hashtable<Integer, LObject> slots = new Hashtable<>();
            Hashtable<Integer, LObject> parentSlots = new Hashtable<>();
            writeSlots(environment, slots, parentSlots, WRITE_UPDATE);

            slots.entrySet().forEach(e -> writeSlot(environment, ObjectStore.REFERENCE_TYPE_NORMAL, e.getKey(), e.getValue()));
            parentSlots.entrySet().forEach(e -> writeSlot(environment, ObjectStore.REFERENCE_TYPE_PARENT, e.getKey(), e.getValue()));
        }
    }
    
    protected abstract int getObjectType();
    
    protected abstract void writeSlots(Environment environment, Map<Integer, LObject> slots, Map<Integer, LObject> parentSlots, int writeContext);

    @Override
    public void nowUnusedFrom(int id) {
        
    }
    
    protected void readSlots(Environment environment) {
        Hashtable<Integer, LObject> slots = new Hashtable<>();
        Hashtable<Integer, LObject> parentSlots = new Hashtable<>();
        objectStore.readSlots(environment, id, slots, parentSlots);
        readSlots(environment, slots, parentSlots);
    }
    
    protected abstract void readSlots(Environment environment, Map<Integer, LObject> slots, Map<Integer, LObject> parentSlots);
}
