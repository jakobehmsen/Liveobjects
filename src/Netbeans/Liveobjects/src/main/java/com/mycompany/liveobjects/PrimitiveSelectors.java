package com.mycompany.liveobjects;

public class PrimitiveSelectors {
    public static final String SET_SLOT = "setSlot:to:";
    public static final String SET_PARENT_SLOT = "setParentSlot:to:";
    public static final String IS_PARENT_SLOT = "isParentSlot:";
    public static final String RESOLVE_SLOT = "resolveSlot:";
    public static final String GET_SLOT = "getSlot:";
    public static final String HAS_SLOT = "hasSlot:";
    public static final String GET_SLOT_SELECTORS = "getSlotSelectors";
    public static final String PARENT = asParentSelector("parent");
    
    public static String asParentSelector(String selector) {
        return "*" + selector;
    }
}
