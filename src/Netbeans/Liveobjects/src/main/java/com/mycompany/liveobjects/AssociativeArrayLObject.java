package com.mycompany.liveobjects;

import java.util.Hashtable;
import java.util.Map;
import java.util.stream.Stream;

public class AssociativeArrayLObject extends IdentityLObject {
    private Map<Integer, LObject> slots;
    private Map<Integer, LObject> parentSlots;

    public AssociativeArrayLObject(ObjectStore objectStore, int id) {
        super(id, objectStore);
    }
    
    @Override
    protected void readSlots(Environment environment, Map<Integer, LObject> slots, Map<Integer, LObject> parentSlots) {
        this.slots = new Hashtable<>(slots);
        this.parentSlots = new Hashtable<>(parentSlots);
    }

    @Override
    public LObject getSlot(Environment environment, String selector) {
        int symbolCode = environment.getSymbolCode(selector);
        
        ensureSlotsRead(environment);
        
        LObject obj = slots.get(symbolCode);
        
        if(obj != null) {
            return obj;
        }
        
        return parentSlots.get(symbolCode);
    }

    @Override
    public LObject setSlot(Environment environment, LObject[] arguments) {
        return setSlot(environment, arguments, ObjectStore.REFERENCE_TYPE_NORMAL);
    }

    @Override
    public boolean hasSlot(Environment environment, String selector) {
        ensureSlotsRead(environment);
        
        int symbolCode = environment.getSymbolCode(selector);
        return slots.containsKey(symbolCode) || parentSlots.containsKey(symbolCode);
    }

    @Override
    public LObject setParentSlot(Environment environment, LObject[] arguments) {
        return setSlot(environment, arguments, ObjectStore.REFERENCE_TYPE_PARENT);
    }

    private LObject setSlot(Environment environment, LObject[] arguments, int referenceType) {
        final StringLObject selector = (StringLObject)arguments[1];
        int symbolCode = environment.getSymbolCode(selector.getValue());
        
        ensureSlotsRead(environment);
        
        LObject newValue = arguments[0];
        
        return setSlot(environment, referenceType, symbolCode, newValue);
    }

    public LObject setParentSlot(int symbolCode, LObject parent, Environment environment) {
        return setSlot(environment, ObjectStore.REFERENCE_TYPE_PARENT, symbolCode, parent);
    }
    
    private LObject setSlot(Environment environment, int referenceType, int symbolCode, LObject newValue) {
        ensureSlotsRead(environment);
        
        writeSlot(environment, referenceType, symbolCode, newValue);
        
        switch(referenceType) {
            case ObjectStore.REFERENCE_TYPE_NORMAL:
                parentSlots.remove(symbolCode);
                slots.put(symbolCode, newValue);
                break;
            case ObjectStore.REFERENCE_TYPE_PARENT:
                boolean thisIsParentOfNewParent = newValue.isParent(environment, this);
                if(thisIsParentOfNewParent) {
                    String selector = environment.getSymbolString(symbolCode);
                    environment.currentFrame().handlePrimitiveError(
                        environment, 
                        new StringLObject("Can not set parent slot '" + selector + "' to child."));
                } else {
                    slots.remove(symbolCode);
                    parentSlots.put(symbolCode, newValue);
                }
                break;
        }
        
        return newValue;
    }

    @Override
    public String[] getSlotSelectors(Environment environment) {
        ensureSlotsRead(environment);
        
        return Stream.concat(parentSlots.keySet().stream(), slots.keySet().stream())
                .map(symbolCode -> environment.getSymbolString(symbolCode))
                .toArray(s -> new String[s]);
    }

    @Override
    protected String getSelector(Environment environment, int referenceType, int symbolCode) {
        return environment.getSymbolString(symbolCode);
    }

    @Override
    protected LObject getValue(Environment environment, int referenceType, int symbolCode, String selector) {
        switch(referenceType) {
            case ObjectStore.REFERENCE_TYPE_NORMAL:
                return slots.get(symbolCode);
            case ObjectStore.REFERENCE_TYPE_PARENT:
                return parentSlots.get(symbolCode);
        }
        
        return null;
    }

    @Override
    protected int getObjectType() {
        return ObjectStore.OBJECT_TYPE_ASSOCIATIVE_ARRAY;
    }

    @Override
    protected void writeSlots(Environment environment, Map<Integer, LObject> slots, Map<Integer, LObject> parentSlots, int writeContext) {
        slots.putAll(this.slots);
        parentSlots.putAll(this.parentSlots);
    }

    @Override
    public void send(int selector, LObject[] arguments, Environment environment) {
        ensureSlotsRead(environment);
        
        Block behavior = (Block)resolve(selector, environment);
        if(behavior != null) {
            behavior.evaluate(this, arguments, environment);
        } else {
            String symbol = environment.getSymbolString(selector);
            sendCannotResolveSlotError(environment, symbol);
        }
    }
    
    public static void sendCannotResolveSlotError(Environment environment, String symbol) {
        environment.getDispatcher().handlePrimitiveError(environment, new StringLObject("Could not resolve symbol '" + symbol + "'."));
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

    @Override
    public boolean isParent(Environment environment, AssociativeArrayLObject obj) {
        ensureSlotsRead(environment);
        if(parentSlots.isEmpty()) {
            return false;
        } else {
            if(parentSlots.containsValue(obj)) {
                return true;
            } else {
                return parentSlots.values().stream()
                    .anyMatch(p -> p.isParent(environment, obj));
            }
        }
    }

    @Override
    public boolean isParentSlot(Environment environment, String selector) {
        ensureSlotsRead(environment);
        int symbolCode = environment.getSymbolCode(selector);
        return parentSlots.containsKey(symbolCode);
    }
}
