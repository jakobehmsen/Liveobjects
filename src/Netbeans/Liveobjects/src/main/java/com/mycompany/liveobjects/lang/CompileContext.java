package com.mycompany.liveobjects.lang;

import java.util.List;

public interface CompileContext {
    int distanceToLocal(String str);
    boolean isLocal(String str);
    void declareLocal(String str);
    int getLocalOrdinal(String str);
    int localCount();
    CompileContext newForBlock(List<String> params, boolean isClosed);
    CompileContext atContext(int distanceToLocal);
}
