package com.mycompany.liveobjects.msgjournal.jdbc;

import com.mycompany.liveobjects.msgjournal.JournalSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCJournalSession implements JournalSession {
    private Connection connection;

    public JDBCJournalSession(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<byte[]> getSerializedMessageSendsAfter(int sequenceNumber) {
        try {
            PreparedStatement stmt = connection.prepareStatement(
                "SELECT serialized_message_send\n" +
                "FROM message_send_journal\n" +
                "WHERE sequence_number > ?\n" + 
                "ORDER BY sequence_number ASC");
            
            stmt.setInt(1, sequenceNumber);
            
            ArrayList<byte[]> list = new ArrayList<>();
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                 list.add(rs.getBytes(1));
            }
            
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void addMessageSend(byte[] serializedMessageSend) {
        try {
            PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO message_send_journal VALUES(?)");
            stmt.setBytes(1, serializedMessageSend);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
