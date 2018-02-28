package com.mycompany.liveobjects;

import com.mycompany.liveobjects.lang.SyntaxErrorException;
import com.mycompany.liveobjects.runtime.Environment;
import com.mycompany.liveobjects.runtime.LObject;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Properties;
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
                
                InputStream inputStream = new ByteArrayInputStream(src.getBytes());
                try {
                    Environment environment = evaluator.evaluate(inputStream);
                    LObject result = environment.currentFrame().peek();
                    String resultAsString = result.toString(environment);
                    resultTextPane.setText(resultAsString);
                } catch (SyntaxErrorException ex) {
                    String resultAsString = ex.getFormattedSyntaxErrors().stream().collect(Collectors.joining("\n"));
                    resultTextPane.setText(resultAsString);
                }
            } catch (Exception ex) {
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
