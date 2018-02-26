package com.mycompany.liveobjects.runtime;

import java.sql.SQLException;

public interface ObjectStore {
    public static final int REFERENCE_TYPE_NORMAL = 0;
    public static final int REFERENCE_TYPE_PARENT = 1;
    public static final int OBJECT_TYPE_ASSOCIATIVE_ARRAY = 0;
    public static final int OBJECT_TYPE_ARRAY = 1;
    public static final int OBJECT_TYPE_CONTEXT = 2;
    public static final int OBJECT_TYPE_CLOSURE = 3;

    //public LObject load(int id);
    
    public ArrayLObject newArray(LObject[] items);

    public AssociativeArrayLObject newAssociativeArray();

    public Frame newFrame(LObject sender, Instruction[] instructions, Frame lexicalContext);

    public Frame newFrame(LObject sender, Instruction[] instructions);
    
    Closure newClosure(Frame frame, Block behavior);

    LObject newJavaInstance(Object object);

    ObjectStoreConnection getObjectStoreConnection();
}
