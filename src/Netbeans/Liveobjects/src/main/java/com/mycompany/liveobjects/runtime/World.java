package com.mycompany.liveobjects.runtime;

public interface World {
    LObject getRoot();
    LObject getIntegerPrototype();
    LObject getFramePrototype();
    LObject getClosurePrototype();
    LObject getTrue();
    LObject getFalse();
    LObject getArrayPrototype();
    LObject getBlockPrototype();
    LObject getStringPrototype();
    LObject getNil();
    LObject getJava();
    LObject getStore();

    default LObject getBoolean(boolean b) {
        return b ? getTrue() : getFalse();
    }
}
