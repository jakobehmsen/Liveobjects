package com.mycompany.liveobjects.msgjournal;

import java.util.List;

public interface JournalSession extends AutoCloseable {
    List<byte[]> getSerializedMessageSendsAfter(int sequenceNumber);
    void addMessageSend(byte[] serializedMessageSend);
    void commit();
    void rollback();
}
