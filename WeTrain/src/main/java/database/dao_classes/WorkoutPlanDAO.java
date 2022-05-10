package database.dao_classes;

import database.Queries;
import exception.DBConnectionFailedException;
import model.Trainer;
import model.WorkoutDay;
import model.WorkoutPlan;

import java.sql.SQLException;

public class WorkoutPlanDAO {

    public WorkoutPlanDAO() {
    }

    public void saveWorkoutPlan(WorkoutPlan workoutPlan, String athleteFc) throws SQLException, DBConnectionFailedException {
        int idWorkoutPlan = Queries.insertWorkoutPlan(athleteFc);
        for (WorkoutDay workoutDay : workoutPlan.getWorkoutDayList()){
            new WorkoutDayDAO().saveWorkoutDay(workoutDay, idWorkoutPlan);
        }
        new AthleteDAO().addWorkoutPlan(idWorkoutPlan, athleteFc);
    }

    public WorkoutPlan loadWorkoutPlan(Integer idWorkoutPlan, Trainer trainer) throws SQLException, DBConnectionFailedException {
        WorkoutPlan workoutPlan = new WorkoutPlan(idWorkoutPlan);
        workoutPlan.addAllWorkoutDays(new WorkoutDayDAO().loadAllWorkoutDays(workoutPlan, trainer));
        return workoutPlan;
    }
}

