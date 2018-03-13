package com.mycompany.liveobjects.msgruntime;

import java.io.Serializable;

public interface MessageSend extends Serializable {
    void evaluate(Environment environment);
}
