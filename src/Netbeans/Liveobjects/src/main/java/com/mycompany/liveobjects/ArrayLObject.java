package com.mycompany.liveobjects;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class ArrayLObject extends IdentityLObject implements PrimitiveLObject {
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
    
    private void ensureItemsRead(Environment environment) {
        if(items == null) {
            readItems(environment);
        }
    }
    
    private void readItems(Environment environment) {
        readSlots(environment);
    }
    
    @Override
    protected void readSlots(Environment environment, Map<Integer, LObject> slots, Map<Integer, LObject> parentSlots) {
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
        ensureItemsRead(environment);
        
        return items[index];
    }
    
    public void set(Environment environment, int index, LObject newValue) {
        ensureItemsRead(environment);
        
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
        ensureItemsRead(environment);
        
        return items.length;
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
    public String toString() {
        return Arrays.toString(items);
    }

    @Override
    public String toString(Environment environment) {
        ensureItemsRead(environment);
        
        return "[" + Arrays.asList(items).stream()
                .map(x -> x != null ? x.toString(environment) : "nil")
                .collect(Collectors.joining(",")) + "]";
    }

    @Override
    public LObject getProto(Environment environment) {
        return environment.getWorld().getArrayPrototype();
    }
}
