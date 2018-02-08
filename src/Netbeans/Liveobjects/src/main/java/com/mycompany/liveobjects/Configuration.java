package com.mycompany.liveobjects;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JFrame;

public class Configuration {
    private static final String FilePath = "config.properties";
    
    private Properties properties;

    public Configuration() {
        this.properties = new Properties();
    }
    
    public Connection createConnection() throws SQLException {
        String username = properties.getProperty("database.username");
        String password = properties.getProperty("database.password");
        
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

    public void writeFrame(JFrame frame) {
        properties.setProperty("frame.bounds.x", "" + frame.getBounds().x);
        properties.setProperty("frame.bounds.y", "" + frame.getBounds().y);
        properties.setProperty("frame.bounds.width", "" + frame.getBounds().width);
        properties.setProperty("frame.bounds.height", "" + frame.getBounds().height);
        properties.setProperty("frame.extendedState", "" + frame.getExtendedState());
    }
    
    public void readFrame(JFrame frame) {
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
}
