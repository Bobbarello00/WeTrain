package database.daoClasses;

import database.DatabaseConnection;
import database.Query;
import model.Exercise;
import model.WorkoutDay;

import java.sql.SQLException;
import java.sql.Statement;

public class WorkoutDayDAO {

    public static void saveWorkoutDay(WorkoutDay workoutDay, int idWorkoutPlan) throws SQLException {
        try (Statement stmt = DatabaseConnection.getInstance().conn.createStatement()) {
            int id = Query.insertWorkoutDay(stmt, workoutDay, idWorkoutPlan);
            for (Exercise exercise : workoutDay.getListExercise()){
                ExerciseDAO.insertExerciseInWorkoutDay(stmt, exercise, id);
            }
        }
    }

}
