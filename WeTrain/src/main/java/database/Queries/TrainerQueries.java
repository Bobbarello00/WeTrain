package database.Queries;

import models.Trainer;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainerQueries extends Queries{

    public static final String LOAD_TRAINER_QUERY = SELECT_ALL +
            "FROM mydb.Trainer " +
            WHERE_USER;

    public static final String LOAD_ALL_TRAINERS_QUERY = SELECT_ALL +
            "FROM mydb.Trainer join mydb.Athlete on Athlete.Trainer = Trainer.User " +
            "GROUP BY Trainer.User " +
            "ORDER BY COUNT(Athlete.User) DESC " +
            LIMIT_10;

    public static final String SEARCH_TRAINER_QUERY = SELECT_ALL +
            "FROM mydb.Trainer join mydb.User on Trainer.User = User.FC " +
            "WHERE (Name LIKE ? OR Surname LIKE ?) " +
            "GROUP BY User " +
            "ORDER BY COUNT(User) DESC " +
            LIMIT_10;

    public static final String INSERT_TRAINER_QUERY_1 = "INSERT INTO mydb.User (FC, Name, Surname, Username, Birth, Gender, Email, Password) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String INSERT_TRAINER_QUERY_2 = "INSERT INTO mydb.Trainer (User) VALUES (?)";

    public static final String UPDATE_IBAN_TRAINER_QUERY = "UPDATE mydb.Trainer SET Iban = ? " +
            WHERE_USER;

    public static final String COUNT_TRAINER_SUBSCRIBERS_QUERY = "SELECT COUNT(*) " +
            FROM_MYDB_ATHLETE +
            WHERE_TRAINER;

    public static final String LOAD_ALL_TRAINER_SUBSCRIBERS_QUERY = SELECT_ALL +
            FROM_MYDB_ATHLETE +
            "WHERE Trainer = ? " +
            LIMIT_30;

    public static ResultSet loadTrainer(String fc, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, fc);
        return preparedStatement.executeQuery();
    }

    public static ResultSet loadAllTrainers(PreparedStatement preparedStatement) throws SQLException {
        return preparedStatement.executeQuery();
    }

    public static ResultSet searchTrainer(PreparedStatement preparedStatement, String name) throws SQLException {
        String myString = "%%" + name + "%%";
        preparedStatement.setString(1, myString);
        preparedStatement.setString(2, myString);
        return preparedStatement.executeQuery();
    }

    public static void insertTrainer(PreparedStatement preparedStatement, PreparedStatement preparedStatement1, Trainer trainer) throws SQLException {
        preparedStatement.setString(1, trainer.getFiscalCode());
        preparedStatement.setString(2, trainer.getName());
        preparedStatement.setString(3, trainer.getSurname());
        preparedStatement.setString(4, trainer.getUsername());
        preparedStatement.setDate(5, Date.valueOf(trainer.getDateOfBirth()));
        preparedStatement.setString(6, String.valueOf(trainer.getGender()));
        preparedStatement.setString(7, trainer.getEmail());
        preparedStatement.setString(8, trainer.getPassword());
        preparedStatement.executeUpdate();

        preparedStatement1.setString(1, trainer.getFiscalCode());
        preparedStatement1.executeUpdate();
    }

    public static void updateIbanTrainer(PreparedStatement preparedStatement, Trainer trainer) throws SQLException {
        preparedStatement.setString(1, trainer.getIban());
        preparedStatement.setString(2, trainer.getFiscalCode());
        preparedStatement.executeUpdate();
    }

    public static ResultSet countTrainerSubscribers(PreparedStatement preparedStatement, String trainerFc) throws SQLException {
        preparedStatement.setString(1, trainerFc);
        return preparedStatement.executeQuery();
    }

    public static ResultSet loadAllTrainerSubscribers(String trainerFc, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, trainerFc);
        return preparedStatement.executeQuery();
    }
}