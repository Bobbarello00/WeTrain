package database.daoClasses;

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
    Connection conn = DatabaseConnection.getInstance().conn;

    public void saveExercise(Exercise exercise) throws SQLException {
        try(Statement stmt = conn.createStatement()){
            Query.insertExercise(stmt, exercise);
        }catch(SQLException sqlEx){
            System.out.println(sqlEx);
        }
    }
    public ExerciseCatalogue loadTrainerExercises(Trainer trainer) throws SQLException {
        try(Statement stmt = conn.createStatement(); ResultSet rs = Query.loadTrainerExercises(stmt, trainer)){
            return ExerciseCatalogue();
        }
    }
}
