package com.mycompany.liveobjects;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) throws SQLException, FileNotFoundException, IOException {
        Configuration configuration = Configuration.load();
        
        ConnectionProvider connectionProvider = () -> configuration.createConnection();
        
        List<Class<? extends Instruction>> instructionClasses =
            Arrays.asList(Instructions.class.getClasses()).stream()
                .filter(c -> Instruction.class.isAssignableFrom(c))
                .map(c -> (Class<? extends Instruction>)c)
                .filter(c -> c.isAnnotationPresent(Operation.class))
                .collect(Collectors.toList());
        InstructionSet instructionSet = new OpcodeInstructionSet(new ReflectiveInstructionDescriptorResolver(instructionClasses));
        
        ScriptEvaluator evaluator = new ScriptEvaluator(connectionProvider, instructionSet);
        EvaluatorFrame frame = new EvaluatorFrame(evaluator);
        frame.setTitle("Liveobjects");
        
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        configuration.readFrame(frame);
        
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                evaluator.close();
                
                configuration.writeFrame(frame);
                
                try {
                    configuration.save();
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                System.exit(0);
            }
        });
        
        frame.setVisible(true);
    }
}
