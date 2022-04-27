package database.dao_classes;

import database.DatabaseConnectionSingleton;
import database.Query;
import exception.DBConnectionFailedException;
import model.Exercise;
import model.Trainer;
import model.WorkoutDay;
import model.WorkoutPlan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WorkoutDayDAO {
    Connection conn = DatabaseConnectionSingleton.getInstance().getConn();

    public WorkoutDayDAO() throws DBConnectionFailedException {
    }

    public void saveWorkoutDay(WorkoutDay workoutDay, int idWorkoutPlan) throws SQLException, DBConnectionFailedException {
        try (Statement stmt = conn.createStatement()) {
            int id = Query.insertWorkoutDay(idWorkoutPlan);
            for (Exercise exercise : workoutDay.getExerciseList()){
                new ExerciseDAO().insertExerciseInWorkoutDay(stmt, exercise, id);
            }
        }
    }

    public List<WorkoutDay> loadAllWorkoutDays(WorkoutPlan workoutPlan, Trainer trainer) throws SQLException, DBConnectionFailedException {
        try(Statement stmt = conn.createStatement(); ResultSet rs = Query.loadAllWorkoutDays(stmt, workoutPlan)){
            List<WorkoutDay> myList = new ArrayList<>();
            while(rs.next()){
                WorkoutDay workoutDay = new WorkoutDay(
                        rs.getInt("idWorkoutDay"),
                        rs.getString("Day"),
                        rs.getString("Info"));
                workoutDay.addAllExercise(new ExerciseDAO().loadExerciseInWorkoutPlan(workoutDay, trainer));
                myList.add(workoutDay);
            }
            return myList;
        }
    }
}
