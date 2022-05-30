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
        int idWorkoutDay;
        try (ResultSet generatedKeys = Queries.insertWorkoutDay(idWorkoutPlan, workoutDay.getDay())) {
            if (generatedKeys.next()) {
                idWorkoutDay = generatedKeys.getInt(1);
            } else {
                throw new NoGeneratedKeyException();
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
        try(ResultSet rs = Queries.loadAllWorkoutDays(workoutPlan.getId())){
            List<WorkoutDay> myList = new ArrayList<>();
            while(rs.next()){
                WorkoutDay workoutDay = new WorkoutDay(
                        rs.getInt("idWorkoutDay"),
                        rs.getString("Day"),
                        new ExerciseDAO().loadExerciseInWorkoutPlan(rs.getInt("idWorkoutDay"), trainer)
                );
                myList.add(workoutDay);
            }
            return myList;
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }
}
