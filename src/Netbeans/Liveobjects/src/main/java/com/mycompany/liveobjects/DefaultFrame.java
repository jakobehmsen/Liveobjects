package com.mycompany.liveobjects;

import java.util.Stack;

public class DefaultFrame implements Frame {
    private DefaultFrame sender;
    private Instruction[] instructions;
    private int ip;
    private Stack<LObject> stack = new Stack<>();

    public DefaultFrame(DefaultFrame sender, Instruction[] instructions) {
        this.sender = sender;
        this.instructions = instructions;
    }
    
    public void executeNext(Environment environment) {
        instructions[ip].execute(environment);
    }

    @Override
    public void load(LObject value) {
        stack.push(value);
    }

    @Override
    public LObject pop() {
        return stack.pop();
    }

    @Override
    public void loadInteger(int i) {
        load(new IntegerLObject(i));
    }

    @Override
    public void loadString(String str) {
        load(new StringLObject(str));
    }

    @Override
    public void cloneObject() {
        LObject obj = pop();
        load(obj.cloneObject());
    }

    @Override
    public void loadThis() {
        load(stack.get(0));
    }

    @Override
    public void load(int index) {
        stack.push(stack.get(index));
    }

    @Override
    public void incIP() {
        ip++;
    }

    @Override
    public void popInto(LObject[] arguments, int count) {
        for(int i = 0; i < count; i++) {
            arguments[i] = pop();
        }
    }

    @Override
    public LObject peek() {
        return stack.peek();
    }

    @Override
    public void dup2() {
        stack.insertElementAt(stack.peek(), stack.size() - 3);
    }

    @Override
    public void replaceInstruction(Instruction instruction) {
        instructions[ip] = instruction;
    }

    @Override
    public Frame sender() {
        return sender;
    }

    @Override
    public void dup() {
        stack.add(stack.peek());
    }

    @Override
    public void store(int ordinal) {
        stack.set(ordinal, stack.pop());
    }

    @Override
    public void allocate(int localCount) {
        for(int i = 0; i < localCount; i++) {
            stack.add(null);
        }
    }

    @Override
    public void handlePrimitiveError(Environment environment, LObject error) {
        throw new PrimitiveErrorException(error);
    }
}
