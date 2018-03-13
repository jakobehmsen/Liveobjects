package com.mycompany.liveobjects.msgruntime;

import java.util.List;

public interface MessageSendJournalSession extends AutoCloseable {
    List<MessageSend> getMessageSendsAfter(int sequenceNumber);
    void addMessageSend(MessageSend messageSend);
    void commit();
    void rollback();
}
