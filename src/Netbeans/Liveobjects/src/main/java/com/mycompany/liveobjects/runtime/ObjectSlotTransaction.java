package com.mycompany.liveobjects.runtime;

import java.sql.Timestamp;
import java.util.List;

public interface ObjectSlotTransaction extends AutoCloseable {

    public void deleteSlotIntegerValue();

    public void deleteSlotReferenceValue(int id);

    public void addIntegerSlot(int value);

    public void updateIntegerSlot(int value);

    public void addStringSlot(String value);

    public void deleteStringSlot(String value);

    public void updateStringSlot(String value);

    public void deleteSlotBlockValue();

    public void addBlockSlot(int arity, int varCount, List<Instruction> instructions);

    public void updateBlockSlot(int arity, int varCount, List<Instruction> instructions);

    public void deleteSlotBlobValue();

    public void addBlobSlot(int type, byte[] bytes);

    public void updateBlobSlot(int type, byte[] bytes);

    public void addSlotReference(int id);

    public void updateSlotReference(int id);
    
}
