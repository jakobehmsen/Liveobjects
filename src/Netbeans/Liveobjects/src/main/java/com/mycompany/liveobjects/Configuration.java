package com.mycompany.liveobjects;

import com.mycompany.liveobjects.runtime.ConnectionProvider;
import com.mycompany.liveobjects.runtime.Evaluator;
import com.mycompany.liveobjects.runtime.Instruction;
import com.mycompany.liveobjects.runtime.InstructionSet;
import com.mycompany.liveobjects.runtime.Instructions;
import com.mycompany.liveobjects.runtime.OpcodeInstructionSet;
import com.mycompany.liveobjects.runtime.Operation;
import com.mycompany.liveobjects.runtime.ReflectiveInstructionDescriptorResolver;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Configuration {
    private static final String FilePath = "config.properties";
    
    private Properties properties;

    public Configuration() {
        this.properties = new Properties();
    }
    
    public Connection createConnection() throws SQLException {
        String username = properties.getProperty("database.username");
        String password = "";
        try {
            password = getPassword();
        } catch (Exception ex) {
            throw new RuntimeException("Could not create connection.", ex);
        }
        
        String dbName = properties.getProperty("database.schema");
        String dbHost = properties.getProperty("database.host");
        String url = "jdbc:mysql://" + dbHost + "/" + dbName + "?useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=true";
        
        Connection connection = DriverManager.getConnection(url, username, password);
        connection.setSchema(dbName);
        
        connection.setAutoCommit(false);
        
        return connection;
    }
    
    public void load(InputStream inputStream) throws IOException {
        properties.load(inputStream);
    }
    
    public static Configuration load() throws IOException {
        Configuration configuration = new Configuration();
        
        configuration.load(new FileInputStream(FilePath));
        
        return configuration;
    }

    public void writeFrame(EvaluatorFrame frame) {
        properties.setProperty("frame.bounds.x", "" + frame.getBounds().x);
        properties.setProperty("frame.bounds.y", "" + frame.getBounds().y);
        properties.setProperty("frame.bounds.width", "" + frame.getBounds().width);
        properties.setProperty("frame.bounds.height", "" + frame.getBounds().height);
        properties.setProperty("frame.extendedState", "" + frame.getExtendedState());
        
        frame.writeConfigurationTo(properties);
    }
    
    public void readFrame(EvaluatorFrame frame) {
        frame.readConfigurationFrom(properties);
        
        int frameBoundsX = Integer.parseInt(properties.getProperty("frame.bounds.x", "" + frame.getBounds().x));
        int frameBoundsY = Integer.parseInt(properties.getProperty("frame.bounds.y", "" + frame.getBounds().y));
        int frameBoundsWidth = Integer.parseInt(properties.getProperty("frame.bounds.width", "" + frame.getBounds().width));
        int frameBoundsHeight = Integer.parseInt(properties.getProperty("frame.bounds.height", "" + frame.getBounds().height));
        int frameExtendedState = Integer.parseInt(properties.getProperty("frame.extendedState", "" + frame.getExtendedState()));
        
        frame.setBounds(frameBoundsX, frameBoundsY, frameBoundsWidth, frameBoundsHeight);
        frame.setExtendedState(frameExtendedState);
    }
    
    public void save() throws IOException {
        properties.store(new FileOutputStream(FilePath, false), "");
    }
    
    public void setPassword(String password) throws NoSuchAlgorithmException, Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); // block size is 128bits
        SecretKey secretKey = keyGenerator.generateKey();
        String pwde = Security.encrypt(password, secretKey);
        String encodedKey = Security.encodeSecretKey(secretKey);
        
        properties.setProperty("database.password.encrypted", pwde);
        properties.setProperty("database.password.secretKey", encodedKey);
    }
    
    private String getPassword() throws Exception {
        String pwde = properties.getProperty("database.password.encrypted");
        String encodedKey = properties.getProperty("database.password.secretKey");
        SecretKey secretKey = Security.decodeSecretKey(encodedKey);
        
        return Security.decrypt(pwde, secretKey);
    }
    
    public ScriptEvaluator createScriptEvaluator() {
        ConnectionProvider connectionProvider = new ConnectionProvider() {
            @Override
            public Connection getConnection() throws SQLException {
                Connection conn = createConnection();
                return conn;
            }

            @Override
            public void close() throws Exception {
                
            }
        }.asPool();
        
        List<Class<? extends Instruction>> instructionClasses =
            Arrays.asList(Instructions.class.getClasses()).stream()
                .filter(c -> Instruction.class.isAssignableFrom(c))
                .map(c -> (Class<? extends Instruction>)c)
                .filter(c -> c.isAnnotationPresent(Operation.class))
                .collect(Collectors.toList());
        InstructionSet instructionSet = new OpcodeInstructionSet(new ReflectiveInstructionDescriptorResolver(instructionClasses));
        
        return new ScriptEvaluator(new Evaluator(connectionProvider, instructionSet));
    }
}
