package com.mycompany.liveobjects;

import java.sql.Connection;
import java.util.Hashtable;

public class JDBCWorld implements World {
    private InstructionSet instructionSet;
    private Connection connection;
    private int rootObjectId;
    private int integerPrototypeId;
    private Hashtable<Integer, LObject> objectCache;

    public JDBCWorld(Connection connection, InstructionSet instructionSet) {
        this(connection, instructionSet, 1, 2);
    }

    public JDBCWorld(Connection connection, InstructionSet instructionSet, int rootObjectId, int integerPrototypeId) {
        this.connection = connection;
        this.instructionSet = instructionSet;
        this.rootObjectId = rootObjectId;
        this.integerPrototypeId = integerPrototypeId;
        objectCache = new Hashtable<>();
    }
    
    private ObjectLoader getObjectLoader() {
        return new ObjectLoader() {
            @Override
            public LObject load(int id) {
                return objectCache.computeIfAbsent(id, i -> 
                    new DBLObject(new JDBCObjectTransaction(connection, instructionSet, this, id)));
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
}
