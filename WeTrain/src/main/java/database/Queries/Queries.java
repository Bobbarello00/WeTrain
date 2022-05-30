package database.Queries;

import java.sql.*;
import java.time.LocalDateTime;

public abstract class Queries {

    protected static final String SELECT_ALL = "SELECT * ";
    protected static final String WHERE_USER = "WHERE User = ?";
    protected static final String LIMIT_10 = "LIMIT 10";
    protected static final String LIMIT_30 = "LIMIT 30";
    protected static final String UPDATE_MYDB_ATHLETE = "UPDATE mydb.Athlete ";
    protected static final String FROM_MYDB_COURSE = "FROM mydb.Course ";
    protected static final String WHERE_ID_COURSE = "WHERE idCourse = ?";
    protected static final String UPDATE_MYDB_COURSE = "UPDATE mydb.Course ";
    protected static final String WHERE_COURSE = "WHERE Course = ?";
    protected static final String FROM_MYDB_ATHLETE = "FROM mydb.Athlete ";
    protected static final String WHERE_TRAINER = "WHERE Trainer = ?";
    protected static final String FROM_MYDB_LESSON = "FROM mydb.Lesson ";

    public static final String INSERT_REQUEST_QUERY = "INSERT INTO mydb.Request (RequestDate, Info, Athlete, Trainer) " +
            "VALUES (?, ?, ?, ?)";
    public static void insertRequest(PreparedStatement preparedStatement, LocalDateTime requestDate, String info, String athleteFc, String trainerFc) throws SQLException {
        preparedStatement.setTimestamp(1, Timestamp.valueOf(requestDate));
        preparedStatement.setString(2, info);
        preparedStatement.setString(3, athleteFc);
        preparedStatement.setString(4, trainerFc);
        preparedStatement.executeUpdate();
    }

    public static final String LOAD_TRAINER_REQUESTS_QUERY = "SELECT Request.* " +
            "FROM mydb.Request join mydb.Trainer on Request.Trainer = Trainer.User " +
            "WHERE Trainer = ? " +
            LIMIT_30;
    public static ResultSet loadTrainerRequests(String trainerFc, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, trainerFc);
        return preparedStatement.executeQuery();
    }

    public static final String DELETE_REQUEST_QUERY = "DELETE FROM mydb.Request " +
            "WHERE idRequest = ?";
    public static void deleteRequest(PreparedStatement preparedStatement, int idRequest) throws SQLException {
        preparedStatement.setInt(1, idRequest);
        preparedStatement.executeUpdate();
    }



    public static final String INSERT_WORKOUT_DAY_QUERY = "INSERT INTO mydb.WorkoutDay (WorkoutPlan, Day) VALUES (?, ?)";
    public static ResultSet insertWorkoutDay(PreparedStatement preparedStatement, int workoutPlanKey, String day) throws SQLException {
        preparedStatement.setInt(1, workoutPlanKey);
        preparedStatement.setString(2, day);
        preparedStatement.executeUpdate();
        return preparedStatement.getGeneratedKeys();
    }

    public static final String LOAD_ALL_WORKOUT_DAYS_QUERY = "SELECT WorkoutDay.* " +
            "FROM mydb.WorkoutDay " +
            "WHERE WorkoutDay.WorkoutPlan = ?";
    public static ResultSet loadAllWorkoutDays(PreparedStatement preparedStatement, int idWorkoutPlan) throws SQLException {
        preparedStatement.setInt(1, idWorkoutPlan);
        return preparedStatement.executeQuery();
    }

    public static final String INSERT_WORKOUT_PLAN_QUERY = "INSERT INTO mydb.WorkoutPlan (Athlete) VALUES (?)";
    public static ResultSet insertWorkoutPlan(PreparedStatement preparedStatement, String athleteFc) throws SQLException {
        preparedStatement.setString(1, athleteFc);
        preparedStatement.executeUpdate();
        return preparedStatement.getGeneratedKeys();
    }
}
