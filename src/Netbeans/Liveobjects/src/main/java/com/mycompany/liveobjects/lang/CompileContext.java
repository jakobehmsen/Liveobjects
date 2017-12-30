package com.mycompany.liveobjects.lang;

import java.util.List;

public interface CompileContext {
    int distanceToLocal(String str);
    boolean isLocal(String str);
    void declareLocal(String str);
    int getLocalOrdinal(String str);

    public int localCount();

    public CompileContext newForBlock(List<String> params, boolean isClosed);

    public CompileContext atContext(int distanceToLocal);
}
