package database.dao_classes;

import database.DatabaseConnectionSingleton;
import database.queries.ExerciseQueries;
import exceptions.DBConnectionFailedException;
import exceptions.DBUnreachableException;
import models.Exercise;
import models.Trainer;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ExerciseDAO {

    private static final String NAME = "Name";
    private static final String INFO = "Info";
    private static final String IDEXERCISE = "idExercise";

    public void insertExerciseInWorkoutDay(Exercise exercise, int workoutDayId) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                ExerciseQueries.INSERT_EXERCISE_IN_WORKOUT_DAY_QUERY)) {
            ExerciseQueries.insertExerciseInWorkoutDay(preparedStatement, exercise.getId(), workoutDayId);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public int saveExercise(Exercise exercise) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                ExerciseQueries.INSERT_EXERCISE_QUERY,
                Statement.RETURN_GENERATED_KEYS); ResultSet generatedKeys = ExerciseQueries.insertExercise(preparedStatement, exercise)){
            if(generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
        return 0;
    }

    public List<Exercise> loadExerciseInWorkoutPlan(int idWorkoutDay, Trainer trainer) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                ExerciseQueries.LOAD_ALL_EXERCISE_IN_WORKOUT_DAYS_QUERY); ResultSet rs = ExerciseQueries.loadAllExerciseInWorkoutDays(preparedStatement, idWorkoutDay)){
            return getExercises(trainer, rs);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public ArrayList<Exercise> loadTrainerExercises(Trainer trainer) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                ExerciseQueries.LOAD_TRAINER_EXERCISES_QUERY); ResultSet rs = ExerciseQueries.loadTrainerExercises(trainer.getFiscalCode(), preparedStatement)){
            return getExercises(trainer, rs);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    @NotNull
    private ArrayList<Exercise> getExercises(Trainer trainer, ResultSet rs) throws SQLException {
        ArrayList<Exercise> exerciseList = new ArrayList<>();
        while(rs.next()){
            exerciseList.add(new Exercise(
                    rs.getInt(IDEXERCISE),
                    rs.getString(NAME),
                    rs.getString(INFO),
                    trainer));
        }
        return exerciseList;
    }

    public void removeExercise(Exercise exercise) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                ExerciseQueries.DELETE_EXERCISE_QUERY)){
            ExerciseQueries.deleteExercise(preparedStatement, exercise.getId());
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }
}
