package database.dao_classes;

import database.DatabaseConnectionSingleton;
import database.Query;
import exception.DBConnectionFailedException;
import model.Athlete;
import model.WorkoutDay;
import model.WorkoutPlan;

import java.sql.Connection;
import java.sql.SQLException;

public class WorkoutPlanDAO {
    Connection conn = DatabaseConnectionSingleton.getInstance().getConn();

    public WorkoutPlanDAO() throws DBConnectionFailedException {
    }

    public void saveWorkoutPlan(WorkoutPlan workoutPlan) throws SQLException, DBConnectionFailedException {
        int id = Query.insertWorkoutPlan(workoutPlan);
        for (WorkoutDay workoutDay : workoutPlan.getWorkoutDayList()){
            new WorkoutDayDAO().saveWorkoutDay(workoutDay, id);
        }
    }

    public WorkoutPlan loadWorkoutPlan(Integer idWorkoutPlan, Athlete athlete) throws SQLException, DBConnectionFailedException {
        WorkoutPlan workoutPlan = new WorkoutPlan(idWorkoutPlan, athlete);
        workoutPlan.addAllWorkoutDays(new WorkoutDayDAO().loadAllWorkoutDays(workoutPlan));
        return workoutPlan;
    }
}

