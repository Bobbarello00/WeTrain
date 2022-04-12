package database.dao_classes;

import database.DatabaseConnectionSingleton;
import database.Query;
import model.Athlete;
import model.WorkoutDay;
import model.WorkoutPlan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WorkoutPlanDAO {
    Connection conn = DatabaseConnectionSingleton.getInstance().getConn();

    public void saveWorkoutPlan(WorkoutPlan workoutPlan) throws SQLException {
        int id = Query.insertWorkoutPlan(workoutPlan);
        for (WorkoutDay workoutDay : workoutPlan.getWorkoutDayList()){
            new WorkoutDayDAO().saveWorkoutDay(workoutDay, id);
        }
    }

    public WorkoutPlan loadWorkoutPlan(Integer idWorkoutPlan, Athlete athlete) throws SQLException {
        WorkoutPlan workoutPlan = new WorkoutPlan(idWorkoutPlan, athlete);
        workoutPlan.addAllWorkoutDays(new WorkoutDayDAO().loadAllWorkoutDays(workoutPlan));
        return workoutPlan;
    }
}

