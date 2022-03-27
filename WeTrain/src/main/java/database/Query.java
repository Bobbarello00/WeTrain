package database;

import model.*;
import org.jetbrains.annotations.NotNull;

import java.sql.*;

public class Query {

    //TODO gestione duplicate record
    public static ResultSet loadAllNotifications(@NotNull Statement stmt, User user) throws SQLException {
        return stmt.executeQuery(String.format("SELECT *;" +
                "FROM Notification;" +
                "WHERE Athlete = '%s' or Trainer = '%s'", user.getFiscalCode(), user.getFiscalCode()));
    }

    public static int insertNotification(Statement stmt, Notification notification) throws SQLException {
        if(notification.getUser() instanceof Athlete) {
            return stmt.executeUpdate(String.format("INSERT INTO mydb.Notification (Type, Description, NotificationDate, Athlete) " +
                    "VALUES (%s,'%s','%s','%s');", notification.getType(), notification.getDescription(), Timestamp.valueOf(notification.getNotificationDate()), notification.getUser()));
        }
        else {
            return stmt.executeUpdate(String.format("INSERT INTO mydb.Notification (Type, Description, NotificationDate, Trainer) " +
                    "VALUES (%s,'%s','%s','%s');", notification.getType(), notification.getDescription(), Timestamp.valueOf(notification.getNotificationDate()), notification.getUser()));
        }
    }

    public static int deleteNotification(Statement stmt, Notification notification) throws SQLException {
        return stmt.executeUpdate(String.format("DELETE FROM Notification" +
                "WHERE idNotification = %s", notification.getId()));
    }

    public static ResultSet loadAthlete(Statement stmt, String fc) throws SQLException {
        return stmt.executeQuery(String.format("SELECT *;" +
                "FROM Athlete;" +
                "WHERE fc = '%s'", fc));
    }

    public static int insertAthlete(Statement stmt, Athlete athlete) throws SQLException {
        return stmt.executeUpdate(String.format("INSERT INTO mydb.Athlete (FC, Name, Surname, Birth, Email) VALUES ('%s', '%s', '%s', '%s', '%s');", athlete.getFiscalCode(), athlete.getName(), athlete.getSurname(), Date.valueOf(athlete.getDateOfBirth()), athlete.getEmail()));
    }

    public static int insertCardInfoAthlete(Statement stmt, Athlete athlete) throws SQLException {
        return stmt.executeUpdate(String.format("UPDATE ATHLETE SET CardNumber = '%s', CardExpirationDate = '%s' WHERE Fc = '%s';", athlete.getCardNumber(), Date.valueOf(athlete.getCardExpirationDate()), athlete.getFiscalCode()));
    }

    public static int deleteAthlete(Statement stmt, Athlete athlete) throws SQLException {
        return stmt.executeUpdate(String.format("DELETE FROM Athlete" +
                "WHERE FC = '%s'", athlete.getFiscalCode()));
    }

    public static ResultSet loadTrainer(Statement stmt, String fc) throws SQLException {
        return stmt.executeQuery(String.format("SELECT *;" +
                "FROM Trainer;" +
                "WHERE fc = '%s'", fc));
    }

    public static int insertTrainer(Statement stmt, Trainer trainer) throws SQLException {
        return stmt.executeUpdate(String.format("INSERT INTO mydb.Trainer (FC, Name, Surname, Birth, Email) VALUES ('%s', '%s', '%s', '%s', '%s');", trainer.getFiscalCode(), trainer.getName(), trainer.getSurname(), Date.valueOf(trainer.getDateOfBirth()), trainer.getEmail()));
    }

    public static int insertIbanTrainer(Statement stmt, Trainer trainer) throws SQLException {
        return stmt.executeUpdate(String.format("UPDATE TRAINER SET Iban = '%s' WHERE Fc = '%s';", trainer.getIban(), trainer.getFiscalCode()));
    }

    public static int deleteTrainer(Statement stmt, Trainer trainer) throws SQLException {
        return stmt.executeUpdate(String.format("DELETE FROM Trainer" +
                "WHERE FC = '%s'", trainer.getFiscalCode()));
    }

    public static ResultSet loadCourse(Statement stmt, int id) throws SQLException {
        return stmt.executeQuery(String.format("SELECT *;" +
                "FROM Course;" +
                "WHERE idCourse = %s", id));
    }

    public static int insertCourse(Statement stmt, Course course) throws SQLException {
        return stmt.executeUpdate(String.format("INSERT INTO mydb.Course (Name, Description, FitnessLevel, Equipment, Trainer) VALUES ('%s', '%s', '%s', '%s', '%s');", course.getName(), course.getDescription(), course.getFitnessLevel(), course.getEquipment(), course.getOwner().getFiscalCode()));
    }

    public static int deleteCourse(Statement stmt, Course course) throws SQLException {
        return stmt.executeUpdate(String.format("DELETE FROM Course" +
                "WHERE idCourse = %s", course.getId()));
    }

    public static ResultSet loadExercise(Statement stmt, int idExercise) throws SQLException {
        return stmt.executeQuery(String.format("SELECT *;" +
                "FROM Exercise;" +
                "WHERE idExercise = %s", idExercise));
    }

    public static ResultSet loadTrainerExercises(Statement stmt, Trainer trainer) throws SQLException {
        return stmt.executeQuery(String.format("SELECT *;" +
                "FROM Exercise join Trainer on Exercise.Trainer = '%s';", trainer.getFiscalCode()));
    }

    public static int insertExercise(Statement stmt, Exercise exercise) throws SQLException {
        return stmt.executeUpdate(String.format("INSERT INTO mydb.Exercise (Name, Information, Trainer) VALUES ('%s', '%s', '%s');", exercise.getName(), exercise.getInfo(), exercise.getTrainer().getFiscalCode()));
    }

    public static int deleteExercise(Statement stmt, Exercise exercise) throws SQLException {
        return stmt.executeUpdate(String.format("DELETE FROM Exercise" +
                "WHERE idExercise = %s", exercise.getId()));
    }

    public static ResultSet loadLesson(Statement stmt, int id) throws SQLException {
        return stmt.executeQuery(String.format("SELECT *;" +
                "FROM Lesson;" +
                "WHERE idLesson = %s", id));
    }

    public static int insertLesson(Statement stmt, Lesson lesson) throws SQLException {
        return stmt.executeUpdate(String.format("INSERT INTO mydb.Lesson (LessonDay, Course) VALUES ('%s', %s);", Timestamp.valueOf(lesson.getLessonDate()), lesson.getCourse().getId()));
    }

    public static int deleteLesson(Statement stmt, Lesson lesson) throws SQLException {
        return stmt.executeUpdate(String.format("DELETE FROM Lesson" +
                "WHERE idLesson = %s", lesson.getId()));
    }

    public static int insertRequest(Statement stmt, Request request) throws SQLException {
        return stmt.executeUpdate(String.format("INSERT INTO mydb.Request (RequestDate, Athlete, Trainer) VALUES ('%s', '%s', '%s');", Timestamp.valueOf(request.getRequestDate()), request.getAthlete().getFiscalCode(), request.getTrainer().getFiscalCode()));
    }

    public static ResultSet loadTrainerRequests(Statement stmt, Trainer trainer) throws SQLException {
        return stmt.executeQuery(String.format("SELECT *;" +
                "FROM Request join Trainer on Request.Trainer = '%s';", trainer.getFiscalCode()));
    }

    public static ResultSet loadRequest(Statement stmt, int id) throws SQLException {
        return stmt.executeQuery(String.format("SELECT *;" +
                "FROM Request;" +
                "WHERE idRequest = %s", id));
    }

    public static int deleteRequest(Statement stmt, Request request) throws SQLException {
        return stmt.executeUpdate(String.format("DELETE FROM Request" +
                "WHERE idRequest = %s", request.getId()));
    }

    public static int insertExerciseInWorkoutDay(Statement stmt, Exercise exercise, int workoutDayKey) throws SQLException{
        return stmt.executeUpdate(String.format("INSERT INTO mydb.Contains (WorkoutDay, Exercise) VALUES (%s, %s);", workoutDayKey, exercise.getId()));
    }

    public static int insertWorkoutDay(Statement stmt, WorkoutDay workoutDay, int workoutPlanKey) throws SQLException{
        try (PreparedStatement statement2 = DatabaseConnection.getInstance().conn.prepareStatement(String.format("INSERT INTO mydb.WorkoutDay (WorkoutPlan) VALUES (%s)", workoutPlanKey), Statement.RETURN_GENERATED_KEYS);) {
            statement2.executeUpdate();
            try (ResultSet generatedKeys = statement2.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    //TODO eccezione personalizzata
                    System.out.println("DB - Errore nell'inserimento del workoutDay");
                }
            }
        }
        return -1;
    }

    public static int deleteWorkoutDay(Statement stmt, WorkoutDay workoutDay) throws SQLException {
        return stmt.executeUpdate(String.format("DELETE FROM WorkoutDay" +
                "WHERE idWorkoutDay = %s", workoutDay.getId()));
    }

    public static int insertWorkoutPlan(Statement stmt, WorkoutPlan workoutPlan) throws SQLException {

        try(PreparedStatement statement = DatabaseConnection.getInstance().conn.prepareStatement(String.format("INSERT INTO mydb.WorkoutPlan (Athlete) VALUES ('%s');", workoutPlan.getAthlete().getFiscalCode()), Statement.RETURN_GENERATED_KEYS);) {
            int ret = statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    //TODO eccezione personalizzata
                    System.out.println("DB - Errore nell'inserimento del workoutPlan");
                }
            }
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return -1;
    }

    public static ResultSet loadWorkoutPlan(Statement stmt, Athlete athlete) throws SQLException {
        return stmt.executeQuery(String.format("SELECT WorkoutPlan.*;" +
                "FROM WorkoutPlan join Athlete on WorkoutPlan.idWorkoutPlan = Athlete.WorkoutPlan;" +
                "WHERE Athlete.fc = '%s'", athlete.getFiscalCode()));
    }

    public static int deleteWorkoutPla(Statement stmt, WorkoutPlan workoutPlan) throws SQLException {
        return stmt.executeUpdate(String.format("DELETE FROM WorkoutPlan" +
                "WHERE idWorkoutPlan = %s", workoutPlan.getId()));
    }

    public static int insertSubscribe(Statement stmt, Course course, Athlete athlete) throws SQLException {
        return stmt.executeUpdate(String.format("INSERT INTO mydb.Subscribe (Course, Athlete) VALUES (%s, '%s');", course.getId(), athlete.getFiscalCode()));
    }

    public static int deleteSubscribe(Statement stmt, Course course, Athlete athlete) throws SQLException {
        return stmt.executeUpdate(String.format("DELETE FROM Subscribe" +
                "WHERE Course = %s and Athlete = '%s'", course.getId(), athlete.getFiscalCode()));
    }
}
