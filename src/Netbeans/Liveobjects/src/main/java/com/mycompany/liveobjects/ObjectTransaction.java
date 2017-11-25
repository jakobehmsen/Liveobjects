package com.mycompany.liveobjects;

public interface ObjectTransaction {

    public LObject getSlot(Environment environment, LObject[] arguments);

    public LObject setSlot(Environment environment, LObject[] arguments);

    public void deleteSlotValue(ObjectSlotTransaction slotTransaction);

    public void setSlotValue(ObjectSlotTransaction slotTransaction);

    public void close();

    public ObjectTransaction cloneObject(Environment environment, LObject self);

    public void nowUsedFrom(int id, Environment environment);

    public void nowUnusedFrom(int id);

    public void send(int selector, LObject[] arguments, Environment environment, LObject receiver);

    public LObject resolve(int selector, Environment environment);

    public void addSlot(ObjectSlotTransaction slotTransaction);

    public void updateSlot(ObjectSlotTransaction slotTransaction);
    
}
