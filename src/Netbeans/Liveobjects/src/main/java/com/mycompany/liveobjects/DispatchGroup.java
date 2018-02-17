package com.mycompany.liveobjects;

public interface DispatchGroup {
    boolean handles(LObject receiver, LObject[] arguments, Environment environment, int selector);
    Instructions.SendI replace(LObject receiver, LObject[] arguments, Environment environment, int selector);
}
