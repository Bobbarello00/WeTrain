package database.dao_classes;

import database.Queries;
import exception.DBConnectionFailedException;
import model.Exercise;
import model.Trainer;
import model.WorkoutDay;

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
        Queries.insertExerciseInWorkoutDay(exercise.getId(), workoutDayId);
    }

    public void saveExercise(Exercise exercise) throws SQLException, DBConnectionFailedException {
        Queries.insertExercise(exercise);
    }

    public List<Exercise> loadExerciseInWorkoutPlan(WorkoutDay workoutDay, Trainer trainer) throws SQLException, DBConnectionFailedException {
        try(ResultSet rs = Queries.loadAllExerciseInWorkoutDays(workoutDay.getId())){
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
        try(ResultSet rs = Queries.loadTrainerExercises(trainer.getFiscalCode())){
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
        Queries.deleteExercise(exercise.getId());
    }

    public Exercise loadExercise(int id) throws SQLException, DBConnectionFailedException {
        try(ResultSet rs = Queries.loadExercise(id)){
            return new Exercise(
                    rs.getInt(IDEXERCISE),
                    rs.getString(NAME),
                    rs.getString(INFO),
                    new TrainerDAO().loadTrainer(rs.getString(TRAINER))
            );
        }
    }
}
