package database.dao_classes;

import database.Queries;
import exception.DBConnectionFailedException;
import exception.DBUnreachableException;
import model.Exercise;
import model.Trainer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExerciseDAO {

    private static final String NAME = "Name";
    private static final String INFO = "Info";
    private static final String TRAINER = "Trainer";
    private static final String IDEXERCISE = "idExercise";

    public void insertExerciseInWorkoutDay(Exercise exercise, int workoutDayId) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = Queries.insertExerciseInWorkoutDay(exercise.getId(), workoutDayId)){
            preparedStatement.executeUpdate();
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public void saveExercise(Exercise exercise) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = Queries.insertExercise(exercise)) {
            preparedStatement.executeUpdate();
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public List<Exercise> loadExerciseInWorkoutPlan(int idWorkoutDay, Trainer trainer) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = Queries.loadAllExerciseInWorkoutDays(idWorkoutDay); ResultSet rs = preparedStatement.executeQuery()){
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
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public List<Exercise> loadTrainerExercises(Trainer trainer) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = Queries.loadTrainerExercises(trainer.getFiscalCode());
            ResultSet rs = preparedStatement.executeQuery()){
            List<Exercise> exerciseList = new ArrayList<>();
            while(rs.next()){
                exerciseList.add(new Exercise(
                        rs.getInt(IDEXERCISE),
                        rs.getString(NAME),
                        rs.getString(INFO),
                        trainer));
            }
            return exerciseList;
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public void removeExercise(Exercise exercise) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = Queries.deleteExercise(exercise.getId())){
            preparedStatement.executeUpdate();
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public Exercise loadExercise(int id) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = Queries.loadExercise(id); ResultSet rs = preparedStatement.executeQuery()){
            return new Exercise(
                    rs.getInt(IDEXERCISE),
                    rs.getString(NAME),
                    rs.getString(INFO),
                    new TrainerDAO().loadTrainer(rs.getString(TRAINER))
            );
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }
}
