package database.dao_classes;

import database.DatabaseConnectionSingleton;
import database.Query;
import exception.DBConnectionFailedException;
import model.Athlete;
import model.Trainer;
import model.WorkoutDay;
import model.WorkoutPlan;

import java.sql.Connection;
import java.sql.SQLException;

public class WorkoutPlanDAO {
    Connection conn = DatabaseConnectionSingleton.getInstance().getConn();

    public WorkoutPlanDAO() throws DBConnectionFailedException {
    }

    public void saveWorkoutPlan(WorkoutPlan workoutPlan, String athleteFc) throws SQLException, DBConnectionFailedException {
        int id = Query.insertWorkoutPlan(athleteFc);
        for (WorkoutDay workoutDay : workoutPlan.getWorkoutDayList()){
            new WorkoutDayDAO().saveWorkoutDay(workoutDay, id);
        }
        new AthleteDAO().addWorkoutPlan(id, athleteFc);
    }

    public WorkoutPlan loadWorkoutPlan(Integer idWorkoutPlan, Trainer trainer) throws SQLException, DBConnectionFailedException {
        WorkoutPlan workoutPlan = new WorkoutPlan(idWorkoutPlan);
        workoutPlan.addAllWorkoutDays(new WorkoutDayDAO().loadAllWorkoutDays(workoutPlan, trainer));
        return workoutPlan;
    }
}

