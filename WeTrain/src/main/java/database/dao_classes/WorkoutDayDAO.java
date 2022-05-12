package database.dao_classes;

import database.Queries;
import exception.DBConnectionFailedException;
import exception.DBUnreachableException;
import exception.runtime_exception.NoGeneratedKeyException;
import model.Exercise;
import model.Trainer;
import model.WorkoutDay;
import model.WorkoutPlan;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkoutDayDAO {

    public void saveWorkoutDay(WorkoutDay workoutDay, int idWorkoutPlan) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = Queries.insertWorkoutDay(idWorkoutPlan, workoutDay.getDay())) {
            int idWorkoutDay;
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    idWorkoutDay = generatedKeys.getInt(1);
                } else {
                    throw new NoGeneratedKeyException();
                }
            }
            for (Exercise exercise : workoutDay.getExerciseList()){
                new ExerciseDAO().insertExerciseInWorkoutDay(exercise, idWorkoutDay);
            }
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public List<WorkoutDay> loadAllWorkoutDays(WorkoutPlan workoutPlan, Trainer trainer) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = Queries.loadAllWorkoutDays(workoutPlan.getId()); ResultSet rs = preparedStatement.executeQuery()){
            List<WorkoutDay> myList = new ArrayList<>();
            while(rs.next()){
                WorkoutDay workoutDay = new WorkoutDay(
                        rs.getInt("idWorkoutDay"),
                        rs.getString("Day"));
                workoutDay.addAllExercise(new ExerciseDAO().loadExerciseInWorkoutPlan(workoutDay, trainer));
                myList.add(workoutDay);
            }
            return myList;
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }
}
