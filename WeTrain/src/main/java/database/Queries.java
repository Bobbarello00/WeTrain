package database;

import com.mysql.cj.exceptions.CJCommunicationsException;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import exception.DBConnectionFailedException;
import model.*;

import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Queries {

    private static final String SELECT_ALL = "SELECT * ";
    private static final String WHERE_USER = "WHERE User = ?";
    private static final String LIMIT_10 = "LIMIT 10";
    private static final String LIMIT_30 = "LIMIT 30";

    private Queries(){}

    //TODO gestione duplicate record
    public static PreparedStatement loadAllNotifications(User receiver) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                SELECT_ALL +
                "FROM mydb.Notification " +
                "WHERE Receiver = ? " +
                LIMIT_30);
        preparedStatement.setString(1, receiver.getFiscalCode());
        return preparedStatement;
    }

    public static PreparedStatement insertNotification(int type, String info, LocalDateTime dateTime, String sender, String receiver) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "INSERT INTO mydb.Notification (Type, Info, NotificationDate, Sender, Receiver) " +
                "VALUES (?, ?, ?, ?, ?)");
        preparedStatement.setInt(1, type);
        preparedStatement.setString(2, info);
        preparedStatement.setTimestamp(3, Timestamp.valueOf(dateTime));
        preparedStatement.setString(4, sender);
        preparedStatement.setString(5, receiver);
        return preparedStatement;
    }

    public static PreparedStatement deleteNotification(int idNotification) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "DELETE FROM mydb.Notification " +
                "WHERE idNotification = ?");
        preparedStatement.setInt(1, idNotification);
        return preparedStatement;
    }

    public static PreparedStatement loadAthlete(String fc) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                SELECT_ALL +
                        "FROM mydb.Athlete " +
                        WHERE_USER);
        preparedStatement.setString(1, fc);
        return preparedStatement;
    }

    public static List<PreparedStatement> insertAthlete(Athlete athlete) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "INSERT INTO mydb.User (FC, Name, Surname, Username, Birth, Email, Gender, Password) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1, athlete.getFiscalCode());
        preparedStatement.setString(2, athlete.getName());
        preparedStatement.setString(3, athlete.getSurname());
        preparedStatement.setString(4, athlete.getUsername());
        preparedStatement.setDate(5, Date.valueOf(athlete.getDateOfBirth()));
        preparedStatement.setString(6, athlete.getEmail());
        preparedStatement.setString(7, String.valueOf(athlete.getGender()));
        preparedStatement.setString(8, athlete.getPassword());

        PreparedStatement preparedStatement1 = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "INSERT INTO mydb.Athlete (User) VALUES (?)");
        preparedStatement.setString(1, athlete.getFiscalCode());
        return Arrays.asList(preparedStatement, preparedStatement1);
    }

    public static void updateCardInfoAthlete(Athlete athlete) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "UPDATE mydb.Athlete " +
                "SET CardNumber = ?, CardExpirationDate = ? " +
                WHERE_USER)){
            preparedStatement.setString(1, athlete.getCardNumber());
            preparedStatement.setDate(2, Date.valueOf((athlete.getCardExpirationDate()).atDay(1)));
            preparedStatement.setString(3, athlete.getFiscalCode());
            preparedStatement.executeUpdate();
        }
    }


    public static void removeCardInfoAthlete(String fc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "UPDATE mydb.Athlete " +
                "SET CardNumber = NULL, CardExpirationDate = NULL " +
                WHERE_USER)) {
            preparedStatement.setString(1, fc);
            preparedStatement.executeUpdate();
        }
    }

    public static void updateTrainerAthlete(String athleteFc, String trainerFc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "UPDATE mydb.Athlete " +
                "SET Trainer = ? " +
                WHERE_USER)) {
            preparedStatement.setString(1, trainerFc);
            preparedStatement.setString(2, athleteFc);
            preparedStatement.executeUpdate();
        }
    }

    public static void removeTrainerAthlete(String athleteFc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "UPDATE mydb.Athlete " +
                "SET Trainer = NULL " +
                WHERE_USER)) {
            preparedStatement.setString(1, athleteFc);
            preparedStatement.executeUpdate();
        }
    }

    public static ResultSet countAthleteCourses(String athleteFc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "SELECT COUNT(*) FROM mydb.Subscribe " +
                "WHERE Athlete = ?")) {
            preparedStatement.setString(1, athleteFc);
            return preparedStatement.executeQuery();
        }
    }

    public static int deleteAthlete(String athleteFc) throws SQLException, DBConnectionFailedException {
        //TODO non lo usiamo
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "DELETE FROM mydb.Athlete " +
                WHERE_USER)) {
            preparedStatement.setString(1, athleteFc);
            return preparedStatement.executeUpdate();
        }
    }

    public static void addWorkoutPlanToAthlete(int idWorkoutPlan, String athleteFc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "UPDATE mydb.Athlete SET WorkoutPlan = ? " +
                WHERE_USER)) {
            preparedStatement.setInt(1, idWorkoutPlan);
            preparedStatement.setString(2, athleteFc);
            preparedStatement.executeUpdate();
        }
    }

    public static ResultSet loadTrainer(String fc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                SELECT_ALL +
                "FROM mydb.Trainer " +
                WHERE_USER)) {
            preparedStatement.setString(1, fc);
            return preparedStatement.executeQuery();
        }
    }

    public static ResultSet loadAllTrainers() throws SQLException, DBConnectionFailedException {
        //TODO CORREZIONE QUERY (ordinare per popolarità)
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                SELECT_ALL +
                "FROM mydb.Trainer " +
                "GROUP BY User " +
                "ORDER BY COUNT(User) DESC " +
                LIMIT_10)){
            return preparedStatement.executeQuery();
        }
    }

    public static ResultSet searchTrainer(String name) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                SELECT_ALL +
                "FROM mydb.Trainer join mydb.User on Trainer.User = User.FC " +
                "WHERE (Name LIKE ? OR Surname LIKE ?) " +
                "GROUP BY User " +
                "ORDER BY COUNT(User) DESC " +
                LIMIT_10)){
            StringBuilder myString = new StringBuilder();
            myString.append("%%");
            myString.append(name);
            myString.append("%%");
            preparedStatement.setString(1, myString.toString());
            preparedStatement.setString(2, myString.toString());
            return preparedStatement.executeQuery();
        }
    }

    public static void insertTrainer(Trainer trainer) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "INSERT INTO mydb.User (FC, Name, Surname, Username, Birth, Gender, Email, Password) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)")){
            preparedStatement.setString(1, trainer.getFiscalCode());
            preparedStatement.setString(2, trainer.getName());
            preparedStatement.setString(3, trainer.getSurname());
            preparedStatement.setString(4, trainer.getUsername());
            preparedStatement.setDate(5, Date.valueOf(trainer.getDateOfBirth()));
            preparedStatement.setString(6, String.valueOf(trainer.getGender()));
            preparedStatement.setString(7, trainer.getEmail());
            preparedStatement.setString(8, trainer.getPassword());
            preparedStatement.executeUpdate();
        }
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "INSERT INTO mydb.Trainer (User) VALUES (?)")){
            preparedStatement.setString(1, trainer.getFiscalCode());
            preparedStatement.executeUpdate();
        }
    }

    public static void updateIbanTrainer(Trainer trainer) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "UPDATE mydb.Trainer SET Iban = ? " +
                WHERE_USER)){
            preparedStatement.setString(1, trainer.getIban());
            preparedStatement.setString(2, trainer.getFiscalCode());
            preparedStatement.executeUpdate();
        }
    }

    public static ResultSet countTrainerSubscribers(String trainerFc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "SELECT COUNT(*) FROM mydb.Athlete "+
                        "WHERE Trainer = ?")){
            preparedStatement.setString(1, trainerFc);
            return preparedStatement.executeQuery();
        }
    }

    public static ResultSet loadAllTrainerSubscribers(String trainerFc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                SELECT_ALL +
                "FROM mydb.Athlete " +
                "WHERE Trainer = ? " +
                LIMIT_30)){
            preparedStatement.setString(1, trainerFc);
            return preparedStatement.executeQuery();
        }
    }

    public static int deleteTrainer(String trainerFc) throws SQLException, DBConnectionFailedException {
        //TODO non lo usiamo
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "DELETE FROM mydb.Trainer " +
                WHERE_USER)){
            preparedStatement.setString(1, trainerFc);
            return preparedStatement.executeUpdate();
        }
    }

    public static PreparedStatement loadUser(String email, String password) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "SELECT * " +
                        " FROM mydb.User " +
                        " WHERE Email = ? AND Password = ?");
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        return preparedStatement;
    }

    public static PreparedStatement loadUser(String fc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "SELECT * " +
                        "FROM mydb.User " +
                        "WHERE FC = ?")){
            preparedStatement.setString(1, fc);
            return preparedStatement;
        } catch(CJCommunicationsException | CommunicationsException e) {
            throw new DBConnectionFailedException();
        }
    }

    public static ResultSet loadCourse(int idCourse) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                SELECT_ALL +
                "FROM mydb.Course " +
                "WHERE idCourse = ?")){
            preparedStatement.setInt(1, idCourse);
            return preparedStatement.executeQuery();
        }
    }

    public static ResultSet loadPopularCourse() throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "SELECT Course.* " +
                "FROM mydb.Course join mydb.Subscribe on Course.idCourse = Subscribe.Course " +
                "GROUP BY idCourse " +
                "ORDER BY COUNT(idCourse) DESC " +
                LIMIT_10)){
            return preparedStatement.executeQuery();
        }
    }

    public static ResultSet loadAllCoursesAthlete(String athleteFc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                SELECT_ALL +
                "FROM mydb.Course join mydb.Subscribe on Subscribe.Course = Course.idCourse " +
                "WHERE Subscribe.Athlete = ?")){
            preparedStatement.setString(1, athleteFc);
            return preparedStatement.executeQuery();
        }
    }

    public static ResultSet loadAllCoursesTrainer(String trainerFc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                SELECT_ALL +
                "FROM mydb.Course " +
                "WHERE Trainer = ?")){
            preparedStatement.setString(1, trainerFc);
            return preparedStatement.executeQuery();
        }
    }

    public static int insertCourse(Course course) throws SQLException, DBConnectionFailedException {
        try (PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "INSERT INTO mydb.Course (Name, Description, FitnessLevel, Equipment, Trainer) " +
                        "VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, course.getName());
            preparedStatement.setString(2, course.getDescription());
            preparedStatement.setString(3, course.getFitnessLevel());
            preparedStatement.setString(4, course.getEquipment());
            preparedStatement.setString(5, course.getOwner().getFiscalCode());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
                return -1;
            }
        }
    }

    public static ResultSet searchCourse(String name, String fitnessLevel, Boolean[] days) throws SQLException, DBConnectionFailedException {
        //TODO QUESTA PER IL PREPARE STATEMENT TOCCA STARE ATTENTI
        try(Statement stmt = DatabaseConnectionSingleton.getInstance().getConn().createStatement()) {
            boolean condition = true;
            List<String> dayStringList = new ArrayList<>(7);
            for (int i = 1; i <= 7; i++) {
                dayStringList.add(DayOfWeek.of(i).name());
            }

            StringBuilder queryString = new StringBuilder();

            for (int i = 0; i < 7; i++) {
                if (days[i]) {
                    condition = false;
                    queryString.append(String.format("AND Lesson.LessonDay != '%s' ", dayStringList.get(i)));
                }
            }

            String nestedQuery = "(SELECT * " +
                    "FROM mydb.Lesson " +
                    "WHERE Lesson.Course = Course.idCourse " +
                    queryString +
                    ")";
            if (condition) {
                return stmt.executeQuery(String.format(SELECT_ALL +
                                "FROM mydb.Course " +
                                "WHERE Name LIKE '%%%s%%' " +
                                "AND FitnessLevel = '%s';",
                        name,
                        fitnessLevel));
            } else {
                return stmt.executeQuery(String.format(SELECT_ALL +
                                "FROM mydb.Course " +
                                "WHERE Name LIKE '%%%s%%' " +
                                "AND FitnessLevel = '%s' " +
                                "AND NOT EXISTS " +
                                nestedQuery +
                                ";",
                        name,
                        fitnessLevel));
            }
        }
    }

    public static ResultSet loadCourseStartedLessonUrl(int idCourse) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "SELECT StartedLessonUrl " +
                "FROM mydb.Course " +
                "WHERE idCourse = ?")){
            preparedStatement.setInt(1, idCourse);
            return  preparedStatement.executeQuery();
        }
    }

    public static void insertCourseStartedLessonUrl(int idCourse, String url) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "UPDATE mydb.Course " +
                        "SET StartedLessonUrl = ? " +
                        "WHERE idCourse = ?;")){
            preparedStatement.setString(1, url);
            preparedStatement.setInt(2, idCourse);
            preparedStatement.executeUpdate();
        }
    }

    public static void removeCourseStartedLessonUrl(int idCourse) throws SQLException, DBConnectionFailedException {
        //TODO non lo usiamo
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "UPDATE mydb.Course " +
                        "SET StartedLessonUrl = NULL " +
                        "WHERE idCourse = ?")){
            preparedStatement.setInt(1, idCourse);
            preparedStatement.executeUpdate();
        }
    }

    public static void modifyCourse(int idCourse, Course course) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "UPDATE mydb.Course " +
                        "SET Name = ?, Description = ?, FitnessLevel = ?, Equipment = ?, Trainer = ? " +
                        "WHERE idCourse = ?")){
            preparedStatement.setString(1, course.getName());
            preparedStatement.setString(2, course.getDescription());
            preparedStatement.setString(3, course.getFitnessLevel());
            preparedStatement.setString(4, course.getEquipment());
            preparedStatement.setString(5, course.getOwner().getFiscalCode());
            preparedStatement.setInt(6, idCourse);
            preparedStatement.executeUpdate();
        }
    }

    public static void deleteCourse(int idCourse) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "DELETE FROM mydb.Course " +
                        "WHERE idCourse = ?")){
            preparedStatement.setInt(1, idCourse);
            preparedStatement.executeUpdate();
        }
    }

    public static ResultSet loadExercise(int idExercise) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                SELECT_ALL +
                        "FROM mydb.Exercise " +
                        "WHERE idExercise =?")){
            preparedStatement.setInt(1, idExercise);
            return preparedStatement.executeQuery();
        }
    }

    public static ResultSet loadTrainerExercises(String trainerFc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "SELECT Exercise.* " +
                        "FROM mydb.Exercise join mydb.Trainer on Exercise.Trainer = ?")){
            preparedStatement.setString(1, trainerFc);
            return preparedStatement.executeQuery();
        }
    }

    public static void insertExercise(Exercise exercise) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "INSERT INTO mydb.Exercise (Name, Info, Trainer) " +
                        "VALUES (?, ?, ?)")){
            preparedStatement.setString(1, exercise.getName());
            preparedStatement.setString(2, exercise.getInfo());
            preparedStatement.setString(3, exercise.getTrainer().getFiscalCode());
            preparedStatement.executeUpdate();
        }
    }

    public static void deleteExercise(int idExercise) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "DELETE FROM mydb.Exercise " +
                        "WHERE idExercise = ?")){
            preparedStatement.setInt(1, idExercise);
            preparedStatement.executeUpdate();
        }
    }

    public static ResultSet loadLesson(int idLesson) throws SQLException, DBConnectionFailedException {
        //TODO non lo usiamo
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                SELECT_ALL +
                        "FROM mydb.Lesson " +
                        "WHERE idLesson = ?")){
            preparedStatement.setInt(1, idLesson);
            return preparedStatement.executeQuery();
        }
    }

    public static ResultSet loadAllLessons(int idCourse) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                SELECT_ALL +
                        "FROM mydb.Lesson " +
                        "WHERE Course = ?")){
            preparedStatement.setInt(1, idCourse);
            return preparedStatement.executeQuery();
        }
    }

    public static void insertLesson(Lesson lesson, int idCourse) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "INSERT INTO mydb.Lesson (LessonDay, LessonStartTime, LessonEndTime, Course) " +
                        "VALUES (?, ?, ?, ?)")){
            preparedStatement.setString(1, lesson.getLessonDay());
            preparedStatement.setString(2, lesson.getLessonStartTime().toString());
            preparedStatement.setString(3, lesson.getLessonEndTime().toString());
            preparedStatement.setInt(4, idCourse);
            preparedStatement.executeUpdate();
        }
    }

    public static int deleteLesson(int idLesson) throws SQLException, DBConnectionFailedException {
        //TODO non lo usiamo
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "DELETE FROM mydb.Lesson " +
                        "WHERE idLesson = ?")){
            preparedStatement.setInt(1, idLesson);
            return preparedStatement.executeUpdate();
        }
    }

    public static void insertRequest(LocalDateTime requestDate, String info, String athleteFc, String trainerFc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "INSERT INTO mydb.Request (RequestDate, Info, Athlete, Trainer) " +
                        "VALUES (?, ?, ?, ?)")){
            preparedStatement.setTimestamp(1, Timestamp.valueOf(requestDate));
            preparedStatement.setString(2, info);
            preparedStatement.setString(3, athleteFc);
            preparedStatement.setString(4, trainerFc);
            preparedStatement.executeUpdate();
        }
    }

    public static ResultSet loadTrainerRequests(String trainerFc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "SELECT Request.* " +
                        "FROM mydb.Request join mydb.Trainer on Request.Trainer = Trainer.User " +
                        "WHERE Trainer = ? " +
                        LIMIT_30)){
            preparedStatement.setString(1, trainerFc);
            return preparedStatement.executeQuery();
        }
    }

    public static ResultSet loadRequest(int idRequest) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                SELECT_ALL +
                        "FROM mydb.Request " +
                        "WHERE idRequest = ?")){
            preparedStatement.setInt(1, idRequest);
            return preparedStatement.executeQuery();
        }
    }

    public static void deleteRequest(int idRequest) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "DELETE FROM mydb.Request " +
                        "WHERE idRequest = ?")){
            preparedStatement.setInt(1, idRequest);
            preparedStatement.executeUpdate();
        }
    }

    public static void insertExerciseInWorkoutDay(int idExercise, int workoutDayKey) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "INSERT INTO mydb.Contains (WorkoutDay, Exercise) " +
                        "VALUES (?, ?)")){
            preparedStatement.setInt(1, workoutDayKey);
            preparedStatement.setInt(2, idExercise);
            preparedStatement.executeUpdate();
        }
    }

    public static int insertWorkoutDay(int workoutPlanKey, String day) throws SQLException, DBConnectionFailedException {
        try (PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "INSERT INTO mydb.WorkoutDay (WorkoutPlan, Day) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, workoutPlanKey);
            preparedStatement.setString(2, day);
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
                return -1;
            }
        }
    }

    public static ResultSet loadAllWorkoutDays(int idWorkoutPlan) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "SELECT WorkoutDay.* " +
                "FROM mydb.WorkoutDay " +
                "WHERE WorkoutDay.WorkoutPlan = ?")){
            preparedStatement.setInt(1, idWorkoutPlan);
            return preparedStatement.executeQuery();
        }
    }

    public static ResultSet loadAllExerciseInWorkoutDays(int idWorkoutDay) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "SELECT Exercise.* " +
                "FROM mydb.Contains join mydb.Exercise on Contains.Exercise = Exercise.idExercise " +
                "WHERE Contains.WorkoutDay = ?")){
            preparedStatement.setInt(1, idWorkoutDay);
            return preparedStatement.executeQuery();
        }
    }

    public static PreparedStatement insertWorkoutPlan(String athleteFc) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "INSERT INTO mydb.WorkoutPlan (Athlete) VALUES (?)",
                Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, athleteFc);
        return preparedStatement;
    }

    public static PreparedStatement removeWorkoutPlan(int idWorkoutPlan) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "DELETE FROM mydb.WorkoutPlan " +
                "WHERE idWorkoutPlan = ?");
        preparedStatement.setInt(1, idWorkoutPlan);
        return preparedStatement;
    }

    public static PreparedStatement insertCourseSubscriber(int idCourse, String athleteFc) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "INSERT INTO mydb.Subscribe (Course, Athlete) " +
                "VALUES (?, ?)");
        preparedStatement.setInt(1, idCourse);
        preparedStatement.setString(2, athleteFc);
        return preparedStatement;

    }

    public static PreparedStatement deleteCourseSubscriber(int idCourse, String athleteFc) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "DELETE FROM mydb.Subscribe " +
                "WHERE Course = ? and Athlete = ?");
        preparedStatement.setInt(1, idCourse);
        preparedStatement.setString(2, athleteFc);
        return preparedStatement;
    }

    public static PreparedStatement loadSubscribed(int idCourse) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                SELECT_ALL +
                "FROM mydb.Subscribe " +
                "WHERE Course = ?");
        preparedStatement.setInt(1, idCourse);
        return preparedStatement;
    }

    public static PreparedStatement getSubscribers(int idCourse) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "SELECT Count(*)" +
                "FROM mydb.Subscribe " +
                "WHERE Course = ?");
        preparedStatement.setInt(1, idCourse);
        return preparedStatement;
    }
}
