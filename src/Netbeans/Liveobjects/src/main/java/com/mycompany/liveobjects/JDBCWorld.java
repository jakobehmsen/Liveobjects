package com.mycompany.liveobjects;

import java.sql.Connection;

public class JDBCWorld implements World {
    private InstructionSet instructionSet;
    private Connection connection;
    private int rootObjectId;
    private int integerPrototypeId;

    public JDBCWorld(Connection connection, InstructionSet instructionSet) {
        this(connection, instructionSet, 1, 2);
    }

    public JDBCWorld(Connection connection, InstructionSet instructionSet, int rootObjectId, int integerPrototypeId) {
        this.connection = connection;
        this.instructionSet = instructionSet;
        this.rootObjectId = rootObjectId;
        this.integerPrototypeId = integerPrototypeId;
    }

    @Override
    public DBLObject getRoot() {
        return new DBLObject(new JDBCObjectTransaction(connection, instructionSet, rootObjectId));
    }

    @Override
    public LObject getIntegerPrototype() {
        return new DBLObject(new JDBCObjectTransaction(connection, instructionSet, integerPrototypeId));
    }
}
