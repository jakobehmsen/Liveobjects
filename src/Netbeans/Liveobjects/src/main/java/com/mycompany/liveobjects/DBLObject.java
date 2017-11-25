package com.mycompany.liveobjects;

import java.io.IOException;

public class DBLObject implements LObject {
    private ObjectTransaction transaction;
    
    public DBLObject(ObjectTransaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public void send(int selector, LObject[] arguments, Environment environment) {
        /*// Indicate to transaction that it is now being interacted with
        // This could implicate the object's parent being resolved
        
        switch(selector) {
            case PrimitiveSelectors.GET_SLOT: {
                LObject value = transaction.getSlot(environment, arguments);
                environment.currentFrame().load(value);
                environment.currentFrame().incIP();
                break;
            } case PrimitiveSelectors.SET_SLOT: {
                LObject value = transaction.setSlot(environment, arguments);
                environment.currentFrame().load(value);
                environment.currentFrame().incIP();
                break;
            } default: {
                transaction.send(selector, arguments, environment, this);
                break;
            }
        }*/
        transaction.send(selector, arguments, environment, this);
    }

    @Override
    public void deleteSlotValue(ObjectSlotTransaction slotTransaction) {
        transaction.deleteSlotValue(slotTransaction);
    }

    @Override
    public void setSlotValue(ObjectSlotTransaction slotTransaction) {
        transaction.setSlotValue(slotTransaction);
    }

    @Override
    public void addSlot(ObjectSlotTransaction slotTransaction) {
        transaction.addSlot(slotTransaction);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateSlot(ObjectSlotTransaction slotTransaction) {
        transaction.updateSlot(slotTransaction);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void close() throws IOException {
        transaction.close();
    }

    @Override
    public LObject cloneObject(Environment environment) {
        return new DBLObject(transaction.cloneObject(environment, this));
    }

    @Override
    public void nowUsedFrom(int id, Environment environment) {
        transaction.nowUsedFrom(id, environment);
    }

    @Override
    public void nowUnusedFrom(int id) {
        transaction.nowUnusedFrom(id);
    }

    @Override
    public LObject getSlot(Environment environment, LObject[] arguments) {
        return transaction.getSlot(environment, arguments);
    }

    @Override
    public LObject setSlot(Environment environment, LObject[] arguments) {
        return transaction.setSlot(environment, arguments);
    }

    @Override
    public LObject resolve(int selector, Environment environment) {
        return transaction.resolve(selector, environment);
    }
}
