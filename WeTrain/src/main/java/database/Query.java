package database;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Query {


    public ResultSet query() throws SQLException {
        Connection conn = DatabaseConnection.getInstance().conn;
        Statement stmt = conn.createStatement();
        return stmt.executeQuery("show databases;");
    }

}

