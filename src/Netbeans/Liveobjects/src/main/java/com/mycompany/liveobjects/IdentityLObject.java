package com.mycompany.liveobjects;

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
        AssociativeArrayObject clone = objectStore.newAssociativeArray();
        
        clone.setParentSlot(environment.getSymbolCode("parent"), this, environment);
        
        return clone;
    }
}
