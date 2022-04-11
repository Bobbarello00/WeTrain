package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnectionSingleton {

    private static final DatabaseConnectionSingleton dbConn = new DatabaseConnectionSingleton();
    private Connection conn;

    private DatabaseConnectionSingleton() {
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://wetraindb.ckbiquzyonvq.us-east-1.rds.amazonaws.com:3306/", "admin", "WeTrain!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Connection getConn(){
        return conn;
    }

    public static DatabaseConnectionSingleton getInstance(){
        if(dbConn == null){
            System.out.println("Error: dbConn is null");
        }
        return dbConn;
    }
}
