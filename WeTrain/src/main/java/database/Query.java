package database;

import model.*;
import org.jetbrains.annotations.NotNull;

import java.sql.*;

public class Query {

    private static final String SELECT_ALL = "SELECT * ";

    private Query(){}


    //TODO gestione duplicate record
    public static ResultSet loadAllNotifications(@NotNull Statement stmt, User user) throws SQLException {
        return stmt.executeQuery(String.format(SELECT_ALL +
                "FROM Notification" +
                "WHERE Athlete = '%s' or Trainer = '%s';", user.getFiscalCode(), user.getFiscalCode()));
    }

    public static int insertNotification(Statement stmt, Notification notification) throws SQLException {
        if(notification.getUser() instanceof Athlete) {
            return stmt.executeUpdate(String.format("INSERT INTO mydb.Notification (Type, Description, NotificationDate, Athlete) " +
                    "VALUES (%s,'%s','%s','%s');",
                    notification.getType(),
                    notification.getDescription(),
                    Timestamp.valueOf(notification.getNotificationDate()),
                    notification.getUser()));
        }
        else {
            return stmt.executeUpdate(String.format("INSERT INTO mydb.Notification (Type, Description, NotificationDate, Trainer) " +
                    "VALUES (%s,'%s','%s','%s');",
                    notification.getType(),
                    notification.getDescription(),
                    Timestamp.valueOf(notification.getNotificationDate()),
                    notification.getUser()));
        }
    }

    public static int deleteNotification(Statement stmt, Notification notification) throws SQLException {
        return stmt.executeUpdate(String.format("DELETE FROM mydb.Notification" +
                "WHERE idNotification = %s;", notification.getId()));
    }

    public static ResultSet loadAthlete(Statement stmt, String fc) throws SQLException {
        return stmt.executeQuery(String.format(SELECT_ALL +
                " FROM mydb.Athlete" +
                " WHERE User = '%s';", fc));
    }

    public static void insertAthlete(Statement stmt, Athlete athlete) throws SQLException {
        stmt.executeUpdate(String.format("INSERT INTO mydb.User (FC, Name, Surname, Username, Birth, Email, Gender, Password) " +
                "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');",
                athlete.getFiscalCode(),
                athlete.getName(),
                athlete.getSurname(),
                athlete.getUsername(),
                Date.valueOf(athlete.getDateOfBirth()),
                athlete.getEmail(),
                athlete.getGender(),
                athlete.getPassword()));
        stmt.executeUpdate(String.format("INSERT INTO mydb.Athlete (User) VALUES ('%s');", athlete.getFiscalCode()));
    }

    public static int insertCardInfoAthlete(Statement stmt, Athlete athlete) throws SQLException {
        return stmt.executeUpdate(String.format("UPDATE mydb.ATHLETE SET CardNumber = '%s', CardExpirationDate = '%s' WHERE User = '%s';", athlete.getCardNumber(), Date.valueOf(athlete.getCardExpirationDate()), athlete.getFiscalCode()));
    }

    public static int deleteAthlete(Statement stmt, Athlete athlete) throws SQLException {
        return stmt.executeUpdate(String.format("DELETE FROM mydb.Athlete" +
                "WHERE User = '%s';", athlete.getFiscalCode()));
    }

    public static ResultSet loadTrainer(Statement stmt, String fc) throws SQLException {
        return stmt.executeQuery(String.format(SELECT_ALL +
                "FROM mydb.Trainer " +
                "WHERE User = '%s';", fc));
    }

    public static void insertTrainer(Statement stmt, Trainer trainer) throws SQLException {
        stmt.executeUpdate(String.format("INSERT INTO mydb.User (FC, Name, Surname, Username, Birth, Gender, Email, Password) " +
                "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');",
                trainer.getFiscalCode(),
                trainer.getName(),
                trainer.getSurname(),
                trainer.getUsername(),
                Date.valueOf(trainer.getDateOfBirth()),
                trainer.getGender(),
                trainer.getEmail(),
                trainer.getPassword()));
        stmt.executeUpdate(String.format("INSERT INTO mydb.Trainer (User) VALUES ('%s');", trainer.getFiscalCode()));
    }

    public static int insertIbanTrainer(Statement stmt, Trainer trainer) throws SQLException {
        return stmt.executeUpdate(String.format("UPDATE mydb.TRAINER SET Iban = '%s' WHERE User = '%s';", trainer.getIban(), trainer.getFiscalCode()));
    }

    public static int deleteTrainer(Statement stmt, Trainer trainer) throws SQLException {
        return stmt.executeUpdate(String.format("DELETE FROM mydb.Trainer " +
                "WHERE User = '%s';", trainer.getFiscalCode()));
    }

    public static ResultSet loadUser(Statement stmt, String email, String password) throws SQLException {
        System.out.println(password);
        return stmt.executeQuery(String.format(SELECT_ALL +
                " FROM mydb.User " +
                " WHERE Email = '%s' AND Password = '%s';", email, password));
    }

    public static ResultSet loadUser(Statement stmt, String fc) throws SQLException {
        return stmt.executeQuery(String.format(SELECT_ALL +
                "FROM mydb.User " +
                "WHERE FC = '%s';", fc));
    }

    public static ResultSet loadCourse(Statement stmt, int id) throws SQLException {
        return stmt.executeQuery(String.format(SELECT_ALL +
                "FROM mydb.Course " +
                "WHERE idCourse = %s;", id));
    }

    public static ResultSet loadAllCoursesAthlete(Statement stmt, Athlete athlete) throws SQLException {
        return stmt.executeQuery(String.format(SELECT_ALL +
                "FROM mydb.Course join mydb.Subscribe on Subscribe.Course = Course.idCourse " +
                "WHERE Subscribe.Athlete = '%s';", athlete.getFiscalCode()));
    }

    public static ResultSet loadAllCoursesTrainer(Statement stmt, Trainer trainer) throws SQLException {
        return stmt.executeQuery(String.format(SELECT_ALL +
                "FROM mydb.Course " +
                "WHERE Trainer = '%s';", trainer.getFiscalCode()));
    }

    public static int insertCourse(Statement stmt, Course course) throws SQLException {
        return stmt.executeUpdate(String.format("INSERT INTO mydb.Course (Name, Description, FitnessLevel, Equipment, Trainer) " +
                "VALUES ('%s', '%s', '%s', '%s', '%s');",
                course.getName(),
                course.getDescription(),
                course.getFitnessLevel(),
                course.getEquipment(),
                course.getOwner().getFiscalCode()));
    }

    public static int deleteCourse(Statement stmt, Course course) throws SQLException {
        return stmt.executeUpdate(String.format("DELETE FROM mydb.Course " +
                "WHERE idCourse = %s;", course.getId()));
    }

    public static ResultSet loadExercise(Statement stmt, int idExercise) throws SQLException {
        return stmt.executeQuery(String.format(SELECT_ALL +
                "FROM mydb.Exercise " +
                "WHERE idExercise = %s;", idExercise));
    }

    public static ResultSet loadTrainerExercises(Statement stmt, Trainer trainer) throws SQLException {
        return stmt.executeQuery(String.format("SELECT Exercise.* " +
                "FROM mydb.Exercise join mydb.Trainer on Exercise.Trainer = '%s';", trainer.getFiscalCode()));
    }

    public static void insertExercise(Statement stmt, Exercise exercise) throws SQLException {
        stmt.executeUpdate(String.format("INSERT INTO mydb.Exercise (Name, Information, Trainer) " +
                "VALUES ('%s', '%s', '%s');",
                exercise.getName(),
                exercise.getInfo(),
                exercise.getTrainer().getFiscalCode()));
    }

    public static int deleteExercise(Statement stmt, Exercise exercise) throws SQLException {
        return stmt.executeUpdate(String.format("DELETE FROM mydb.Exercise" +
                "WHERE idExercise = %s;", exercise.getId()));
    }

    public static ResultSet loadLesson(Statement stmt, int id) throws SQLException {
        return stmt.executeQuery(String.format(SELECT_ALL +
                "FROM mydb.Lesson " +
                "WHERE idLesson = %s;", id));
    }

    public static ResultSet loadAllLessons(Statement stmt, Course course) throws SQLException {
        return stmt.executeQuery(String.format(SELECT_ALL +
                "FROM mydb.Lesson " +
                "WHERE Course = %s;", course.getId()));
    }

    public static int insertLesson(Statement stmt, Lesson lesson) throws SQLException {
        return stmt.executeUpdate(String.format("INSERT INTO mydb.Lesson (LessonDay, Course) " +
                "VALUES ('%s', %s);",
                Timestamp.valueOf(lesson.getLessonDate()),
                lesson.getCourse().getId()));
    }

    public static int deleteLesson(Statement stmt, Lesson lesson) throws SQLException {
        return stmt.executeUpdate(String.format("DELETE FROM mydb.Lesson " +
                "WHERE idLesson = %s;", lesson.getId()));
    }

    public static int insertRequest(Statement stmt, Request request) throws SQLException {
        return stmt.executeUpdate(String.format("INSERT INTO mydb.Request (RequestDate, Athlete, Trainer) " +
                "VALUES ('%s', '%s', '%s');",
                Timestamp.valueOf(request.getRequestDate()),
                request.getAthlete().getFiscalCode(),
                request.getTrainer().getFiscalCode()));
    }

    public static ResultSet loadTrainerRequests(Statement stmt, Trainer trainer) throws SQLException {
        return stmt.executeQuery(String.format("SELECT Request.* " +
                "FROM mydb.Request join mydb.Trainer on Request.Trainer = '%s';", trainer.getFiscalCode()));
    }

    public static ResultSet loadRequest(Statement stmt, int id) throws SQLException {
        return stmt.executeQuery(String.format(SELECT_ALL +
                "FROM mydb.Request " +
                "WHERE idRequest = %s;", id));
    }

    public static void deleteRequest(Statement stmt, Request request) throws SQLException {
        stmt.executeUpdate(String.format("DELETE FROM mydb.Request" +
                "WHERE idRequest = %s;", request.getId()));
    }

    public static void insertExerciseInWorkoutDay(Statement stmt, Exercise exercise, int workoutDayKey) throws SQLException{
        stmt.executeUpdate(String.format("INSERT INTO mydb.Contains (WorkoutDay, Exercise) " +
                "VALUES (%s, %s);",
                workoutDayKey,
                exercise.getId()));
    }

    public static int insertWorkoutDay(WorkoutDay workoutDay, int workoutPlanKey) throws SQLException{
        try (PreparedStatement statement2 = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(String.format("INSERT INTO mydb.WorkoutDay (WorkoutPlan) VALUES (%s);", workoutPlanKey), Statement.RETURN_GENERATED_KEYS);) {
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

    public static ResultSet loadAllWorkoutDays(Statement stmt, WorkoutPlan workoutPlan) throws SQLException {
        return stmt.executeQuery(String.format("SELECT WorkoutDay.* " +
                "FROM mydb.WorkoutDay " +
                "WHERE WorkoutDay.WorkoutPlan = %s;", workoutPlan.getId()));
    }

    public static ResultSet loadAllExerciseInWorkoutDays(Statement stmt, WorkoutDay workoutDay) throws SQLException {
        return stmt.executeQuery(String.format("SELECT Exercise.* " +
                "FROM mydb.Contains join mydb.Exercise on Contains.Exercise = Exercise.idExercise " +
                "WHERE Contains.WorkoutDay = %s;", workoutDay.getId()));
    }

    public static int deleteWorkoutDay(Statement stmt, WorkoutDay workoutDay) throws SQLException {
        return stmt.executeUpdate(String.format("DELETE FROM mydb.WorkoutDay " +
                "WHERE idWorkoutDay = %s;", workoutDay.getId()));
    }

    public static int insertWorkoutPlan(WorkoutPlan workoutPlan) throws SQLException {

        try(PreparedStatement statement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(String.format("INSERT INTO mydb.WorkoutPlan (Athlete) VALUES ('%s');", workoutPlan.getAthlete().getFiscalCode()), Statement.RETURN_GENERATED_KEYS);) {
            if(statement.executeUpdate() == 0){
                System.out.println("Inserimento WorkoutPlan fallito.");
            }
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

    public static ResultSet loadWorkoutPlan(Statement stmt, Integer idWorkoutPlan) throws SQLException {
        return stmt.executeQuery(String.format("SELECT WorkoutPlan.* " +
                "FROM mydb.WorkoutPlan " +
                "WHERE idWorkoutPlan = %s;", idWorkoutPlan));
    }

    public static int deleteWorkoutPlan(Statement stmt, WorkoutPlan workoutPlan) throws SQLException {
        return stmt.executeUpdate(String.format("DELETE FROM mydb.WorkoutPlan " +
                "WHERE idWorkoutPlan = %s;", workoutPlan.getId()));
    }

    public static int insertSubscribe(Statement stmt, Course course, Athlete athlete) throws SQLException {
        return stmt.executeUpdate(String.format("INSERT INTO mydb.Subscribe (Course, Athlete) " +
                "VALUES (%s, '%s');",
                course.getId(),
                athlete.getFiscalCode()));
    }

    public static int deleteSubscribe(Statement stmt, Course course, Athlete athlete) throws SQLException {
        return stmt.executeUpdate(String.format("DELETE FROM mydb.Subscribe " +
                "WHERE Course = %s and Athlete = '%s';", course.getId(), athlete.getFiscalCode()));
    }
}
