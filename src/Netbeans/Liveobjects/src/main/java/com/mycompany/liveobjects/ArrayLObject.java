package com.mycompany.liveobjects;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import java.util.stream.Collectors;

public class ArrayLObject extends IdentityLObject {
    private LObject[] items;

    public ArrayLObject(ObjectStore objectStore, int id) {
        super(id, objectStore);
        this.objectStore = objectStore;
    }

    public ArrayLObject(ObjectStore objectStore, int id, LObject[] items) {
        super(id, objectStore);
        this.objectStore = objectStore;
        this.items = items;
    }
    
    private void readItems(Environment environment) {
        Hashtable<Integer, LObject> slots = new Hashtable<>();
        Hashtable<Integer, LObject> parentSlots = new Hashtable<>();
        objectStore.readSlots(environment, id, slots, parentSlots);
        IntegerLObject length = (IntegerLObject) slots.get(environment.getSymbolCode("length"));
        items = new LObject[length.getValue()];
        slots.forEach((symbolCode, obj) -> {
            String selector = environment.getSymbolString(symbolCode);
            if(!selector.equals("length")) {
                int index = Integer.parseInt(selector);
                items[index] = obj;
            }
        });
    }
    
    public LObject get(Environment environment, int index) {
        if(items == null) {
            readItems(environment);
        }
        
        return items[index];
    }
    
    public void set(Environment environment, int index, LObject newValue) {
        if(items == null) {
            readItems(environment);
        }
        
        writeSlot(environment, ObjectStore.REFERENCE_TYPE_NORMAL, index, newValue);
        
        items[index] = newValue;
    }

    @Override
    protected String getSelector(Environment environment, int referenceType, int symbolCode) {
        return "" + symbolCode;
    }

    @Override
    protected LObject getValue(Environment environment, int referenceType, int symbolCode, String selector) {
        return items[symbolCode];
    }

    public int length(Environment environment) {
        if(items == null) {
            readItems(environment);
        }
        
        return items.length;
    }

    @Override
    public void send(int selector, LObject[] arguments, Environment environment) {
        Block behavior = (Block)resolve(selector, environment);
        behavior.evaluate(this, arguments, environment);
    }

    @Override
    protected int getObjectType() {
        return ObjectStore.OBJECT_TYPE_ARRAY;
    }

    @Override
    protected void writeSlots(Environment environment, Map<Integer, LObject> slots, Map<Integer, LObject> parentSlots) {
        for(int i = 0; i < items.length; i++) {
            if(items[i] != null) {
                slots.put(environment.getSymbolCode("" + i), items[i]);
            }
        }
        slots.put(environment.getSymbolCode("length"), new IntegerLObject(items.length));
    }

    @Override
    public LObject resolve(int selector, Environment environment) {
        return environment.getWorld().getArrayPrototype().resolve(selector, environment);
    }

    @Override
    public boolean isParent(Environment environment, AssociativeArrayLObject obj) {
        return environment.getWorld().getArrayPrototype().isParent(environment, obj);
    }

    @Override
    public String toString() {
        return Arrays.toString(items);
    }

    @Override
    public String toString(Environment environment) {
        if(items == null) {
            readItems(environment);
        }
        
        return "[" + Arrays.asList(items).stream()
                .map(x -> x != null ? x.toString(environment) : "nil")
                .collect(Collectors.joining(",")) + "]";
    }
}
