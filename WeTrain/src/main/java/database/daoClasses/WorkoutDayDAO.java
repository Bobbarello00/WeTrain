package database.daoClasses;

import database.DatabaseConnection;

import java.sql.Connection;

public class WorkoutDayDAO {
    Connection conn = DatabaseConnection.getInstance().conn;

}
