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
        try (Statement stmt = conn.createStatement()) {     //stmt può essere tolto
            int id = Query.insertWorkoutPlan(workoutPlan);
            for (WorkoutDay workoutDay : workoutPlan.getWorkoutDayList()){
                new WorkoutDayDAO().saveWorkoutDay(workoutDay, id);
            }
        }
    }

    public WorkoutPlan loadWorkoutPlan(Athlete athlete) throws SQLException {
        try (Statement stmt = conn.createStatement(); ResultSet rs = Query.loadWorkoutPlan(stmt,athlete)){
            if(rs.next()){
                WorkoutDayDAO workoutDayDAO = new WorkoutDayDAO();
                int idWorkoutPlan = rs.getInt("idWorkoutPlan");
                WorkoutPlan workoutPlan = new WorkoutPlan(idWorkoutPlan);
                workoutPlan.addAllWorkoutDays(workoutDayDAO.loadAllWorkoutDays(workoutPlan));
                return workoutPlan;
            }else {
                return null;
            }
        }
    }
}

