package com.mycompany.liveobjects;

import java.sql.Connection;
import java.util.Hashtable;

public class JDBCWorld implements World {
    private InstructionSet instructionSet;
    private Connection connection;
    private int rootObjectId;
    private int integerPrototypeId;
    private int framePrototypeId;
    private int closurePrototypeId;
    private int trueId;
    private int falseId;
    private Hashtable<Integer, LObject> objectCache;

    public JDBCWorld(Connection connection, InstructionSet instructionSet) {
        this(connection, instructionSet, 1, 2, 3, 4, 5, 6);
    }

    public JDBCWorld(Connection connection, InstructionSet instructionSet, 
            int rootObjectId, int integerPrototypeId, 
            int framePrototypeId, int closurePrototypeId,
            int trueId, int falseId) {
        this.connection = connection;
        this.instructionSet = instructionSet;
        this.rootObjectId = rootObjectId;
        this.integerPrototypeId = integerPrototypeId;
        this.framePrototypeId = framePrototypeId;
        this.closurePrototypeId = closurePrototypeId;
        this.trueId = trueId;
        this.falseId = falseId;
        objectCache = new Hashtable<>();
    }
    
    private ObjectLoader getObjectLoader() {
        return new ObjectLoader() {
            @Override
            public LObject load(int id) {
                return objectCache.computeIfAbsent(id, i -> 
                    new JDBCObject(connection, instructionSet, this, id));
            }
        };
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
}
