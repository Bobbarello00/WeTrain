package database.dao_classes;

import database.DatabaseConnectionSingleton;
import database.Queries;
import exception.DBConnectionFailedException;
import exception.DBUnreachableException;
import model.Exercise;
import model.Trainer;
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
                Queries.INSERT_EXERCISE_IN_WORKOUT_DAY_QUERY)) {
            Queries.insertExerciseInWorkoutDay(preparedStatement, exercise.getId(), workoutDayId);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public int saveExercise(Exercise exercise) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                Queries.INSERT_EXERCISE_QUERY,
                Statement.RETURN_GENERATED_KEYS); ResultSet generatedKeys = Queries.insertExercise(preparedStatement, exercise)){
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
                Queries.LOAD_ALL_EXERCISE_IN_WORKOUT_DAYS_QUERY); ResultSet rs = Queries.loadAllExerciseInWorkoutDays(preparedStatement, idWorkoutDay)){
            return getExercises(trainer, rs);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public List<Exercise> loadTrainerExercises(Trainer trainer) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                Queries.LOAD_TRAINER_EXERCISES_QUERY); ResultSet rs = Queries.loadTrainerExercises(preparedStatement, trainer.getFiscalCode())){
            return getExercises(trainer, rs);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    @NotNull
    private List<Exercise> getExercises(Trainer trainer, ResultSet rs) throws SQLException {
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

    public void removeExercise(Exercise exercise) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                Queries.DELETE_EXERCISE_QUERY)){
            Queries.deleteExercise(preparedStatement, exercise.getId());
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }
}
