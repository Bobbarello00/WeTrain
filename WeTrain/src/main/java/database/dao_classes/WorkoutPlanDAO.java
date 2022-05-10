package database.dao_classes;

import database.Queries;
import exception.DBConnectionFailedException;
import model.Trainer;
import model.WorkoutDay;
import model.WorkoutPlan;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkoutPlanDAO {

    public WorkoutPlanDAO() {
    }

    public void saveWorkoutPlan(WorkoutPlan workoutPlan, String athleteFc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = Queries.insertWorkoutPlan(athleteFc)) {
            if(preparedStatement.executeUpdate() == 0){
                System.out.println("Inserimento WorkoutPlan fallito.");
            }
            int idWorkoutPlan;
            try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    idWorkoutPlan = generatedKeys.getInt(1);
                } else {
                    //TODO va bene lanciarla?
                    throw new SQLException();
                }
            }
            for (WorkoutDay workoutDay : workoutPlan.getWorkoutDayList()) {
                new WorkoutDayDAO().saveWorkoutDay(workoutDay, idWorkoutPlan);
            }
            new AthleteDAO().addWorkoutPlan(idWorkoutPlan, athleteFc);
        }
    }

    public WorkoutPlan loadWorkoutPlan(Integer idWorkoutPlan, Trainer trainer) throws SQLException, DBConnectionFailedException {
        WorkoutPlan workoutPlan = new WorkoutPlan(idWorkoutPlan);
        workoutPlan.addAllWorkoutDays(new WorkoutDayDAO().loadAllWorkoutDays(workoutPlan, trainer));
        return workoutPlan;
    }
}

