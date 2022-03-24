package database.daoClasses;

import database.DatabaseConnection;
import database.Query;
import model.Exercise;
import model.WorkoutDay;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WorkoutDayDAO {
    Connection conn = DatabaseConnection.getInstance().conn;
    public void saveWorkoutDay(WorkoutDay workoutDay, int idWorkoutPlan) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            int id = Query.insertWorkoutDay(stmt, workoutDay, idWorkoutPlan);
            workoutDay.setId(id);
            for (Exercise exercise : workoutDay.getListExercise()){
                ExerciseDAO.insertExerciseInWorkoutDay(stmt, exercise, id);
            }
        }
    }
}
