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

    @Override
    public LObject load(int id) {
        if(objectStore == null) {
            objectStore = objectStoreSupplier.getObjectStore(this);
        }
        
        return objectCache.computeIfAbsent(id, i -> 
            objectStore.load(id));
    }

    @Override
    public ArrayLObject newArray(int length) {
        if(objectStore == null) {
            objectStore = objectStoreSupplier.getObjectStore(this);
        }
        
        return objectStore.newArray(length);
    }
}
