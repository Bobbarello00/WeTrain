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
        }
    }

    public Trainer loadTrainer(String fc) throws SQLException {
        try(Statement stmt = conn.createStatement(); ResultSet rs = Query.loadUser(stmt, fc)) {
            if (rs.next()) {
                Trainer trainer = new Trainer(rs.getString("Name"),
                    rs.getString("Surname"),
                    rs.getString("Username"),
                    rs.getDate("Birth").toLocalDate(),
                    rs.getString("FC"),
                    rs.getString("Gender").charAt(0),
                    rs.getString("Email"),
                    rs.getString("Password")
                );
                try(ResultSet rs1 = Query.loadTrainer(stmt, fc)) {
                    if (rs1.next()) {
                        trainer.setIban(rs1.getString("Iban"));
                        return trainer;
                    } else {
                        return null;
                    }
                }
            } else {
                return null;
            }
        }
    }
}
