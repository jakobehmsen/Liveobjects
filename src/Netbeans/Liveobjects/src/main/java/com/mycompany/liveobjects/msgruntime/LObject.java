package com.mycompany.liveobjects.msgruntime;

public interface LObject {
    void receive(Environment environment, int symbolCode, LObject args);
}
