package database.dao_classes;

import database.Queries;
import exception.DBConnectionFailedException;
import model.Exercise;
import model.Trainer;
import model.WorkoutDay;
import model.WorkoutPlan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkoutDayDAO {

    public WorkoutDayDAO() {
    }

    public void saveWorkoutDay(WorkoutDay workoutDay, int idWorkoutPlan) throws SQLException, DBConnectionFailedException {
            int id = Queries.insertWorkoutDay(idWorkoutPlan, workoutDay.getDay());
            for (Exercise exercise : workoutDay.getExerciseList()){
                new ExerciseDAO().insertExerciseInWorkoutDay(exercise, id);
            }
    }

    public List<WorkoutDay> loadAllWorkoutDays(WorkoutPlan workoutPlan, Trainer trainer) throws SQLException, DBConnectionFailedException {
        try(ResultSet rs = Queries.loadAllWorkoutDays(workoutPlan.getId())){
            List<WorkoutDay> myList = new ArrayList<>();
            while(rs.next()){
                WorkoutDay workoutDay = new WorkoutDay(
                        rs.getInt("idWorkoutDay"),
                        rs.getString("Day"));
                workoutDay.addAllExercise(new ExerciseDAO().loadExerciseInWorkoutPlan(workoutDay, trainer));
                myList.add(workoutDay);
            }
            return myList;
        }
    }
}
