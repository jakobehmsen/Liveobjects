package com.mycompany.liveobjects;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class GUIEvaluator {
    public static void main(String[] args) throws SQLException, FileNotFoundException, IOException {
        Configuration configuration = Configuration.load();
        
        ScriptEvaluator evaluator = configuration.createScriptEvaluator();
        EvaluatorFrame frame = new EvaluatorFrame(evaluator);
        frame.setTitle("Liveobjects");
        
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        configuration.readFrame(frame);
        
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                configuration.writeFrame(frame);
                
                try {
                    configuration.save();
                } catch (IOException ex) {
                    Logger.getLogger(GUIEvaluator.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                System.exit(0);
            }
        });
        
        frame.setVisible(true);
    }
}
