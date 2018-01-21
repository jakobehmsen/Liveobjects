package com.mycompany.liveobjects;

import java.sql.SQLException;
import java.util.Map;

public interface ObjectStore {
    public static final int REFERENCE_TYPE_NORMAL = 0;
    public static final int REFERENCE_TYPE_PARENT = 1;
    public static final int OBJECT_TYPE_ASSOCIATIVE_ARRAY = 0;
    public static final int OBJECT_TYPE_ARRAY = 1;
    
    ObjectSlotTransaction createObjectSlotTransaction(int id, String selector, int referenceType);

    public void readSlots(Environment environment, int id, Map<Integer, LObject> slots, Map<Integer, LObject> parentSlots);

    public void close() throws SQLException;

    public int nowUsedFrom(int id, Environment environment, Map<Integer, LObject> slots, Map<Integer, LObject> parentSlots, int type);

    public LObject load(int id);

    public ArrayLObject newArray(int length);

    public AssociativeArrayLObject newAssociativeArray();
}
