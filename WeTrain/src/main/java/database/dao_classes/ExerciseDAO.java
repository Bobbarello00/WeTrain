package database.dao_classes;

import database.DatabaseConnectionSingleton;
import database.Queries;
import exception.DBConnectionFailedException;
import model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ExerciseDAO {

    private final Connection conn = DatabaseConnectionSingleton.getInstance().getConn();
    private static final String NAME = "Name";
    private static final String INFO = "Info";
    private static final String TRAINER = "Trainer";
    private static final String IDEXERCISE = "idExercise";

    public ExerciseDAO() throws DBConnectionFailedException {}

    public void insertExerciseInWorkoutDay(Statement stmt, Exercise exercise, int workoutDayId) throws SQLException {
        Queries.insertExerciseInWorkoutDay(stmt, exercise, workoutDayId);
    }

    public void saveExercise(Exercise exercise) throws SQLException {
        try(Statement stmt = conn.createStatement()){
            Queries.insertExercise(stmt, exercise);
        }
    }

    public List<Exercise> loadExerciseInWorkoutPlan(WorkoutDay workoutDay, Trainer trainer) throws SQLException {
        try(Statement stmt = conn.createStatement(); ResultSet rs = Queries.loadAllExerciseInWorkoutDays(stmt, workoutDay)){
            List<Exercise> exerciseList = new ArrayList<>();
            while(rs.next()){
                exerciseList.add(new Exercise(
                        rs.getInt(IDEXERCISE),
                        rs.getString(NAME),
                        rs.getString(INFO),
                        trainer)
                );
            }
            return exerciseList;
        }
    }

    public List<Exercise> loadTrainerExercises(Trainer trainer) throws SQLException {
        try(Statement stmt = conn.createStatement(); ResultSet rs = Queries.loadTrainerExercises(stmt, trainer)){
            List<Exercise> exerciseList = new ArrayList<>();
            while(rs.next()){
                exerciseList.add(new Exercise(
                        rs.getInt(IDEXERCISE),
                        rs.getString(NAME),
                        rs.getString(INFO),
                        trainer));
            }
            return exerciseList;
        }
    }

    public void removeExercise(Exercise exercise) throws SQLException {
        try(Statement stmt = conn.createStatement()){
            Queries.deleteExercise(stmt, exercise);
        }
    }

    public Exercise loadExercise(int id) throws SQLException, DBConnectionFailedException {
        try(Statement stmt = conn.createStatement(); ResultSet rs = Queries.loadExercise(stmt, id)){
            return new Exercise(
                    rs.getInt(IDEXERCISE),
                    rs.getString(NAME),
                    rs.getString(INFO),
                    new TrainerDAO().loadTrainer(rs.getString(TRAINER))
            );
        }
    }
}
