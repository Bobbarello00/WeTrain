package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static final DatabaseConnection dbConn = new DatabaseConnection();
    public Connection conn;

    private DatabaseConnection() {
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://wetraindb.ckbiquzyonvq.us-east-1.rds.amazonaws.com:3306/", "admin", "WeTrain!");
            System.out.println("Connected");
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public static DatabaseConnection getInstance(){
        if(dbConn == null){
            System.out.println("Error: dbConn is null");
        }
        return dbConn;
    }
}
