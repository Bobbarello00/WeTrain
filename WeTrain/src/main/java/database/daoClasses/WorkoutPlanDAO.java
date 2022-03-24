package database.daoClasses;

import database.DatabaseConnection;
import database.Query;
import model.WorkoutDay;
import model.WorkoutPlan;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class WorkoutPlanDAO {
    Connection conn = DatabaseConnection.getInstance().conn;
    public void saveWorkoutPlan(WorkoutPlan workoutPlan) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            int id = Query.insertWorkoutPlan(stmt, workoutPlan);
            workoutPlan.setId(id);
            for (WorkoutDay workoutDay : workoutPlan.getWorkoutDayList()){
                WorkoutDayDAO.saveWorkoutDay(workoutDay, id);
            }
        }
    }
}
