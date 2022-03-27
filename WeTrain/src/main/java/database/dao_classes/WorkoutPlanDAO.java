package database.dao_classes;

import database.DatabaseConnection;
import database.Query;
import model.WorkoutDay;
import model.WorkoutPlan;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class WorkoutPlanDAO {
    Connection conn = DatabaseConnection.getInstance().getConn();
    public void saveWorkoutPlan(WorkoutPlan workoutPlan) throws SQLException {
        try (Statement stmt = conn.createStatement()) {     //stmt può essere tolto
            int id = Query.insertWorkoutPlan(workoutPlan);
            for (WorkoutDay workoutDay : workoutPlan.getWorkoutDayList()){
                WorkoutDayDAO.saveWorkoutDay(workoutDay, id);
            }
        }
    }
}
