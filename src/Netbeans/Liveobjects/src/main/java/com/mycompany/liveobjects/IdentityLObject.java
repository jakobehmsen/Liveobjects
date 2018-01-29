package com.mycompany.liveobjects;

import java.util.Hashtable;
import java.util.Map;

public abstract class IdentityLObject implements LObject {
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
        
        clone.setParentSlot(environment.getSymbolCode("*parent"), this, environment);
        
        return clone;
    }

    @Override
    public String toString() {
        if(id != -1) {
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
            writeSlots(environment, slots, parentSlots);
            this.id = objectStore.nowUsedFrom(id, environment, slots, parentSlots, getObjectType());
        }
    }
    
    protected abstract int getObjectType();
    
    protected abstract void writeSlots(Environment environment, Map<Integer, LObject> slots, Map<Integer, LObject> parentSlots);

    @Override
    public void nowUnusedFrom(int id) {
        
    }
}
