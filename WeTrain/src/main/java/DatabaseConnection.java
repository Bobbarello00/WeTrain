import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseConnection {

    public static void main(String[] args) {
        try
        {
            Class.forName("src/main/connector_J/mysql-connector-java-8.0.28.jar");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://wetraindb.ckbiquzyonvq.us-east-1.rds.amazonaws.com:3306/","admin","WeTrain!");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("show databases;");
            System.out.println("Connected");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
