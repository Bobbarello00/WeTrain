package database.dao_classes;

import database.DatabaseConnectionSingleton;
import database.Query;
import model.Trainer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TrainerDAO {
    Connection conn = DatabaseConnectionSingleton.getInstance().getConn();

    public void saveTrainer(Trainer trainer) throws SQLException {
        try(Statement stmt = conn.createStatement();){
            Query.insertTrainer(stmt, trainer);
        }catch(SQLException sqlEx){
            sqlEx.printStackTrace();
        }
    }

    public Trainer loadTrainer(String fc) throws SQLException {
        try (Statement stmt = conn.createStatement(); ResultSet rs = Query.loadTrainer(stmt, fc)) {
            if (rs.next()) {
                return new Trainer(rs.getString("Name"),
                        rs.getString("Surname"),
                        rs.getString("Username"),
                        rs.getDate("Birth").toLocalDate(),
                        rs.getString("FC"),
                        rs.getString("Gender").charAt(0),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getString("Iban")
                );
            } else {
                return null;
            }
        }
    }
}
