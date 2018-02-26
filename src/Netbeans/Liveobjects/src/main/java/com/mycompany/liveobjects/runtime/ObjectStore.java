package com.mycompany.liveobjects.runtime;

public interface ObjectStore {
    public static final int REFERENCE_TYPE_NORMAL = 0;
    public static final int REFERENCE_TYPE_PARENT = 1;
    public static final int OBJECT_TYPE_ASSOCIATIVE_ARRAY = 0;
    public static final int OBJECT_TYPE_ARRAY = 1;
    public static final int OBJECT_TYPE_CONTEXT = 2;
    public static final int OBJECT_TYPE_CLOSURE = 3;
    
    ArrayLObject newArray(LObject[] items);
    AssociativeArrayLObject newAssociativeArray();
    Frame newFrame(LObject sender, Instruction[] instructions, Frame lexicalContext);
    Frame newFrame(LObject sender, Instruction[] instructions);
    Closure newClosure(Frame frame, Block behavior);
    LObject newJavaInstance(Object object);
    ObjectStoreConnection getObjectStoreConnection();
}
