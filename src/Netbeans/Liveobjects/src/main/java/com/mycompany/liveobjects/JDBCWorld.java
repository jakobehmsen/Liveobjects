package com.mycompany.liveobjects;

public class JDBCWorld implements World {
    private int rootObjectId;
    private int integerPrototypeId;
    private int framePrototypeId;
    private int closurePrototypeId;
    private int trueId;
    private int falseId;
    private int arrayPrototypeId;    
    private ObjectLoader objectLoader;

    public JDBCWorld(ObjectLoader objectLoader) {
        this(objectLoader, 1, 2, 3, 4, 5, 6, 7);
    }

    public JDBCWorld(ObjectLoader objectLoader, 
            int rootObjectId, int integerPrototypeId, 
            int framePrototypeId, int closurePrototypeId,
            int trueId, int falseId, int arrayPrototypeId) {
        this.objectLoader = objectLoader;
        this.rootObjectId = rootObjectId;
        this.integerPrototypeId = integerPrototypeId;
        this.framePrototypeId = framePrototypeId;
        this.closurePrototypeId = closurePrototypeId;
        this.trueId = trueId;
        this.falseId = falseId;
        this.arrayPrototypeId = arrayPrototypeId;
    }
    
    private ObjectLoader getObjectLoader() {
        return objectLoader;
    }

    @Override
    public LObject getRoot() {
        return getObjectLoader().load(rootObjectId);
    }

    @Override
    public LObject getIntegerPrototype() {
        return getObjectLoader().load(integerPrototypeId);
    }

    @Override
    public LObject getFramePrototype() {
        return getObjectLoader().load(framePrototypeId);
    }

    @Override
    public LObject getClosurePrototype() {
        return getObjectLoader().load(closurePrototypeId);
    }

    @Override
    public LObject getTrue() {
        return getObjectLoader().load(trueId);
    }

    @Override
    public LObject getFalse() {
        return getObjectLoader().load(falseId);
    }

    @Override
    public LObject getArrayPrototype() {
        return getObjectLoader().load(arrayPrototypeId);
    }
}
