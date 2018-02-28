package com.mycompany.liveobjects;

import com.mycompany.liveobjects.lang.SyntaxErrorException;
import com.mycompany.liveobjects.runtime.Environment;
import com.mycompany.liveobjects.runtime.LObject;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutionException;

public class FileEvaluator {
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException, ExecutionException {
        if(args.length != 1) {
            throw new IllegalArgumentException("Expected file path as argument.");
        }
        
        String filePath = args[0];
        
        Configuration configuration = Configuration.load();
        
        ScriptEvaluator evaluator = configuration.createScriptEvaluator();
        
        try(InputStream inputStream = new FileInputStream(filePath)) {
            try {
                Environment environment = evaluator.evaluate(inputStream);
                LObject result = environment.currentFrame().peek();
                String resultAsString = result.toString(environment);
                System.out.println(resultAsString);
            } catch (SyntaxErrorException ex) {
                ex.getFormattedSyntaxErrors().forEach(e -> System.err.println(e));
            }
        }
    }
}
