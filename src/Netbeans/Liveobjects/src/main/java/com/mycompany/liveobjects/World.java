package com.mycompany.liveobjects;

public interface World {
    LObject getRoot();
    LObject getIntegerPrototype();
    LObject getFramePrototype();
    LObject getClosurePrototype();
    LObject getTrue();
    LObject getFalse();
    LObject getArrayPrototype();
}
