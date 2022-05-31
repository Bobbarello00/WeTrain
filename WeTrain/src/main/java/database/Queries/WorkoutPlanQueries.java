package database.queries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkoutPlanQueries extends Queries{

    private WorkoutPlanQueries() {}

    public static final String INSERT_WORKOUT_PLAN_QUERY = "INSERT INTO mydb.WorkoutPlan (Athlete) VALUES (?)";
    public static ResultSet insertWorkoutPlan(PreparedStatement preparedStatement, String athleteFc) throws SQLException {
        preparedStatement.setString(1, athleteFc);
        preparedStatement.executeUpdate();
        return preparedStatement.getGeneratedKeys();
    }
}