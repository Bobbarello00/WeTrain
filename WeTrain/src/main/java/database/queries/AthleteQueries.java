package database.queries;

import models.Athlete;
import models.record.Card;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AthleteQueries extends Queries {

    private AthleteQueries() {}

    public static final String LOAD_ATHLETE_QUERY = SELECT_ALL +
            FROM_MYDB_ATHLETE +
            WHERE_USER;
    public static ResultSet loadAthlete(PreparedStatement preparedStatement, String fc) throws SQLException {
        preparedStatement.setString(1, fc);
        return preparedStatement.executeQuery();
    }

    public static final String INSERT_ATHLETE_QUERY_1 = "INSERT INTO mydb.User (FC, Name, Surname, Username, Birth, Email, Gender, Password) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String INSERT_ATHLETE_QUERY_2 = "INSERT INTO mydb.Athlete (User) VALUES (?)";
    public static void insertAthlete(PreparedStatement preparedStatement, PreparedStatement preparedStatement1, Athlete athlete) throws SQLException {
        preparedStatement.setString(1, athlete.getFiscalCode());
        preparedStatement.setString(2, athlete.getName());
        preparedStatement.setString(3, athlete.getSurname());
        preparedStatement.setString(4, athlete.getUsername());
        preparedStatement.setDate(5, Date.valueOf(athlete.getDateOfBirth()));
        preparedStatement.setString(6, athlete.getEmail());
        preparedStatement.setString(7, String.valueOf(athlete.getGender()));
        preparedStatement.setString(8, athlete.getPassword());
        preparedStatement.executeUpdate();

        preparedStatement1.setString(1, athlete.getFiscalCode());
        preparedStatement1.executeUpdate();
    }

    public static final String UPDATE_CARD_INFO_ATHLETE_QUERY = UPDATE_MYDB_ATHLETE +
            "SET CardNumber = ?, CardExpirationDate = ? " +
            WHERE_USER;
    public static void updateCardInfoAthlete(PreparedStatement preparedStatement, Athlete athlete, Card card) throws SQLException {
        preparedStatement.setString(1, card.cardNumber());
        preparedStatement.setDate(2, Date.valueOf((card.cardExpirationDate()).atDay(1)));
        preparedStatement.setString(3, athlete.getFiscalCode());
        preparedStatement.executeUpdate();
    }

    public static final String REMOVE_CARD_INFO_ATHLETE_QUERY = UPDATE_MYDB_ATHLETE +
            "SET CardNumber = NULL, CardExpirationDate = NULL " +
            WHERE_USER;
    public static void removeCardInfoAthlete(PreparedStatement preparedStatement, String fc) throws SQLException {
        preparedStatement.setString(1, fc);
        preparedStatement.executeUpdate();
    }

    public static final String UPDATE_TRAINER_ATHLETE_QUERY = UPDATE_MYDB_ATHLETE +
            "SET Trainer = ? " +
            WHERE_USER;
    public static void updateTrainerAthlete(PreparedStatement preparedStatement, String athleteFc, String trainerFc) throws SQLException {
        preparedStatement.setString(1, trainerFc);
        preparedStatement.setString(2, athleteFc);
        preparedStatement.executeUpdate();
    }

    public static final String REMOVE_TRAINER_ATHLETE_QUERY = UPDATE_MYDB_ATHLETE +
            "SET Trainer = NULL " +
            WHERE_USER;
    public static void removeTrainerAthlete(PreparedStatement preparedStatement, String athleteFc) throws SQLException {
        preparedStatement.setString(1, athleteFc);
        preparedStatement.executeUpdate();
    }

    public static final String COUNT_ATHLETE_COURSES_QUERY = "SELECT COUNT(*) FROM mydb.Subscribe " +
            "WHERE Athlete = ?";
    public static ResultSet countAthleteCourses(PreparedStatement preparedStatement, String athleteFc) throws SQLException {
        preparedStatement.setString(1, athleteFc);
        return preparedStatement.executeQuery();
    }

    public static final String ADD_WORKOUT_PLAN_TO_ATHLETE_QUERY = "UPDATE mydb.Athlete SET WorkoutPlan = ? " +
            WHERE_USER;
    public static void addWorkoutPlanToAthlete(PreparedStatement preparedStatement, int idWorkoutPlan, String athleteFc) throws SQLException {
        preparedStatement.setInt(1, idWorkoutPlan);
        preparedStatement.setString(2, athleteFc);
        preparedStatement.executeUpdate();
    }

    public static final String REMOVE_WORKOUT_PLAN_QUERY = "DELETE FROM mydb.WorkoutPlan " +
            "WHERE idWorkoutPlan = ?";
    public static void removeWorkoutPlan(PreparedStatement preparedStatement, int idWorkoutPlan) throws SQLException {
        preparedStatement.setInt(1, idWorkoutPlan);
        preparedStatement.executeUpdate();
    }
}