package database.dao_classes;

import database.DatabaseConnectionSingleton;
import database.Query;
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

    public ExerciseDAO() throws DBConnectionFailedException {
    }

    public void insertExerciseInWorkoutDay(Statement stmt, Exercise exercise, int workoutDayId) throws SQLException {
        Query.insertExerciseInWorkoutDay(stmt, exercise, workoutDayId);
    }

    public void saveExercise(Exercise exercise) throws SQLException {
        try(Statement stmt = conn.createStatement()){
            Query.insertExercise(stmt, exercise);
        }
    }

    public List<Exercise> loadExerciseInWorkoutPlan(WorkoutPlan workoutPlan, WorkoutDay workoutDay) throws SQLException, DBConnectionFailedException {
        try(Statement stmt = conn.createStatement(); ResultSet rs = Query.loadAllExerciseInWorkoutDays(stmt, workoutDay)){
            List<Exercise> exerciseList = new ArrayList<>();
            while(rs.next()){
                exerciseList.add(new Exercise(
                        rs.getInt(IDEXERCISE),
                        rs.getString(NAME),
                        rs.getString(INFO),
                        new TrainerDAO().loadTrainer(workoutPlan.getAthlete().getTrainer().getFiscalCode())
                ));
            }
            return exerciseList;
        }
    }

    public ExerciseCatalogue loadTrainerExercises(Trainer trainer) throws SQLException {
        try(Statement stmt = conn.createStatement(); ResultSet rs = Query.loadTrainerExercises(stmt, trainer)){
            ExerciseCatalogue newCatalogue = new ExerciseCatalogue();
            while(rs.next()){
                newCatalogue.addExercise(new Exercise(
                        rs.getInt(IDEXERCISE),
                        rs.getString(NAME),
                        rs.getString(INFO),
                        trainer));
            }
            return newCatalogue;
        }
    }

    public Exercise loadExercise(int id) throws SQLException, DBConnectionFailedException {
        try(Statement stmt = conn.createStatement(); ResultSet rs = Query.loadExercise(stmt, id)){
            return new Exercise(
                    rs.getInt(IDEXERCISE),
                    rs.getString(NAME),
                    rs.getString(INFO),
                    new TrainerDAO().loadTrainer(rs.getString(TRAINER))
            );
        }
    }
}
