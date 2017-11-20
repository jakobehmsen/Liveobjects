package com.mycompany.liveobjects.lang;

import com.mycompany.liveobjects.expr.Expression;

public interface Compiler {
    Expression compile(CompileContext compileContext);
}
