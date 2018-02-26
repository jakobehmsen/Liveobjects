package com.mycompany.liveobjects.runtime;

import java.sql.Timestamp;
import java.util.Map;

public interface ObjectStoreConnection extends AutoCloseable {
    LObject load(int id);
    int nowUsedFrom(int id, Environment environment, Map<Integer, LObject> slots, Map<Integer, LObject> parentSlots, int type);
    void readSlots(int id, Environment environment, Timestamp lastUpdate, Map<Integer, LObject> slots, Map<Integer, LObject> parentSlots);
    Timestamp lastUpdateTime(int id);
    ObjectSlotTransaction createObjectSlotTransaction(int id, String selector, int referenceType);
}
