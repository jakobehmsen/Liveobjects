package com.mycompany.liveobjects.runtime;

public interface SymbolTable {
    int getSymbolCode(String str);
    String getSymbolString(int symbolCode);
}
