package com.mycompany.liveobjects;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCObjectStore implements ObjectStore {
    public static final int SLOT_TYPE_REFERENCE = 0;
    public static final int SLOT_TYPE_INTEGER = 1;
    public static final int SLOT_TYPE_STRING = 2;
    public static final int SLOT_TYPE_BLOCK = 3;
    
    private ConnectionProvider connectionProvider;
    private InstructionSet instructionSet;
    private ObjectLoader objectLoader;
    
    public JDBCObjectStore(ConnectionProvider connectionProvider, InstructionSet instructionSet, ObjectLoader objectLoader) {
        this.connectionProvider = connectionProvider;
        this.instructionSet = instructionSet;
        this.objectLoader = objectLoader;
    }

    @Override
    public ArrayLObject newArray(LObject[] items) {
        return new ArrayLObject(this, 0, null, items);
    }

    @Override
    public AssociativeArrayLObject newAssociativeArray() {
        return new AssociativeArrayLObject(this, 0, null);
    }

    @Override
    public Frame newFrame(LObject sender, Instruction[] instructions, Frame lexicalContext) {
        return new DefaultFrame(0, null, this, sender, instructions, lexicalContext);
    }

    @Override
    public Frame newFrame(LObject sender, Instruction[] instructions) {
        return new DefaultFrame(0, null, this, sender, instructions);
    }

    @Override
    public Closure newClosure(Frame frame, Block behavior) {
        return new Closure(0, null, this, frame, behavior);
    }

    @Override
    public LObject newJavaInstance(Object object) {
        return new JavaInstanceLObject(object);
    }
    
    private Connection connection;
    private Stack<ObjectStoreConnection> connectionStack = new Stack<>();

    @Override
    public ObjectStoreConnection getObjectStoreConnection() {
        try {
            if(connectionStack.isEmpty()) {
                connection = connectionProvider.getConnection();
            }
            
            ObjectStoreConnection objectStoreConnection = new JDBCObjectStoreConnection(this, connection, instructionSet, objectLoader) {
                @Override
                public void close() throws Exception {
                    if(connectionStack.peek() != this) {
                        throw new IllegalStateException("Attempting to close object store connection that is not inner most.");
                    }
                    
                    connectionStack.pop();
                    
                    if(connectionStack.isEmpty()) {
                        connection.commit();
                        connection.close();
                    }
                }
            };
            
            connectionStack.push(objectStoreConnection);
            
            return objectStoreConnection;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCObjectStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
