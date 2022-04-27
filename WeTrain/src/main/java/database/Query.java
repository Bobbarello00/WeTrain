package database;

import com.mysql.cj.exceptions.*;
import com.mysql.cj.jdbc.exceptions.*;
import exception.DBConnectionFailedException;
import model.*;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class Query {

    private static final String SELECT_ALL = "SELECT * ";
    private static final String WHERE_USER = "WHERE User = '%s';";
    private static final String LIMIT_10 = "LIMIT 10;";
    private static final String LIMIT_50 = "LIMIT 50;";

    private Query(){}


    //TODO gestione duplicate record
    public static ResultSet loadAllNotifications(@NotNull Statement stmt, User user) throws SQLException {
        return stmt.executeQuery(String.format(SELECT_ALL +
                "FROM Notification " +
                "WHERE Athlete = '%s' or Trainer = '%s' " +
                LIMIT_50, user.getFiscalCode(), user.getFiscalCode()));
    }

    public static void insertNotification(Statement stmt, Notification notification) throws SQLException {
        if(notification.getUser() instanceof Athlete) {
            stmt.executeUpdate(String.format("INSERT INTO mydb.Notification (Type, Description, NotificationDate, Athlete) " +
                            "VALUES (%s,'%s','%s','%s');",
                    notification.getType(),
                    notification.getDescription(),
                    Timestamp.valueOf(notification.getNotificationDate()),
                    notification.getUser()));
        }
        else {
            stmt.executeUpdate(String.format("INSERT INTO mydb.Notification (Type, Description, NotificationDate, Trainer) " +
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
                " FROM mydb.Athlete " +
                WHERE_USER, fc));
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

    public static void updateCardInfoAthlete(Statement stmt, Athlete athlete) throws SQLException {
        stmt.executeUpdate(String.format("UPDATE mydb.Athlete " +
                        "SET CardNumber = '%s', CardExpirationDate = '%s' " +
                        WHERE_USER,
                athlete.getCardNumber(),
                Date.valueOf((athlete.getCardExpirationDate()).atDay(1)),
                athlete.getFiscalCode()));
    }


    public static void removeCardInfoAthlete(Statement stmt, String fc) throws SQLException {
        stmt.executeUpdate(String.format("UPDATE mydb.Athlete " +
                        "SET CardNumber = NULL, CardExpirationDate = NULL " +
                WHERE_USER,
                fc));
    }

    public static void updateTrainerAthlete(Statement stmt, String athleteFc, String trainerFc) throws SQLException {
        stmt.executeUpdate(String.format("UPDATE mydb.Athlete " +
                        "SET Trainer = '%s' " +
                        WHERE_USER, trainerFc, athleteFc));
    }

    public static void removeTrainerAthlete(Statement stmt, String athleteFc) throws SQLException {
        stmt.executeUpdate(String.format("UPDATE mydb.Athlete " +
                "SET Trainer = NULL " +
                WHERE_USER, athleteFc));
    }

    public static int deleteAthlete(Statement stmt, Athlete athlete) throws SQLException {
        return stmt.executeUpdate(String.format("DELETE FROM mydb.Athlete " +
                WHERE_USER, athlete.getFiscalCode()));
    }

    public static ResultSet loadTrainer(Statement stmt, String fc) throws SQLException {
        return stmt.executeQuery(String.format(SELECT_ALL +
                "FROM mydb.Trainer " +
                WHERE_USER, fc));
    }

    public static ResultSet loadAllTrainers(Statement stmt) throws SQLException {
        return stmt.executeQuery(SELECT_ALL +
                "FROM mydb.Trainer " +
                "GROUP BY User " +
                "ORDER BY COUNT(User) DESC " +
                LIMIT_10);
    }

    public static ResultSet searchTrainer(Statement stmt, String name) throws SQLException {
        return stmt.executeQuery(String.format(SELECT_ALL +
                "FROM mydb.Trainer join mydb.User on Trainer.User = User.FC " +
                "WHERE (Name LIKE '%%%s%%' OR Surname LIKE '%%%s%%') " +
                "GROUP BY User " +
                "ORDER BY COUNT(User) DESC " +
                LIMIT_10,
                name,
                name)
        );
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

    public static int updateIbanTrainer(Statement stmt, Trainer trainer) throws SQLException {
        return stmt.executeUpdate(String.format("UPDATE mydb.Trainer SET Iban = '%s' " + WHERE_USER,
                trainer.getIban(),
                trainer.getFiscalCode()));
    }

    public static int deleteTrainer(Statement stmt, Trainer trainer) throws SQLException {
        return stmt.executeUpdate(String.format("DELETE FROM mydb.Trainer " +
                WHERE_USER, trainer.getFiscalCode()));
    }

    public static ResultSet loadUser(Statement stmt, String email, String password) throws SQLException {
        return stmt.executeQuery(String.format(SELECT_ALL +
                " FROM mydb.User " +
                " WHERE Email = '%s' AND Password = '%s';", email, password));
    }

    public static ResultSet loadUser(Statement stmt, String fc) throws SQLException, DBConnectionFailedException {
        try {
            return stmt.executeQuery(String.format(SELECT_ALL +
                    "FROM mydb.User " +
                    "WHERE FC = '%s';", fc));
        } catch(CJCommunicationsException| CommunicationsException e) {
            throw new DBConnectionFailedException();
        }
    }

    public static ResultSet loadCourse(Statement stmt, int id) throws SQLException {
        return stmt.executeQuery(String.format(SELECT_ALL +
                "FROM mydb.Course " +
                "WHERE idCourse = %s;", id));
    }

    public static ResultSet loadPopularCourse(Statement stmt) throws SQLException {
        return stmt.executeQuery("SELECT Course.* " +
                "FROM mydb.Course join mydb.Subscribe on Course.idCourse = Subscribe.Course " +
                "GROUP BY idCourse " +
                "ORDER BY COUNT(idCourse) DESC " +
                LIMIT_10);
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

    public static int insertCourse(Course course) throws SQLException, DBConnectionFailedException {
        try (PreparedStatement statement2 = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(String.format("INSERT INTO mydb.Course (Name, Description, FitnessLevel, Equipment, Trainer) " +
                        "VALUES ('%s', '%s', '%s', '%s', '%s');",
                course.getName(),
                course.getDescription(),
                course.getFitnessLevel(),
                course.getEquipment(),
                course.getOwner().getFiscalCode()), Statement.RETURN_GENERATED_KEYS)) {
            statement2.executeUpdate();
            try (ResultSet generatedKeys = statement2.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
                return -1;
            }
        }
    }

    public static ResultSet searchCourse(Statement stmt, String name, String fitnessLevel, Boolean[] days) throws SQLException {
        boolean condition = true;
        List<String> dayStringList = new ArrayList<>(7);
        for(int i = 1; i <= 7; i++) {
            dayStringList.add(DayOfWeek.of(i).name());
        }

        StringBuilder queryString = new StringBuilder();

        for(int i = 0; i < 7; i++){
            if(days[i]){
                condition = false;
                queryString.append(String.format("AND Lesson.LessonDay != '%s' ", dayStringList.get(i)));
            }
        }

        String nestedQuery = "(SELECT * " +
                "FROM mydb.Lesson " +
                "WHERE Lesson.Course = Course.idCourse " +
                queryString +
                ")";
        if(condition){
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

    public static void insertLesson(Statement stmt, Lesson lesson, Course course) throws SQLException {
        stmt.executeUpdate(String.format("INSERT INTO mydb.Lesson (LessonDay, LessonStartTime, LessonEndTime, Course) " +
                        "VALUES ('%s', '%s', '%s', %s);",
                lesson.getLessonDay(),
                lesson.getLessonStartTime().toString(),
                lesson.getLessonEndTime().toString(),
                course.getId()));
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
                "FROM mydb.Request join mydb.Trainer on Request.Trainer = '%s' " +
                LIMIT_50, trainer.getFiscalCode()));
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

    public static int insertWorkoutDay(int workoutPlanKey) throws SQLException, DBConnectionFailedException {
        try (PreparedStatement statement2 = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(String.format("INSERT INTO mydb.WorkoutDay (WorkoutPlan) VALUES (%s);", workoutPlanKey), Statement.RETURN_GENERATED_KEYS)) {
            statement2.executeUpdate();
            try (ResultSet generatedKeys = statement2.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
                return -1;
            }
        }
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

    public static int insertWorkoutPlan(Athlete athlete) throws SQLException, DBConnectionFailedException {

        try(PreparedStatement statement = DatabaseConnectionSingleton.getInstance().getConn()
                .prepareStatement(String.format("INSERT INTO mydb.WorkoutPlan (Athlete) VALUES ('%s');",
                        athlete.getFiscalCode()),
                        Statement.RETURN_GENERATED_KEYS)) {
            if(statement.executeUpdate() == 0){
                System.out.println("Inserimento WorkoutPlan fallito.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
                return -1;
            }
        }
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

    public static void insertSubscribe(Statement stmt, Course course, Athlete athlete) throws SQLException {
        stmt.executeUpdate(String.format("INSERT INTO mydb.Subscribe (Course, Athlete) " +
                        "VALUES (%s, '%s');",
                course.getId(),
                athlete.getFiscalCode()));
    }

    public static int deleteSubscriber(Statement stmt, int idCourse, String athleteFc) throws SQLException {
        return stmt.executeUpdate(String.format("DELETE FROM mydb.Subscribe " +
                "WHERE Course = %s and Athlete = '%s';",
                idCourse,
                athleteFc));
    }

}
