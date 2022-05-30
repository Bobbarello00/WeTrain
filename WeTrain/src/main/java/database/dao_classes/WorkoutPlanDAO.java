package database.dao_classes;

import database.DatabaseConnectionSingleton;
import database.Queries;
import exception.DBConnectionFailedException;
import exception.DBUnreachableException;
import exception.runtime_exception.NoGeneratedKeyException;
import model.Trainer;
import model.WorkoutDay;
import model.WorkoutPlan;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WorkoutPlanDAO {

    public void saveWorkoutPlan(WorkoutPlan workoutPlan, String athleteFc) throws SQLException, DBUnreachableException {
        int idWorkoutPlan;
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                Queries.INSERT_WORKOUT_PLAN_QUERY,
                Statement.RETURN_GENERATED_KEYS); ResultSet generatedKeys = Queries.insertWorkoutPlan(preparedStatement, athleteFc)) {
            if (generatedKeys.next()) {
                idWorkoutPlan = generatedKeys.getInt(1);
            } else {
                throw new NoGeneratedKeyException();
            }
            for (WorkoutDay workoutDay : workoutPlan.getWorkoutDayList()) {
                new WorkoutDayDAO().saveWorkoutDay(workoutDay, idWorkoutPlan);
            }
            new AthleteDAO().addWorkoutPlan(idWorkoutPlan, athleteFc);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public WorkoutPlan loadWorkoutPlan(Integer idWorkoutPlan, Trainer trainer) throws SQLException, DBUnreachableException {
        WorkoutPlan workoutPlan = new WorkoutPlan(idWorkoutPlan);
        workoutPlan.addAllWorkoutDays(new WorkoutDayDAO().loadAllWorkoutDays(workoutPlan, trainer));
        return workoutPlan;
    }
}

