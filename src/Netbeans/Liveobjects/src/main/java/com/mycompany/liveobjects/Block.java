package com.mycompany.liveobjects;

import java.util.List;

public class Block extends PrimitiveLObject implements Behavior {
    private int arity;
    private int varCount;
    private List<Instruction> instructions;

    public Block(int arity, int varCount, List<Instruction> instructions) {
        this.arity = arity;
        this.varCount = varCount;
        this.instructions = instructions;
    }

    @Override
    public void deleteSlotValue(ObjectSlotTransaction slotTransaction) {
        slotTransaction.deleteSlotBlockValue();
    }

    @Override
    public void addSlot(ObjectSlotTransaction slotTransaction) {
        slotTransaction.addBlockSlot(arity, varCount, instructions);
    }

    @Override
    public void updateSlot(ObjectSlotTransaction slotTransaction) {
        slotTransaction.updateBlockSlot(arity, varCount, instructions);
    }

    @Override
    public LObject cloneObject(Environment environment) {
        return this;
    }

    @Override
    public void nowUsedFrom(int id, Environment environment) {
        
    }

    @Override
    public void nowUnusedFrom(int id) {
        
    }

    @Override
    public void invoke(LObject receiver, LObject[] arguments, Environment environment) {
        evaluate(receiver, arguments, environment);
    }

    public void evaluateAsClosure(LObject receiver, LObject[] arguments, Environment environment, Frame lexicalContext) {
        environment.pushFrameClosure(instructions.toArray(new Instruction[instructions.size()]), lexicalContext);
        environment.currentFrame().load(receiver);
        for (LObject argument : arguments) {
            environment.currentFrame().load(argument);
        }
        environment.currentFrame().allocate(varCount - 1);
    }

    public void evaluate(LObject receiver, LObject[] arguments, Environment environment) {
        environment.pushFrame(instructions.toArray(new Instruction[instructions.size()]));
        environment.currentFrame().load(receiver);
        for (LObject argument : arguments) {
            environment.currentFrame().load(argument);
        }
        environment.currentFrame().allocate(varCount - 1);
    }

    public void evaluate(LObject receiver, LObject[] arguments, Environment environment, LObject sender) {
        environment.pushFrame(instructions.toArray(new Instruction[instructions.size()]), sender);
        environment.currentFrame().load(receiver);
        for (LObject argument : arguments) {
            environment.currentFrame().load(argument);
        }
        environment.currentFrame().allocate(varCount - 1);
    }

    @Override
    protected LObject getProto(Environment environment) {
        return environment.getWorld().getBlockPrototype();
    }
}
