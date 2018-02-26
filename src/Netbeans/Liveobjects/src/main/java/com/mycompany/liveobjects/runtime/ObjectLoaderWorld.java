package com.mycompany.liveobjects.runtime;

public class ObjectLoaderWorld implements World {
    private int rootObjectId;
    private int integerPrototypeId;
    private int framePrototypeId;
    private int closurePrototypeId;
    private int trueId;
    private int falseId;
    private int arrayPrototypeId;
    private int blockPrototypeId;
    private int stringPrototypeId;
    private int nilId;
    private int javaId;
    private ObjectLoader objectLoader;

    public ObjectLoaderWorld(ObjectLoader objectLoader) {
        this(objectLoader, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
    }

    public ObjectLoaderWorld(ObjectLoader objectLoader, 
            int rootObjectId, int integerPrototypeId, 
            int framePrototypeId, int closurePrototypeId,
            int trueId, int falseId, int arrayPrototypeId, 
            int blockPrototypeId, int stringPrototypeId, 
            int nilId, int javaId) {
        this.objectLoader = objectLoader;
        this.rootObjectId = rootObjectId;
        this.integerPrototypeId = integerPrototypeId;
        this.framePrototypeId = framePrototypeId;
        this.closurePrototypeId = closurePrototypeId;
        this.trueId = trueId;
        this.falseId = falseId;
        this.arrayPrototypeId = arrayPrototypeId;
        this.blockPrototypeId = blockPrototypeId;
        this.stringPrototypeId = stringPrototypeId;
        this.nilId = nilId;
        this.javaId = javaId;
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

    @Override
    public LObject getBlockPrototype() {
        return getObjectLoader().load(blockPrototypeId);
    }

    @Override
    public LObject getStringPrototype() {
        return getObjectLoader().load(stringPrototypeId);
    }

    @Override
    public LObject getNil() {
        return getObjectLoader().load(nilId);
    }

    @Override
    public LObject getJava() {
        return getObjectLoader().load(javaId);
    }
}
