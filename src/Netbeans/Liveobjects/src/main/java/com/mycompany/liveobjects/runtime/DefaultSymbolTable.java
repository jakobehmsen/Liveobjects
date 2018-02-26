package com.mycompany.liveobjects.runtime;

import java.util.Hashtable;

public class DefaultSymbolTable implements SymbolTable {
    private Hashtable<String, Integer> stringToSymbolCodeMap = new Hashtable<>();
    private Hashtable<Integer, String> symbolCodeToStringMap = new Hashtable<>();

    @Override
    public int getSymbolCode(String str) {
        Integer symbolCode = stringToSymbolCodeMap.get(str);
        
        if(symbolCode == null) {
            symbolCode = stringToSymbolCodeMap.size();
            stringToSymbolCodeMap.put(str, symbolCode);
            symbolCodeToStringMap.put(symbolCode, str);
        }
        
        return symbolCode;
    }
    
    @Override
    public String getSymbolString(int symbolCode) {
        return symbolCodeToStringMap.get(symbolCode);
    }
}
