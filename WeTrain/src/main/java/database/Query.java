package database;

import model.*;

import java.sql.*;

public class Query {

    //TODO gestione duplicate record
    public static int insertAthlete(Statement stmt, Athlete athlete) throws SQLException {
        return stmt.executeUpdate(String.format("INSERT INTO mydb.Athlete (FC, Name, Surname, Birth, Email) VALUES ('%s', '%s', '%s', '%s', '%s');", athlete.getFiscalCode(), athlete.getName(), athlete.getSurname(), Date.valueOf(athlete.getDateOfBirth()), athlete.getEmail()));
    }

    public static int insertCardInfoAthlete(Statement stmt, Athlete athlete) throws SQLException {
        return stmt.executeUpdate(String.format("UPDATE ATHLETE SET CardNumber = '%s', CardExpirationDate = '%s' WHERE Fc = '%s';", athlete.getCardNumber(), Date.valueOf(athlete.getCardExpirationDate()), athlete.getFiscalCode()));
    }

    public static int insertTrainer(Statement stmt, Trainer trainer) throws SQLException {
        return stmt.executeUpdate(String.format("INSERT INTO mydb.Trainer (FC, Name, Surname, Birth, Email) VALUES ('%s', '%s', '%s', '%s', '%s');", trainer.getFiscalCode(), trainer.getName(), trainer.getSurname(), Date.valueOf(trainer.getDateOfBirth()), trainer.getEmail()));
    }

    public static int insertIbanTrainer(Statement stmt, Trainer trainer) throws SQLException {
        return stmt.executeUpdate(String.format("UPDATE TRAINER SET Iban = '%s' WHERE Fc = '%s';", trainer.getIban(), trainer.getFiscalCode()));
    }

    public static int insertCourse(Statement stmt, Course course) throws SQLException {
        return stmt.executeUpdate(String.format("INSERT INTO mydb.Course (Name, Description, FitnessLevel, Equipment, Trainer) VALUES ('%s', '%s', '%s', '%s', '%s');", course.getName(), course.getDescription(), course.getFitnessLevel(), course.getEquipment(), course.getOwner().getFiscalCode()));
    }

    public static int insertExercise(Statement stmt, Exercise exercise) throws SQLException {
        return stmt.executeUpdate(String.format("INSERT INTO mydb.Exercise (Name, Information, Trainer) VALUES ('%s', '%s', '%s');", exercise.getName(), exercise.getInformation(), exercise.getTrainer().getFiscalCode()));
    }

    public static int insertLesson(Statement stmt, Lesson lesson) throws SQLException {
        return stmt.executeUpdate(String.format("INSERT INTO mydb.Lesson (LessonDay, Course) VALUES ('%s', '%s');", Timestamp.valueOf(lesson.getLessonDate()), lesson.getCourse().getId()));
    }

    public static int insertNotification(Statement stmt, Notification notification) throws SQLException {
        if(notification.getUser() instanceof Athlete) {
            return stmt.executeUpdate(String.format("INSERT INTO mydb.Notification (Type, Description, NotificationDate, Athlete) " +
                    "VALUES ('%s','%s','%s','%s');", notification.getType(), notification.getDescription(), Timestamp.valueOf(notification.getNotificationDate()), notification.getUser()));
        }
        else {
            return stmt.executeUpdate(String.format("INSERT INTO mydb.Notification (Type, Description, NotificationDate, Trainer) " +
                    "VALUES ('%s','%s','%s','%s');", notification.getType(), notification.getDescription(), Timestamp.valueOf(notification.getNotificationDate()), notification.getUser()));
        }
    }
}
