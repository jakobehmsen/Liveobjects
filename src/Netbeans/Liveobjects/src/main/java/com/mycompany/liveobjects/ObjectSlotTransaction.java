package com.mycompany.liveobjects;

import java.util.List;

public interface ObjectSlotTransaction {

    public void deleteSlotIntegerValue();

    public void setSlotIntegerValue(int value);

    public void deleteSlotReferenceValue(int id);

    public void setSlotReferenceValue(int id);

    public void addIntegerSlot(int value);

    public void updateIntegerSlot(int value);

    public void addStringSlot(String value);

    public void deleteStringSlot(String value);

    public void updateStringSlot(String value);

    public void deleteSlotBlockValue();

    public void setSlotBlockValue(int arity, int varCount, List<Instruction> instructions);

    public void addBlockSlot(int arity, int varCount, List<Instruction> instructions);

    public void updateBlockSlot(int arity, int varCount, List<Instruction> instructions);
    
}
