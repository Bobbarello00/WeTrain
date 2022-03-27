package database.dao_classes;

import database.DatabaseConnection;
import database.Query;
import model.Exercise;
import model.ExerciseCatalogue;
import model.Trainer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExerciseDAO {
    Connection conn = DatabaseConnection.getInstance().getConn();

    public static void insertExerciseInWorkoutDay(Statement stmt, Exercise exercise, int workoutDayId) throws SQLException {
        Query.insertExerciseInWorkoutDay(stmt, exercise, workoutDayId);
    }

    public void saveExercise(Exercise exercise) throws SQLException {
        try(Statement stmt = conn.createStatement()){
            Query.insertExercise(stmt, exercise);
        }catch(SQLException sqlEx){
            sqlEx.printStackTrace();
        }
    }

    public ExerciseCatalogue loadTrainerExercises(Trainer trainer) throws SQLException {
        try(Statement stmt = conn.createStatement(); ResultSet rs = Query.loadTrainerExercises(stmt, trainer)){
            ExerciseCatalogue newCatalogue = new ExerciseCatalogue();
            while(rs.next()){
                newCatalogue.addExercise(new Exercise(rs.getString("Name"), rs.getString("Info"), rs.getInt("idExercise"), trainer));
            }
            return newCatalogue;
        }
    }

    public Exercise loadExercise(int id) throws SQLException {
        try(Statement stmt = conn.createStatement(); ResultSet rs = Query.loadExercise(stmt, id)){
            return new Exercise(rs.getString("Name"), rs.getString("Info"), rs.getInt("idExercise"));
        }
    }
}
