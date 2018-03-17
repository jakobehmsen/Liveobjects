package com.mycompany.liveobjects.msgjournal.jdbc;

import com.mycompany.liveobjects.msgjournal.Journal;
import com.mycompany.liveobjects.msgjournal.JournalSession;
import com.mycompany.liveobjects.runtime.ConnectionProvider;
import java.sql.SQLException;

public class JDBCJournal implements Journal {
    private ConnectionProvider connectionProvider;

    public JDBCJournal(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    @Override
    public JournalSession createSession() {
        try {
            return new JDBCJournalSession(connectionProvider.getConnection());
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
