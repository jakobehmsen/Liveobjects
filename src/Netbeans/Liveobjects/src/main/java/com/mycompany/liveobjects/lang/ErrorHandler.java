package com.mycompany.liveobjects.lang;

public interface ErrorHandler {
    void syntaxError(int line, int column, String message);
}
