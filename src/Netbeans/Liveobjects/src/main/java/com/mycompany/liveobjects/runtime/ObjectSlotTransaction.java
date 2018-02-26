package com.mycompany.liveobjects.runtime;

import java.util.List;

public interface ObjectSlotTransaction extends AutoCloseable {
    void deleteSlotIntegerValue();
    void deleteSlotReferenceValue(int id);
    void addIntegerSlot(int value);
    void updateIntegerSlot(int value);
    void addStringSlot(String value);
    void deleteStringSlot(String value);
    void updateStringSlot(String value);
    void deleteSlotBlockValue();
    void addBlockSlot(int arity, int varCount, List<Instruction> instructions);
    void updateBlockSlot(int arity, int varCount, List<Instruction> instructions);
    void deleteSlotBlobValue();
    void addBlobSlot(int type, byte[] bytes);
    void updateBlobSlot(int type, byte[] bytes);
    void addSlotReference(int id);
    void updateSlotReference(int id);
}
