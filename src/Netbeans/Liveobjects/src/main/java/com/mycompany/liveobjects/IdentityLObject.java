package com.mycompany.liveobjects;

import java.sql.Timestamp;
import java.util.Hashtable;
import java.util.Map;

public abstract class IdentityLObject implements LObject {
    public static final int WRITE_CREATE = 0;
    public static final int WRITE_UPDATE = 1;
    
    protected int id;
    private Timestamp lastUpdate;
    protected ObjectStore objectStore;
    
    protected IdentityLObject(int id, Timestamp lastUpdate, ObjectStore objectStore) {
        this.id = id;
        this.lastUpdate = lastUpdate;
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
            try(ObjectStoreConnection objectStoreConnection = objectStore.getObjectStoreConnection()) {
                writeSlot(environment, objectStoreConnection, referenceType, symbolCode, newValue);
            } catch(Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    protected void writeSlot(Environment environment, ObjectStoreConnection objectStoreConnection, int referenceType, int symbolCode, LObject newValue) {
        newValue.nowUsedFrom(id, environment);

        String selector = getSelector(environment, referenceType, symbolCode);

        try(ObjectSlotTransaction slotTransaction = objectStoreConnection.createObjectSlotTransaction(id, selector, referenceType)) {
            LObject currentValue = getValue(environment, referenceType, symbolCode, selector);

            if(currentValue != null) {
                currentValue.deleteSlotValue(slotTransaction);
            }

            if(currentValue == null) {
                newValue.addSlot(slotTransaction);
            } else {
                newValue.updateSlot(slotTransaction);
            }

            lastUpdate = objectStoreConnection.lastUpdateTime(id);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    protected abstract String getSelector(Environment environment, int referenceType, int symbolCode);
    
    protected abstract LObject getValue(Environment environment, int referenceType, int symbolCode, String selector);

    @Override
    public void nowUsedFrom(int id, Environment environment) {
        if(this.id == 0) {
            try(ObjectStoreConnection objectStoreConnection = objectStore.getObjectStoreConnection()) {
                Hashtable<Integer, LObject> slots = new Hashtable<>();
                Hashtable<Integer, LObject> parentSlots = new Hashtable<>();
                writeSlots(environment, slots, parentSlots, WRITE_CREATE);
                this.id = objectStoreConnection.nowUsedFrom(id, environment, slots, parentSlots, getObjectType());
            } catch(Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    protected void writeSlots(Environment environment) {
        if(id != 0) {
            Hashtable<Integer, LObject> slots = new Hashtable<>();
            Hashtable<Integer, LObject> parentSlots = new Hashtable<>();
            writeSlots(environment, slots, parentSlots, WRITE_UPDATE);

            try(ObjectStoreConnection objectStoreConnection = objectStore.getObjectStoreConnection()) {
                slots.entrySet().forEach(e -> 
                    writeSlot(environment, objectStoreConnection, ObjectStore.REFERENCE_TYPE_NORMAL, e.getKey(), e.getValue()));
                parentSlots.entrySet().forEach(e -> 
                    writeSlot(environment, objectStoreConnection, ObjectStore.REFERENCE_TYPE_PARENT, e.getKey(), e.getValue()));
            } catch(Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    protected abstract int getObjectType();
    
    protected abstract void writeSlots(Environment environment, Map<Integer, LObject> slots, Map<Integer, LObject> parentSlots, int writeContext);

    @Override
    public void nowUnusedFrom(int id) {
        
    }
    
    protected void readSlots(Environment environment, boolean initialization) {
        try(ObjectStoreConnection objectStoreConnection = objectStore.getObjectStoreConnection()) {
            readSlots(environment, objectStoreConnection, initialization);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    protected void readSlots(Environment environment, ObjectStoreConnection objectStoreConnection, boolean initialization) {
        Hashtable<Integer, LObject> slots = new Hashtable<>();
        Hashtable<Integer, LObject> parentSlots = new Hashtable<>();
        objectStoreConnection.readSlots(id, environment, initialization ? null : lastUpdate, slots, parentSlots);
        readSlots(environment, slots, parentSlots, initialization);
    }
    
    protected abstract void readSlots(Environment environment, Map<Integer, LObject> slots, Map<Integer, LObject> parentSlots, boolean initialization);
    
    protected void ensureUpToDate(Environment environment) {
        if(id != 0) {
            try(ObjectStoreConnection objectStoreConnection = objectStore.getObjectStoreConnection()) {
                if(!isInitialized(environment)) {            
                    readSlots(environment, objectStoreConnection, true);
                } else {
                    Timestamp actualLastUpdate = objectStoreConnection.lastUpdateTime(id);
                    if(!actualLastUpdate.equals(lastUpdate)) {
                        readSlots(environment, objectStoreConnection, false);
                        lastUpdate = actualLastUpdate;
                    }
                }
            } catch(Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            if(!isInitialized(environment)) {            
                initializeTransient();
            }
        }
    }
    
    protected boolean isInitialized(Environment environment) {
        return true;
    }
    
    protected void initializeTransient() {
        
    }
}
