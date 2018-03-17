package com.mycompany.liveobjects.msgruntime;

public interface Environment {
    Context getContext();
    void setContext(Context context);
    void halt();
    void send(LObject receiver, int symbolCode, LObject[] args);
    boolean evaluationEnded();
    void evaluateNext();
}
