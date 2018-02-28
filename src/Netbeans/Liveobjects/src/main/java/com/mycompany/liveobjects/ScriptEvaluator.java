package com.mycompany.liveobjects;

import com.mycompany.liveobjects.expr.Expression;
import com.mycompany.liveobjects.lang.DefaultCompileContext;
import com.mycompany.liveobjects.lang.Parser;
import com.mycompany.liveobjects.lang.SyntaxErrorException;
import com.mycompany.liveobjects.runtime.Environment;
import com.mycompany.liveobjects.runtime.Evaluator;
import com.mycompany.liveobjects.runtime.Instruction;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ScriptEvaluator {
    private Evaluator evaluator;

    public ScriptEvaluator(Evaluator evaluator) {
        this.evaluator = evaluator;
    }
    
    public Environment evaluate(InputStream inputStream) throws IOException, SyntaxErrorException {
        com.mycompany.liveobjects.lang.Compiler compiler = new Parser()
            .parse(inputStream);

        DefaultCompileContext compileContext = new DefaultCompileContext();
        compileContext.declareLocal("self");
        Expression expression = compiler.compile(compileContext);

        ArrayList<Instruction> instructions = new ArrayList<>();
        expression.compile(true).emit(instructions);
        
        return evaluator.evaluate(compileContext.localCount() - 1, instructions.toArray(new Instruction[instructions.size()]));
    }
}
