package com.mycompany.liveobjects;

import com.mycompany.liveobjects.expr.Expression;
import com.mycompany.liveobjects.lang.DefaultCompileContext;
import com.mycompany.liveobjects.lang.ErrorHandler;
import com.mycompany.liveobjects.lang.Parser;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;

public class EvaluatorFrame extends JFrame {
    private ScriptEvaluator evaluator;
    private JSplitPane splitPane;
    
    public EvaluatorFrame(ScriptEvaluator evaluator) {
        this.evaluator = evaluator;
        
        Font font = new Font(Font.MONOSPACED, Font.PLAIN, 12);
        JTextPane srcTextPane = new JTextPane();
        srcTextPane.setFont(font);
        JTextPane resultTextPane = new JTextPane();
        resultTextPane.setFont(font);
        resultTextPane.setEditable(false);
        
        srcTextPane.registerKeyboardAction(e -> {
            String origTitle = getTitle();
            
            try {
                setTitle(origTitle + " - Evaluating...");
                
                String src = srcTextPane.getText();
                DefaultCompileContext compileContext = new DefaultCompileContext();
                compileContext.declareLocal("self");
                
                ArrayList<String> syntaxErrors = new ArrayList<>();
                com.mycompany.liveobjects.lang.Compiler compiler = new Parser()
                    .setErrorHandler(new ErrorHandler() {
                        @Override
                        public void syntaxError(int line, int column, String message) {
                            syntaxErrors.add(line + "," + column + ": " + message);
                        }
                    })
                    .parse(src);
                
                if(syntaxErrors.size() > 0) {
                    String resultAsString = syntaxErrors.stream().collect(Collectors.joining("\n"));
                    resultTextPane.setText(resultAsString);
                } else {
                    Expression expression = compiler.compile(compileContext);

                    ArrayList<Instruction> instructions = new ArrayList<>();
                    expression.compile(true).emit(instructions);
                    
                    Future<Environment> environmentFuture = 
                        evaluator.evaluate(compileContext.localCount() - 1, instructions.toArray(new Instruction[instructions.size()]));

                    Environment environment = environmentFuture.get();

                    LObject result = environment.currentFrame().peek();
                    String resultAsString = result.toString(environment);
                    resultTextPane.setText(resultAsString);
                }
            } catch (InterruptedException | ExecutionException ex) {
                String result = "Primitive error occurred:\n" + ex.getMessage();
                resultTextPane.setText(result);
            } finally {
                setTitle(origTitle);
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.ALT_DOWN_MASK, false), JComponent.WHEN_FOCUSED);
        
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(srcTextPane), new JScrollPane(resultTextPane));
        splitPane.setResizeWeight(0.5);
        
        getContentPane().add(splitPane, BorderLayout.CENTER);
    }

    public void writeConfigurationTo(Properties properties) {
        properties.setProperty("frame.splitPane.dividerLocation", "" + splitPane.getDividerLocation());
    }

    public void readConfigurationFrom(Properties properties) {
        splitPane.setDividerLocation(Integer.parseInt(properties.getProperty("frame.splitPane.dividerLocation", "" + splitPane.getDividerLocation())));
    }
}
