package com.mycompany.liveobjects.runtime;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

public interface ConnectionProvider extends AutoCloseable {
    Connection getConnection() throws SQLException;
    default ConnectionProvider asPool() {
        ConnectionProvider self = this;
        
        return new ConnectionProvider() {
            private BlockingQueue<Connection> connectionQueue = new ArrayBlockingQueue<>(3);
            private CountDownLatch producerEnded = new CountDownLatch(1);
            private Thread connectionProducerThread;
            private volatile boolean produce;
            
            {
                produce = true;
                
                connectionProducerThread = new Thread(() -> {
                    while(produce) {
                        try {
                            Connection conn = self.getConnection();
                            connectionQueue.put(conn);
                        } catch (SQLException | InterruptedException ex) {
                            break;
                        }
                    }
                    
                    producerEnded.countDown();
                });
                connectionProducerThread.start();
            }
            
            @Override
            public Connection getConnection() throws SQLException {
                try {
                    Connection conn = connectionQueue.take();
                    return conn;
                } catch (InterruptedException ex) {
                    Logger.getLogger(ConnectionProvider.class.getName()).log(Level.SEVERE, null, ex);
                    return null;
                }
            }

            @Override
            public void close() throws Exception {
                produce = false;
                producerEnded.await();
                
                connectionQueue.forEach(c -> {
                    try {
                        c.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ConnectionProvider.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
            }
        };
    }
}
