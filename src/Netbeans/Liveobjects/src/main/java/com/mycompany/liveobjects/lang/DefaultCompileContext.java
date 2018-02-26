package com.mycompany.liveobjects.lang;

import java.util.ArrayList;
import java.util.List;

public class DefaultCompileContext implements CompileContext {
    private CompileContext outerContext;
    private ArrayList<String> locals = new ArrayList<>();

    public DefaultCompileContext() {
        this(null);
    }

    public DefaultCompileContext(CompileContext outerContext) {
        this.outerContext = outerContext;
    }

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
    public CompileContext newForBlock(List<String> params, boolean isClosed) {
        CompileContext compileContext = new DefaultCompileContext(isClosed ? this : null);
        
        params.forEach(x -> compileContext.declareLocal(x));
        
        return compileContext;
    }

    @Override
    public int distanceToLocal(String str) {
        if(isLocal(str)) {
            return 0;
        } else if(outerContext != null){
            int outerDistance = outerContext.distanceToLocal(str);
            return outerDistance != -1 ? outerDistance + 1: -1;
        }
        
        return -1;
    }

    @Override
    public CompileContext atContext(int distanceToLocal) {
        if(distanceToLocal == 0) {
            return this;
        } else if(outerContext != null){
            return outerContext.atContext(distanceToLocal - 1);
        }
        
        return null;
    }
}
