package database.Queries;

import models.Exercise;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExerciseQueries extends Queries{

    public static final String INSERT_EXERCISE_IN_WORKOUT_DAY_QUERY = "INSERT INTO mydb.Contains (WorkoutDay, Exercise) " +
            "VALUES (?, ?)";

    public static final String LOAD_ALL_EXERCISE_IN_WORKOUT_DAYS_QUERY = "SELECT Exercise.* " +
            "FROM mydb.Contains join mydb.Exercise on Contains.Exercise = Exercise.idExercise " +
            "WHERE Contains.WorkoutDay = ?";
    public static final String LOAD_TRAINER_EXERCISES_QUERY = "SELECT Exercise.* " +
            "FROM mydb.Exercise " +
            WHERE_TRAINER;

    public static final String INSERT_EXERCISE_QUERY = "INSERT INTO mydb.Exercise (Name, Info, Trainer) " +
            "VALUES (?, ?, ?)";

    public static final String DELETE_EXERCISE_QUERY = "DELETE FROM mydb.Exercise " +
            "WHERE idExercise = ?";

    public static ResultSet loadTrainerExercises(String trainerFc, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, trainerFc);
        return preparedStatement.executeQuery();
    }

    public static ResultSet insertExercise(PreparedStatement preparedStatement, Exercise exercise) throws SQLException {
        preparedStatement.setString(1, exercise.getName());
        preparedStatement.setString(2, exercise.getInfo());
        preparedStatement.setString(3, exercise.getTrainer().getFiscalCode());
        preparedStatement.executeUpdate();
        return preparedStatement.getGeneratedKeys();
    }

    public static void deleteExercise(PreparedStatement preparedStatement, int idExercise) throws SQLException {
        preparedStatement.setInt(1, idExercise);
        preparedStatement.executeUpdate();
    }

    public static void insertExerciseInWorkoutDay(PreparedStatement preparedStatement, int idExercise, int workoutDayKey) throws SQLException {
        preparedStatement.setInt(1, workoutDayKey);
        preparedStatement.setInt(2, idExercise);
        preparedStatement.executeUpdate();
    }

    public static ResultSet loadAllExerciseInWorkoutDays(PreparedStatement preparedStatement, int idWorkoutDay) throws SQLException {
        preparedStatement.setInt(1, idWorkoutDay);
        return preparedStatement.executeQuery();
    }
}