package database;

import exception.DBConnectionFailedException;

import java.io.FileInputStream;
import com.mysql.cj.exceptions.CJCommunicationsException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionSingleton {

    private static DatabaseConnectionSingleton dbConn;
    private Connection conn;

    private DatabaseConnectionSingleton() throws DBConnectionFailedException {
        try(FileInputStream fileInputStream = new FileInputStream("src/main/java/database/db.properties")) {

            Properties prop = new Properties();
            prop.load(fileInputStream);

            String username = prop.getProperty("dbUserName");
            String password = prop.getProperty("dbPassword");
            String url = prop.getProperty("dbUrl");

            conn = DriverManager.getConnection(
                    url,
                    username,
                    password);
        } catch (CJCommunicationsException | IOException | SQLException e) {
            this.conn = null;
            throw new DBConnectionFailedException();
        }
    }

    public Connection getConn() throws DBConnectionFailedException {
        if(this.conn == null){
            dbConn = new DatabaseConnectionSingleton();
        }
        return conn;
    }

    public static DatabaseConnectionSingleton getInstance() throws DBConnectionFailedException {
        if(dbConn == null){
            dbConn = new DatabaseConnectionSingleton();
        }
        return dbConn;
    }

    public static void deleteInstance() {
        dbConn = null;
    }
}
