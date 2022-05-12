package database;

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
    public static final String UPDATE_MYDB_ATHLETE = "UPDATE mydb.Athlete ";
    public static final String FROM_MYDB_COURSE = "FROM mydb.Course ";
    public static final String WHERE_ID_COURSE = "WHERE idCourse = ?";
    public static final String UPDATE_MYDB_COURSE = "UPDATE mydb.Course ";
    public static final String WHERE_COURSE = "WHERE Course = ?";
    public static final String FROM_MYDB_ATHLETE = "FROM mydb.Athlete ";

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
                        FROM_MYDB_ATHLETE +
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

    public static PreparedStatement updateCardInfoAthlete(Athlete athlete) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                UPDATE_MYDB_ATHLETE +
                "SET CardNumber = ?, CardExpirationDate = ? " +
                WHERE_USER);
        preparedStatement.setString(1, athlete.getCardNumber());
        preparedStatement.setDate(2, Date.valueOf((athlete.getCardExpirationDate()).atDay(1)));
        preparedStatement.setString(3, athlete.getFiscalCode());
        return preparedStatement;
    }


    public static PreparedStatement removeCardInfoAthlete(String fc) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                UPDATE_MYDB_ATHLETE +
                "SET CardNumber = NULL, CardExpirationDate = NULL " +
                WHERE_USER);
        preparedStatement.setString(1, fc);
        return preparedStatement;
    }

    public static PreparedStatement updateTrainerAthlete(String athleteFc, String trainerFc) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                UPDATE_MYDB_ATHLETE +
                "SET Trainer = ? " +
                WHERE_USER);
        preparedStatement.setString(1, trainerFc);
        preparedStatement.setString(2, athleteFc);
        return preparedStatement;
    }

    public static PreparedStatement removeTrainerAthlete(String athleteFc) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                UPDATE_MYDB_ATHLETE +
                "SET Trainer = NULL " +
                WHERE_USER);
        preparedStatement.setString(1, athleteFc);
        return preparedStatement;
    }

    public static PreparedStatement countAthleteCourses(String athleteFc) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "SELECT COUNT(*) FROM mydb.Subscribe " +
                "WHERE Athlete = ?");
        preparedStatement.setString(1, athleteFc);
        return preparedStatement;
    }

    public static PreparedStatement deleteAthlete(String athleteFc) throws SQLException, DBConnectionFailedException {
        //TODO non lo usiamo
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "DELETE FROM mydb.Athlete " +
                WHERE_USER);
        preparedStatement.setString(1, athleteFc);
        return preparedStatement;
    }

    public static PreparedStatement addWorkoutPlanToAthlete(int idWorkoutPlan, String athleteFc) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "UPDATE mydb.Athlete SET WorkoutPlan = ? " +
                WHERE_USER);
        preparedStatement.setInt(1, idWorkoutPlan);
        preparedStatement.setString(2, athleteFc);
        return preparedStatement;
    }

    public static PreparedStatement loadTrainer(String fc) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                SELECT_ALL +
                "FROM mydb.Trainer " +
                WHERE_USER);
        preparedStatement.setString(1, fc);
        return preparedStatement;
    }

    public static PreparedStatement loadAllTrainers() throws SQLException, DBConnectionFailedException {
        return DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                SELECT_ALL +
                "FROM mydb.Trainer join mydb.Athlete on Athlete.Trainer = Trainer.User " +
                "GROUP BY Trainer.User " +
                "ORDER BY COUNT(Athlete.User) DESC " +
                LIMIT_10);
    }

    public static PreparedStatement searchTrainer(String name) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                SELECT_ALL +
                "FROM mydb.Trainer join mydb.User on Trainer.User = User.FC " +
                "WHERE (Name LIKE ? OR Surname LIKE ?) " +
                "GROUP BY User " +
                "ORDER BY COUNT(User) DESC " +
                LIMIT_10);
        String myString = "%%" + name + "%%";
        preparedStatement.setString(1, myString);
        preparedStatement.setString(2, myString);
        return preparedStatement;
    }

    public static List<PreparedStatement> insertTrainer(Trainer trainer) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "INSERT INTO mydb.User (FC, Name, Surname, Username, Birth, Gender, Email, Password) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1, trainer.getFiscalCode());
        preparedStatement.setString(2, trainer.getName());
        preparedStatement.setString(3, trainer.getSurname());
        preparedStatement.setString(4, trainer.getUsername());
        preparedStatement.setDate(5, Date.valueOf(trainer.getDateOfBirth()));
        preparedStatement.setString(6, String.valueOf(trainer.getGender()));
        preparedStatement.setString(7, trainer.getEmail());
        preparedStatement.setString(8, trainer.getPassword());

        PreparedStatement preparedStatement1 = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "INSERT INTO mydb.Trainer (User) VALUES (?)");
        preparedStatement1.setString(1, trainer.getFiscalCode());
        preparedStatement1.executeUpdate();
        return Arrays.asList(preparedStatement, preparedStatement1);
    }

    public static PreparedStatement updateIbanTrainer(Trainer trainer) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "UPDATE mydb.Trainer SET Iban = ? " +
                WHERE_USER);
        preparedStatement.setString(1, trainer.getIban());
        preparedStatement.setString(2, trainer.getFiscalCode());
        return preparedStatement;
    }

    public static PreparedStatement countTrainerSubscribers(String trainerFc) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "SELECT COUNT(*) " +
                        FROM_MYDB_ATHLETE +
                        "WHERE Trainer = ?");
        preparedStatement.setString(1, trainerFc);
        return preparedStatement;
    }

    public static PreparedStatement loadAllTrainerSubscribers(String trainerFc) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                SELECT_ALL +
                        FROM_MYDB_ATHLETE +
                "WHERE Trainer = ? " +
                LIMIT_30);
        preparedStatement.setString(1, trainerFc);
        return preparedStatement;
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
                SELECT_ALL +
                        " FROM mydb.User " +
                        " WHERE Email = ? AND Password = ?");
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        return preparedStatement;
    }

    public static PreparedStatement loadUser(String fc) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                SELECT_ALL +
                        "FROM mydb.User " +
                        "WHERE FC = ?");
            preparedStatement.setString(1, fc);
            return preparedStatement;
    }

    public static PreparedStatement loadCourse(int idCourse) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                SELECT_ALL +
                        FROM_MYDB_COURSE +
                        WHERE_ID_COURSE);
        preparedStatement.setInt(1, idCourse);
        return preparedStatement;
    }

    public static PreparedStatement loadPopularCourse() throws SQLException, DBConnectionFailedException {
        return DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "SELECT Course.* " +
                "FROM mydb.Course join mydb.Subscribe on Course.idCourse = Subscribe.Course " +
                "GROUP BY idCourse " +
                "ORDER BY COUNT(idCourse) DESC " +
                LIMIT_10);
    }

    public static PreparedStatement loadAllCoursesAthlete(String athleteFc) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                SELECT_ALL +
                "FROM mydb.Course join mydb.Subscribe on Subscribe.Course = Course.idCourse " +
                "WHERE Subscribe.Athlete = ?");
        preparedStatement.setString(1, athleteFc);
        return preparedStatement;
    }

    public static PreparedStatement loadAllCoursesTrainer(String trainerFc) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                SELECT_ALL +
                        FROM_MYDB_COURSE +
                "WHERE Trainer = ?");
        preparedStatement.setString(1, trainerFc);
        return preparedStatement;
    }

    public static PreparedStatement insertCourse(Course course) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "INSERT INTO mydb.Course (Name, Description, FitnessLevel, Equipment, Trainer) " +
                        "VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, course.getName());
        preparedStatement.setString(2, course.getDescription());
        preparedStatement.setString(3, course.getFitnessLevel());
        preparedStatement.setString(4, course.getEquipment());
        preparedStatement.setString(5, course.getOwner().getFiscalCode());
        return preparedStatement;
    }

    public static PreparedStatement searchCourse(String name, String fitnessLevel, Boolean[] days) throws SQLException, DBConnectionFailedException {
        boolean condition = true;
        int index = 0;
        List<String> dayStringList = new ArrayList<>(7);
        for (int i = 1; i <= 7; i++) {
            dayStringList.add(DayOfWeek.of(i).name());
        }

        StringBuilder queryString = new StringBuilder();

        for (int i = 0; i < 7; i++) {
            if (Boolean.TRUE.equals(days[i])) {
                condition = false;
                index = i;
                queryString.append("AND Lesson.LessonDay != ? ");
            }
        }

        String nestedQuery = "(SELECT * " +
                "FROM mydb.Lesson " +
                "WHERE Lesson.Course = Course.idCourse " +
                queryString + ")";
        String myString = "%%" + name + "%%";
        PreparedStatement preparedStatement;
        if (condition) {
            preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(SELECT_ALL +
                    FROM_MYDB_COURSE +
                    "WHERE Name LIKE ? " +
                    "AND FitnessLevel = ?");
            preparedStatement.setString(1, myString);
            preparedStatement.setString(2, fitnessLevel);
        } else {
            preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(SELECT_ALL +
                    FROM_MYDB_COURSE +
                    "WHERE Name LIKE ? " +
                    "AND FitnessLevel = ? " +
                    "AND NOT EXISTS " +
                    nestedQuery);
            preparedStatement.setString(1, myString);
            preparedStatement.setString(2, fitnessLevel);
            preparedStatement.setString(3, dayStringList.get(index));
        }
        return preparedStatement;
    }

    public static PreparedStatement loadCourseStartedLessonUrl(int idCourse) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "SELECT StartedLessonUrl " +
                        FROM_MYDB_COURSE +
                        WHERE_ID_COURSE);
        preparedStatement.setInt(1, idCourse);
        return preparedStatement;
    }

    public static PreparedStatement insertCourseStartedLessonUrl(int idCourse, String url) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                UPDATE_MYDB_COURSE +
                        "SET StartedLessonUrl = ? " +
                        WHERE_ID_COURSE);
        preparedStatement.setString(1, url);
        preparedStatement.setInt(2, idCourse);
        return preparedStatement;
    }

    public static PreparedStatement removeCourseStartedLessonUrl(int idCourse) throws SQLException, DBConnectionFailedException {
        //TODO non lo usiamo
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                UPDATE_MYDB_COURSE +
                        "SET StartedLessonUrl = NULL " +
                        WHERE_ID_COURSE);
        preparedStatement.setInt(1, idCourse);
        return preparedStatement;
    }

    public static PreparedStatement modifyCourse(int idCourse, Course course) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                UPDATE_MYDB_COURSE +
                        "SET Name = ?, Description = ?, FitnessLevel = ?, Equipment = ?, Trainer = ? " +
                        WHERE_ID_COURSE);
        preparedStatement.setString(1, course.getName());
        preparedStatement.setString(2, course.getDescription());
        preparedStatement.setString(3, course.getFitnessLevel());
        preparedStatement.setString(4, course.getEquipment());
        preparedStatement.setString(5, course.getOwner().getFiscalCode());
        preparedStatement.setInt(6, idCourse);
        return preparedStatement;
    }

    public static PreparedStatement deleteCourse(int idCourse) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "DELETE FROM mydb.Course " +
                        WHERE_ID_COURSE);
        preparedStatement.setInt(1, idCourse);
        return preparedStatement;
    }

    public static PreparedStatement loadExercise(int idExercise) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                SELECT_ALL +
                        "FROM mydb.Exercise " +
                        "WHERE idExercise =?");
        preparedStatement.setInt(1, idExercise);
        return preparedStatement;
    }

    public static PreparedStatement loadTrainerExercises(String trainerFc) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "SELECT Exercise.* " +
                        "FROM mydb.Exercise join mydb.Trainer on Exercise.Trainer = ?");
        preparedStatement.setString(1, trainerFc);
        return preparedStatement;
    }

    public static PreparedStatement insertExercise(Exercise exercise) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "INSERT INTO mydb.Exercise (Name, Info, Trainer) " +
                        "VALUES (?, ?, ?)");
        preparedStatement.setString(1, exercise.getName());
        preparedStatement.setString(2, exercise.getInfo());
        preparedStatement.setString(3, exercise.getTrainer().getFiscalCode());
        return preparedStatement;
    }

    public static PreparedStatement deleteExercise(int idExercise) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "DELETE FROM mydb.Exercise " +
                        "WHERE idExercise = ?");
        preparedStatement.setInt(1, idExercise);
        return preparedStatement;
    }

    public static PreparedStatement loadAllLessons(int idCourse) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                SELECT_ALL +
                        "FROM mydb.Lesson " +
                        WHERE_COURSE);
        preparedStatement.setInt(1, idCourse);
        return preparedStatement;
    }

    public static PreparedStatement insertLesson(Lesson lesson, int idCourse) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "INSERT INTO mydb.Lesson (LessonDay, LessonStartTime, LessonEndTime, Course) " +
                        "VALUES (?, ?, ?, ?)");
        preparedStatement.setString(1, lesson.getLessonDay());
        preparedStatement.setString(2, lesson.getLessonStartTime().toString());
        preparedStatement.setString(3, lesson.getLessonEndTime().toString());
        preparedStatement.setInt(4, idCourse);
        return preparedStatement;
    }

    public static PreparedStatement insertRequest(LocalDateTime requestDate, String info, String athleteFc, String trainerFc) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "INSERT INTO mydb.Request (RequestDate, Info, Athlete, Trainer) " +
                        "VALUES (?, ?, ?, ?)");
        preparedStatement.setTimestamp(1, Timestamp.valueOf(requestDate));
        preparedStatement.setString(2, info);
        preparedStatement.setString(3, athleteFc);
        preparedStatement.setString(4, trainerFc);
        return preparedStatement;
    }

    public static PreparedStatement loadTrainerRequests(String trainerFc) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "SELECT Request.* " +
                        "FROM mydb.Request join mydb.Trainer on Request.Trainer = Trainer.User " +
                        "WHERE Trainer = ? " +
                        LIMIT_30);
        preparedStatement.setString(1, trainerFc);
        return preparedStatement;
    }

    public static PreparedStatement loadRequest(int idRequest) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                SELECT_ALL +
                        "FROM mydb.Request " +
                        "WHERE idRequest = ?");
        preparedStatement.setInt(1, idRequest);
        return preparedStatement;
    }

    public static PreparedStatement deleteRequest(int idRequest) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "DELETE FROM mydb.Request " +
                        "WHERE idRequest = ?");
        preparedStatement.setInt(1, idRequest);
        return preparedStatement;
    }

    public static PreparedStatement insertExerciseInWorkoutDay(int idExercise, int workoutDayKey) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "INSERT INTO mydb.Contains (WorkoutDay, Exercise) " +
                        "VALUES (?, ?)");
        preparedStatement.setInt(1, workoutDayKey);
        preparedStatement.setInt(2, idExercise);
        return preparedStatement;
    }

    public static PreparedStatement insertWorkoutDay(int workoutPlanKey, String day) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "INSERT INTO mydb.WorkoutDay (WorkoutPlan, Day) VALUES (?, ?)",
                Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, workoutPlanKey);
        preparedStatement.setString(2, day);
        return preparedStatement;
    }

    public static PreparedStatement loadAllWorkoutDays(int idWorkoutPlan) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "SELECT WorkoutDay.* " +
                "FROM mydb.WorkoutDay " +
                "WHERE WorkoutDay.WorkoutPlan = ?");
        preparedStatement.setInt(1, idWorkoutPlan);
        return preparedStatement;
    }

    public static PreparedStatement loadAllExerciseInWorkoutDays(int idWorkoutDay) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "SELECT Exercise.* " +
                "FROM mydb.Contains join mydb.Exercise on Contains.Exercise = Exercise.idExercise " +
                "WHERE Contains.WorkoutDay = ?");
        preparedStatement.setInt(1, idWorkoutDay);
        return preparedStatement;
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
                        WHERE_COURSE);
        preparedStatement.setInt(1, idCourse);
        return preparedStatement;
    }

    public static PreparedStatement getSubscribers(int idCourse) throws SQLException, DBConnectionFailedException {
        PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "SELECT Count(*)" +
                "FROM mydb.Subscribe " +
                        WHERE_COURSE);
        preparedStatement.setInt(1, idCourse);
        return preparedStatement;
    }
}
