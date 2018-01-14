package com.mycompany.liveobjects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Configuration {
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
        
        configuration.load(new FileInputStream("config.properties"));
        
        return configuration;
    }
}
