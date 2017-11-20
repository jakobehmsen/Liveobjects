/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.liveobjects;

import java.util.List;

/**
 *
 * @author jakob
 */
public class Block implements LObject {
    private int arity;
    private int varCount;
    private List<Instruction> instructions;

    public Block(int arity, int varCount, List<Instruction> instructions) {
        this.arity = arity;
        this.varCount = varCount;
        this.instructions = instructions;
    }

    @Override
    public void send(int selector, LObject[] arguments, Environment environment) {
        Block behavior = (Block)resolve(selector, environment);
        behavior.evaluate(this, arguments, environment);
    }

    @Override
    public void deleteSlotValue(ObjectSlotTransaction slotTransaction) {
        slotTransaction.deleteSlotBlockValue();
    }

    @Override
    public void setSlotValue(ObjectSlotTransaction slotTransaction) {
        slotTransaction.setSlotBlockValue(arity, varCount, instructions);
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
    public LObject cloneObject() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void nowUsedFrom(int id) {
        
    }

    @Override
    public void nowUnusedFrom(int id) {
        
    }

    public void evaluate(LObject receiver, LObject[] arguments, Environment environment) {
        environment.pushFrame(instructions.toArray(new Instruction[instructions.size()]));
        environment.currentFrame().load(receiver);
        for (LObject argument : arguments) {
            environment.currentFrame().load(argument);
        }
        environment.currentFrame().allocate(varCount);
    }

    @Override
    public LObject resolve(int selector, Environment environment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
