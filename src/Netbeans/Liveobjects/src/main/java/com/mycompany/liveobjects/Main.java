package com.mycompany.liveobjects;

import com.mycompany.liveobjects.expr.Expression;
import com.mycompany.liveobjects.lang.DefaultCompileContext;
import com.mycompany.liveobjects.lang.ErrorHandler;
import com.mycompany.liveobjects.lang.Parser;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;

public class Main {
    public static void main(String[] args) throws SQLException, FileNotFoundException, IOException {
        Configuration configuration = Configuration.load();
        
        Connection connection = configuration.createConnection();
        
        MutableInstructionSet instructionSet = new MutableInstructionSet();
        
        instructionSet.registerInstruction(8, Instructions.storeLocalDescriptor);
        instructionSet.registerInstruction(9, Instructions.loadLocalDescriptor);
        instructionSet.registerInstruction(10, Instructions.loadStringDescriptor);
        instructionSet.registerInstruction(11, Instructions.retDescriptor);
        instructionSet.registerInstruction(12, Instructions.loadIntegerDescriptor);
        instructionSet.registerInstruction(13, Instructions.sendDescriptor);
        instructionSet.registerInstruction(14, Instructions.LoadContext.descriptor);
        instructionSet.registerInstruction(15, Instructions.Root.DESCRIPTOR);
        instructionSet.registerInstruction(16, Instructions.Top.DESCRIPTOR);
        
        JFrame frame = new JFrame("Liveobjects");
        
        Font font = new Font(Font.MONOSPACED, Font.PLAIN, 12);
        JTextPane srcTextPane = new JTextPane();
        srcTextPane.setFont(font);
        JTextPane resultTextPane = new JTextPane();
        resultTextPane.setFont(font);
        resultTextPane.setEditable(false);
        
        srcTextPane.registerKeyboardAction(e -> {
            try {
                LazyObjectLoader objectLoader = new LazyObjectLoader(ol -> new JDBCObjectStore(connection, instructionSet, ol));
                Dispatcher dispatcher = new DefaultDispatcher(objectLoader);
                World world = new JDBCWorld(objectLoader);
                
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
                    expression.compile(new DefaultExpressionCompileContext(), true).emit(instructions);

                    DefaultEnvironment environment = new DefaultEnvironment(world, dispatcher, new Instruction[] {
                        Instructions.loadInteger(0), // Dummy instruction; is always ignored due to ip incr
                        Instructions.finish()
                    });
                    environment.pushFrame(instructions.toArray(new Instruction[instructions.size()]));
                    environment.currentFrame().load(world.getRoot());
                    environment.currentFrame().allocate(compileContext.localCount() - 1);

                    while(!environment.finished()) {
                        environment.executeNext();
                    }

                    connection.commit();

                    LObject result = environment.currentFrame().peek();
                    String resultAsString = result.toString();
                    resultTextPane.setText(resultAsString);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (PrimitiveErrorException ex) {
                String result = "Primitive error occurred: " + ex.getError();
                resultTextPane.setText(result);
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.ALT_DOWN_MASK, false), JComponent.WHEN_FOCUSED);
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(srcTextPane), new JScrollPane(resultTextPane));
        splitPane.setResizeWeight(0.5);
        
        frame.getContentPane().add(splitPane, BorderLayout.CENTER);
        
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        frame.setVisible(true);
    }
}
