/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.liveobjects.lang;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author jakob
 */
public class DefaultCompileContext implements CompileContext {
    private ArrayList<String> locals = new ArrayList<>();

    @Override
    public boolean isLocal(String str) {
        return locals.contains(str);
    }

    @Override
    public void declareLocal(String str) {
        if(!isLocal(str)) {
            locals.add(str);
        }
    }

    @Override
    public int getLocalOrdinal(String str) {
        return locals.indexOf(str);
    }

    @Override
    public int localCount() {
        return locals.size();
    }

    @Override
    public CompileContext newForBlock(List<String> params) {
        CompileContext compileContext = new DefaultCompileContext();
        
        params.forEach(x -> compileContext.declareLocal(x));
        
        return compileContext;
    }
}
