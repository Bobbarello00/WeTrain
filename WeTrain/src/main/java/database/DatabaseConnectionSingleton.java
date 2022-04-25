package database;

import exception.DBConnectionFailedException;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConnectionSingleton {

    private static final DatabaseConnectionSingleton dbConn = new DatabaseConnectionSingleton();
    private Connection conn;

    private DatabaseConnectionSingleton() {
        try(FileInputStream fileInputStream = new FileInputStream("src/main/java/database/db.properties")) {

            Properties prop = new Properties() ;
            prop.load(fileInputStream);

            String username = prop.getProperty("dbUserName");
            String password = prop.getProperty("dbPassword");
            String url = prop.getProperty("dbUrl");

            conn = DriverManager.getConnection(
                    url,
                    username,
                    password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConn() throws DBConnectionFailedException {
        if(this.conn == null){
            throw new DBConnectionFailedException();
        }
        return conn;
    }

    public static DatabaseConnectionSingleton getInstance(){
        return dbConn;
    }
}
