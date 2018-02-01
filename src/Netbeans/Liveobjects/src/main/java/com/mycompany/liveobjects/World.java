package com.mycompany.liveobjects;

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
}
