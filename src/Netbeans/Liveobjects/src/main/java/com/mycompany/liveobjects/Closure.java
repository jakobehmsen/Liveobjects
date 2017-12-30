/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.liveobjects;

/**
 *
 * @author jakob
 */
public class Closure implements LObject, Behavior {
    private Frame frame;
    private Block block;

    public Closure(Frame frame, Block block) {
        this.frame = frame;
        this.block = block;
    }
    
    public void evaluate(LObject receiver, LObject[] arguments, Environment environment) {
        block.evaluateAsClosure(receiver, arguments, environment, frame);
    }

    @Override
    public void invoke(LObject receiver, LObject[] arguments, Environment environment) {
        evaluate(receiver, arguments, environment);
    }

    @Override
    public void send(int selector, LObject[] arguments, Environment environment) {
        Block behavior = (Block)resolve(selector, environment);
        behavior.evaluate(this, arguments, environment);
    }

    @Override
    public void deleteSlotValue(ObjectSlotTransaction slotTransaction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSlotValue(ObjectSlotTransaction slotTransaction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addSlot(ObjectSlotTransaction slotTransaction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateSlot(ObjectSlotTransaction slotTransaction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LObject cloneObject(Environment environment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void nowUsedFrom(int id, Environment environment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void nowUnusedFrom(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LObject resolve(int selector, Environment environment) {
        return environment.getWorld().getClosurePrototype().resolve(selector, environment);
    }
}
