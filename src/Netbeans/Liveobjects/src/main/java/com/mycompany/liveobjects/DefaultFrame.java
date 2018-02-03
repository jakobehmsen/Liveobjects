package com.mycompany.liveobjects;

import java.util.Map;
import java.util.Stack;

public class DefaultFrame extends IdentityLObject implements Frame, PrimitiveLObject {
    private LObject sender;
    private Instruction[] instructions;
    private int ip;
    private Stack<LObject> stack;
    private Frame lexicalContext;

    public DefaultFrame(int id, ObjectStore objectStore, LObject sender, Instruction[] instructions, Frame lexicalContext) {
        super(id, objectStore);
        this.sender = sender;
        this.instructions = instructions;
        this.lexicalContext = lexicalContext;
        stack = new Stack<>();
    }

    public DefaultFrame(int id, ObjectStore objectStore, LObject sender, Instruction[] instructions) {
        super(id, objectStore);
        this.sender = sender;
        this.instructions = instructions;
        stack = new Stack<>();
    }

    public DefaultFrame(int id, ObjectStore objectStore) {
        super(id, objectStore);
    }
    
    @Override
    public void executeNext(Environment environment) {
        instructions[ip].execute(environment);
    }

    @Override
    public void load(LObject value) {
        stackPush(value);
    }

    @Override
    public LObject pop() {
        return stackPop();
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
    public void loadThis() {
        load(stackGet(0));
    }

    @Override
    public void load(int index) {
        stackPush(stackGet(index));
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
        return stackPeek();
    }

    @Override
    public void dup2() {
        stackInsertElementAt(stackPeek(), stackSize() - 3);
    }

    @Override
    public void replaceInstruction(Instruction instruction) {
        instructions[ip] = instruction;
    }

    @Override
    public LObject sender() {
        return sender;
    }

    @Override
    public void dup() {
        stackPush(stackPeek());
    }

    @Override
    public void store(int ordinal) {
        stackSet(ordinal, stackPop());
    }

    @Override
    public void allocate(Environment environment, int localCount) {
        for(int i = 0; i < localCount; i++) {
            stackPush(environment.getWorld().getNil());
        }
    }

    @Override
    public void handlePrimitiveError(Environment environment, LObject error) {
        throw new PrimitiveErrorException(error);
    }

    @Override
    public void resumeWith(Environment environment, LObject result) {
        load(result);
        incIP();
        environment.currentFrame(this);
    }

    @Override
    public void loadContext() {
        load(this);
    }

    @Override
    public LObject getDistant(Environment environment, int contextDistance, int ordinal) {
        ensureRead(environment);
        if(contextDistance == 0) {
            return stackGet(ordinal);
        } else {
            return lexicalContext.getDistant(environment, contextDistance - 1, ordinal);
        }
    }

    @Override
    public void setDistant(Environment environment, int contextDistance, int ordinal, LObject value) {
        ensureRead(environment);
        if(contextDistance == 0) {
            writeSlot(environment, ObjectStore.REFERENCE_TYPE_NORMAL, environment.getSymbolCode("" + ordinal), value);
            stackSet(ordinal, value);
        } else {
            lexicalContext.setDistant(environment, contextDistance - 1, ordinal, value);
        }
    }

    @Override
    public void setIP(int location) {
        this.ip = location;
    }

    @Override
    public LObject cloneObject(Environment environment) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LObject getProto(Environment environment) {
        return environment.getWorld().getFramePrototype();
    }
    
    private void stackPush(LObject obj) {
        stack.push(obj);
    }
    
    private LObject stackPeek() {
        return stack.peek();
    }
    
    private LObject stackPop() {
        return stack.pop();
    }
    
    private void stackSet(int index, LObject obj) {
        stack.set(index, obj);
    }
    
    private LObject stackGet(int index) {
        return stack.get(index);
    }
    
    private void stackInsertElementAt(LObject obj, int index) {
        stack.insertElementAt(obj, index);
    }
    
    private int stackSize() {
        return stack.size();
    }

    @Override
    protected String getSelector(Environment environment, int referenceType, int symbolCode) {
        return environment.getSymbolString(symbolCode);
    }

    @Override
    protected LObject getValue(Environment environment, int referenceType, int symbolCode, String selector) {
        if(selector.equals("stackSize")) {
            return new IntegerLObject(stack.size());
        } else {
            int index = Integer.parseInt(selector);
            return stack.get(index);
        }
    }

    @Override
    protected int getObjectType() {
        return ObjectStore.OBJECT_TYPE_CONTEXT;
    }

    @Override
    protected void writeSlots(Environment environment, Map<Integer, LObject> slots, Map<Integer, LObject> parentSlots) {
        for(int i = 0; i < stack.size(); i++) {
            slots.put(environment.getSymbolCode("" + i), stack.get(i));
        }
        slots.put(environment.getSymbolCode("stackSize"), new IntegerLObject(stack.size()));
    }

    @Override
    protected void readSlots(Environment environment, Map<Integer, LObject> slots, Map<Integer, LObject> parentSlots) {
        IntegerLObject length = (IntegerLObject) slots.get(environment.getSymbolCode("stackSize"));
        for(int i = 0; i < length.getValue(); i++) {
            LObject obj = slots.get(environment.getSymbolCode("" + i));
            stack.push(obj);
        }
    }

    @Override
    public void onHalt(Environment environment) {
        writeSlots(environment);
    }

    private void ensureRead(Environment environment) {
        if(stack == null) {
            stack = new Stack<>();
            readSlots(environment);
        }
    }
}
