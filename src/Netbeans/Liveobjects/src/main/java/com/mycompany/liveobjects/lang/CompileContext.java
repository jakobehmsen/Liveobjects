package com.mycompany.liveobjects.lang;

import java.util.List;

public interface CompileContext {
    boolean isLocal(String str);
    void declareLocal(String str);
    int getLocalOrdinal(String str);

    public int localCount();

    public CompileContext newForBlock(List<String> params);
}
