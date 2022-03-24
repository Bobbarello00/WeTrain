package database.daoClasses;

import database.DatabaseConnection;
import database.Query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class WorkoutDayDAO {
    Connection conn = DatabaseConnection.getInstance().conn;
    try(Statement stmt = conn.createStatement(); ResultSet rs = Query.)
}
