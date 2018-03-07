package com.mycompany.liveobjects.runtime;

import java.util.Hashtable;

public class LazyObjectLoader implements ObjectLoader {
    private ObjectStore objectStore;
    private Hashtable<Integer, LObject> objectCache;

    public LazyObjectLoader(ObjectStore objectStore) {
        this.objectStore = objectStore;
        objectCache = new Hashtable<>();
    }

    @Override
    public LObject load(int id) {
        return objectCache.computeIfAbsent(id, i -> {
            try(ObjectStoreConnection objectStoreConnection = objectStore.getObjectStoreConnection()) {
                return objectStoreConnection.load(id);
            } catch(Exception e) {
                throw new RuntimeException("An error occurred when attempting to load #" + id, e);
            }
        });
    }

    @Override
    public Frame newFrame(LObject sender, Instruction[] instructions, Frame lexicalContext) {
        return objectStore.newFrame(sender, instructions, lexicalContext);
    }

    @Override
    public Frame newFrame(LObject sender, Instruction[] instructions) {
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
        return objectStore.newJavaInstance(object);
    }
}
