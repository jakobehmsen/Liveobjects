package com.mycompany.liveobjects;

import java.util.Hashtable;

public class LazyObjectLoader implements ObjectLoader {
    private ObjectStoreSupplier objectStoreSupplier;
    private ObjectStore objectStore;
    private Hashtable<Integer, LObject> objectCache;

    public LazyObjectLoader(ObjectStoreSupplier objectStoreSupplier) {
        this.objectStoreSupplier = objectStoreSupplier;
        objectCache = new Hashtable<>();
    }
    
    private void ensureObjectStoreInitalized() {
        if(objectStore == null) {
            objectStore = objectStoreSupplier.getObjectStore(this);
        }
    }

    @Override
    public LObject load(int id) {
        ensureObjectStoreInitalized();
        
        return objectCache.computeIfAbsent(id, i -> 
            objectStore.load(id));
    }

    @Override
    public Frame newFrame(LObject sender, Instruction[] instructions, Frame lexicalContext) {
        ensureObjectStoreInitalized();
        
        return objectStore.newFrame(sender, instructions, lexicalContext);
    }

    @Override
    public Frame newFrame(LObject sender, Instruction[] instructions) {
        ensureObjectStoreInitalized();
        
        return objectStore.newFrame(sender, instructions);
    }

    @Override
    public Closure newClosure(Frame frame, Block behavior) {
        return objectStore.newClosure(frame, behavior);
    }

    @Override
    public ArrayLObject newArray(LObject[] items) {
        return objectStore.newArray(items);
    }

    @Override
    public LObject newNative(Object object) {
        return objectStore.newNative(object);
    }
}
