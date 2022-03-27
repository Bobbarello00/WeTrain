package database.daoClasses;

import database.DatabaseConnection;
import database.Query;
import model.Trainer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TrainerDAO {
    Connection conn = DatabaseConnection.getInstance().conn;

    public Trainer loadTrainer(String fc){
        try(Statement stmt =conn.createStatement(); ResultSet rs = Query.loadTrainer(stmt, fc)){
            if(rs.next()){
                return new Trainer(rs.getString("Name"),rs.getString("Surname"),
                        rs.getDate("Birth").toLocalDate(),rs.getString("FC"),
                        rs.getString("Email"),rs.getString("Iban"));
            }else{
                throw new Exception("Trainer not found!");
            }
        } catch (Exception sqlEx) {
            sqlEx.printStackTrace();
        }
        return null;
    }
}
