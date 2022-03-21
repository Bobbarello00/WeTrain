package database;

import model.Athlete;

import java.sql.*;

public class Query {

    //TODO gestione duplicate record
    public static int insertAthlete(Athlete athlete) throws SQLException {
        Connection conn = DatabaseConnection.getInstance().conn;
        Statement stmt = conn.createStatement();
        return stmt.executeUpdate(String.format("INSERT INTO mydb.Athlete (FC, Name, Surname, Birth, Email) VALUES ('%s', '%s', '%s', '%s', '%s');", athlete.getFiscalCode(), athlete.getName(), athlete.getSurname(), Date.valueOf(athlete.getDateOfBirth()), athlete.getEmail()));
    }

}

