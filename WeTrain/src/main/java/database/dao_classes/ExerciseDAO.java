package database.dao_classes;

import database.Queries;
import exception.DBConnectionFailedException;
import model.Exercise;
import model.Trainer;
import model.WorkoutDay;

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

    public ExerciseDAO() {}

    public void insertExerciseInWorkoutDay(Exercise exercise, int workoutDayId) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = Queries.insertExerciseInWorkoutDay(exercise.getId(), workoutDayId)){
            preparedStatement.executeUpdate();
        }
    }

    public void saveExercise(Exercise exercise) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = Queries.insertExercise(exercise)) {
            preparedStatement.executeUpdate();
        }
    }

    public List<Exercise> loadExerciseInWorkoutPlan(WorkoutDay workoutDay, Trainer trainer) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = Queries.loadAllExerciseInWorkoutDays(workoutDay.getId()); ResultSet rs = preparedStatement.executeQuery()){
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

    public List<Exercise> loadTrainerExercises(Trainer trainer) throws SQLException, DBConnectionFailedException {
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
        }
    }

    public void removeExercise(Exercise exercise) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = Queries.deleteExercise(exercise.getId())){
            preparedStatement.executeUpdate();
        }
    }

    public Exercise loadExercise(int id) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = Queries.loadExercise(id); ResultSet rs = preparedStatement.executeQuery()){
            return new Exercise(
                    rs.getInt(IDEXERCISE),
                    rs.getString(NAME),
                    rs.getString(INFO),
                    new TrainerDAO().loadTrainer(rs.getString(TRAINER))
            );
        }
    }
}
