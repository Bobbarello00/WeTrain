package database;

import exception.DBConnectionFailedException;
import exception.runtime_exception.ResultSetIsNullException;
import model.*;
import model.record.Card;

import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public static final String WHERE_TRAINER = "WHERE Trainer = ?";
    public static final String FROM_MYDB_LESSON = "FROM mydb.Lesson ";

    public static ResultSet loadAllNotifications(User receiver) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                SELECT_ALL +
                "FROM mydb.Notification " +
                "WHERE Receiver = ? " +
                LIMIT_30)) {
            preparedStatement.setString(1, receiver.getFiscalCode());
            preparedStatement.closeOnCompletion();
            return preparedStatement.executeQuery();
        }
    }

    public static void insertNotification(int type, String info, LocalDateTime dateTime, String sender, String receiver) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "INSERT INTO mydb.Notification (Type, Info, NotificationDate, Sender, Receiver) " +
                "VALUES (?, ?, ?, ?, ?)")) {
            preparedStatement.setInt(1, type);
            preparedStatement.setString(2, info);
            preparedStatement.setTimestamp(3, Timestamp.valueOf(dateTime));
            preparedStatement.setString(4, sender);
            preparedStatement.setString(5, receiver);
            preparedStatement.executeUpdate();
        }
    }

    public static void deleteNotification(int idNotification) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                    "DELETE FROM mydb.Notification " +
                            "WHERE idNotification = ?")) {
            preparedStatement.setInt(1, idNotification);
            preparedStatement.executeUpdate();
        }
    }

    public static ResultSet loadAthlete(String fc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                SELECT_ALL +
                        FROM_MYDB_ATHLETE +
                        WHERE_USER)) {
            preparedStatement.setString(1, fc);
            return preparedStatement.executeQuery();
        }
    }

    public static void insertAthlete(Athlete athlete) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "INSERT INTO mydb.User (FC, Name, Surname, Username, Birth, Email, Gender, Password) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            PreparedStatement preparedStatement1 = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                    "INSERT INTO mydb.Athlete (User) VALUES (?)")) {
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
    }

    public static void updateCardInfoAthlete(Athlete athlete, Card card) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                UPDATE_MYDB_ATHLETE +
                "SET CardNumber = ?, CardExpirationDate = ? " +
                WHERE_USER)) {
            preparedStatement.setString(1, card.cardNumber());
            preparedStatement.setDate(2, Date.valueOf((card.cardExpirationDate()).atDay(1)));
            preparedStatement.setString(3, athlete.getFiscalCode());
            preparedStatement.executeUpdate();
        }
    }


    public static void removeCardInfoAthlete(String fc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                UPDATE_MYDB_ATHLETE +
                "SET CardNumber = NULL, CardExpirationDate = NULL " +
                WHERE_USER)) {
            preparedStatement.setString(1, fc);
            preparedStatement.executeUpdate();
        }
    }

    public static void updateTrainerAthlete(String athleteFc, String trainerFc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                UPDATE_MYDB_ATHLETE +
                "SET Trainer = ? " +
                WHERE_USER)) {
            preparedStatement.setString(1, trainerFc);
            preparedStatement.setString(2, athleteFc);
            preparedStatement.executeUpdate();
        }
    }

    public static void removeTrainerAthlete(String athleteFc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                UPDATE_MYDB_ATHLETE +
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

    public static void deleteUser(String userFc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "DELETE FROM mydb.User " +
                "WHERE FC = ?")) {
                preparedStatement.setString(1, userFc);
                preparedStatement.executeUpdate();
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
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                SELECT_ALL +
                "FROM mydb.Trainer join mydb.Athlete on Athlete.Trainer = Trainer.User " +
                "GROUP BY Trainer.User " +
                "ORDER BY COUNT(Athlete.User) DESC " +
                LIMIT_10)) {
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
                LIMIT_10)) {
            String myString = "%%" + name + "%%";
            preparedStatement.setString(1, myString);
            preparedStatement.setString(2, myString);
            return preparedStatement.executeQuery();
        }
    }

    public static void insertTrainer(Trainer trainer) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "INSERT INTO mydb.User (FC, Name, Surname, Username, Birth, Gender, Email, Password) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            PreparedStatement preparedStatement1 = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                    "INSERT INTO mydb.Trainer (User) VALUES (?)");) {
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
    }

    public static void updateIbanTrainer(Trainer trainer) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "UPDATE mydb.Trainer SET Iban = ? " +
                WHERE_USER)) {
            preparedStatement.setString(1, trainer.getIban());
            preparedStatement.setString(2, trainer.getFiscalCode());
            preparedStatement.executeUpdate();
        }
    }

    public static ResultSet countTrainerSubscribers(String trainerFc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "SELECT COUNT(*) " +
                        FROM_MYDB_ATHLETE +
                        WHERE_TRAINER)) {
            preparedStatement.setString(1, trainerFc);
            return preparedStatement.executeQuery();
        }
    }

    public static ResultSet loadAllTrainerSubscribers(String trainerFc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                SELECT_ALL +
                        FROM_MYDB_ATHLETE +
                "WHERE Trainer = ? " +
                LIMIT_30)) {
            preparedStatement.setString(1, trainerFc);
            return preparedStatement.executeQuery();
        }
    }

    public static ResultSet loadUser(String email, String password) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                SELECT_ALL +
                        " FROM mydb.User " +
                        " WHERE Email = ? AND Password = ?")) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            return preparedStatement.executeQuery();
        }
    }

    public static ResultSet loadUser(String fc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                SELECT_ALL +
                        "FROM mydb.User " +
                        "WHERE FC = ?")) {
            preparedStatement.setString(1, fc);
            return preparedStatement.executeQuery();
        }
    }

    public static ResultSet loadCourse(int idCourse) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                SELECT_ALL +
                        FROM_MYDB_COURSE +
                        WHERE_ID_COURSE)) {
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
                LIMIT_10)) {
            return preparedStatement.executeQuery();
        }
    }

    public static ResultSet loadAllCoursesAthlete(String athleteFc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                SELECT_ALL +
                "FROM mydb.Course join mydb.Subscribe on Subscribe.Course = Course.idCourse " +
                "WHERE Subscribe.Athlete = ?")) {
            preparedStatement.setString(1, athleteFc);
            return preparedStatement.executeQuery();
        }
    }

    public static ResultSet loadAllCoursesTrainer(String trainerFc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                SELECT_ALL +
                        FROM_MYDB_COURSE +
                        WHERE_TRAINER)) {
            preparedStatement.setString(1, trainerFc);
            return preparedStatement.executeQuery();
        }
    }

    public static int insertCourse(Course course) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "INSERT INTO mydb.Course (Name, Description, FitnessLevel, Equipment, Trainer) " +
                        "VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, course.getName());
            preparedStatement.setString(2, course.getDescription());
            preparedStatement.setString(3, course.getFitnessLevel());
            preparedStatement.setString(4, course.getEquipment());
            preparedStatement.setString(5, course.getOwner().getFiscalCode());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new ResultSetIsNullException();
                }
            }
        }
    }

    public static ResultSet searchCourse(String name, String fitnessLevel, Boolean[] days) throws SQLException, DBConnectionFailedException {
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
                FROM_MYDB_LESSON +
                "WHERE Lesson.Course = Course.idCourse " +
                queryString + ")";
        String myString = "%%" + name + "%%";
        if (condition) {
            try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(SELECT_ALL +
                    FROM_MYDB_COURSE +
                    "WHERE Name LIKE ? " +
                    "AND FitnessLevel = ?")){
                preparedStatement.setString(1, myString);
                preparedStatement.setString(2, fitnessLevel);
                return preparedStatement.executeQuery();
            }
        } else {
            try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(SELECT_ALL +
                    FROM_MYDB_COURSE +
                    "WHERE Name LIKE ? " +
                    "AND FitnessLevel = ? " +
                    "AND NOT EXISTS " +
                    nestedQuery)){
                preparedStatement.setString(1, myString);
                preparedStatement.setString(2, fitnessLevel);
                preparedStatement.setString(3, dayStringList.get(index));
                return preparedStatement.executeQuery();
            }
        }
    }

    public static ResultSet loadStartedLessonUrl(int idLesson) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "SELECT StartedLessonUrl " +
                        FROM_MYDB_LESSON +
                        "WHERE idLesson = ?")) {
            preparedStatement.setInt(1, idLesson);
            return preparedStatement.executeQuery();
        }
    }

    public static void insertLessonStartedLessonUrl(int idLesson, String url) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "UPDATE mydb.Lesson " +
                        "SET StartedLessonUrl = ? " +
                        "WHERE idLesson = ?")) {
            preparedStatement.setString(1, url);
            preparedStatement.setInt(2, idLesson);
            preparedStatement.executeUpdate();
        }
    }

    public static void removeLessonStartedLessonUrl(int idCourse) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "UPDATE mydb.Lesson " +
                        "SET StartedLessonUrl = NULL " +
                        WHERE_COURSE)){
            preparedStatement.setInt(1, idCourse);
            preparedStatement.executeUpdate();
        }
    }

    public static void modifyCourse(int idCourse, Course course) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                UPDATE_MYDB_COURSE +
                        "SET Name = ?, Description = ?, FitnessLevel = ?, Equipment = ?, Trainer = ? " +
                        WHERE_ID_COURSE)){
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
                        WHERE_ID_COURSE)){
            preparedStatement.setInt(1, idCourse);
            preparedStatement.executeUpdate();
        }
    }

    public static ResultSet loadTrainerExercises(String trainerFc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "SELECT Exercise.* " +
                        "FROM mydb.Exercise " +
                        WHERE_TRAINER)){
            preparedStatement.setString(1, trainerFc);
            return preparedStatement.executeQuery();
        }
    }

    public static ResultSet insertExercise(Exercise exercise) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "INSERT INTO mydb.Exercise (Name, Info, Trainer) " +
                        "VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, exercise.getName());
            preparedStatement.setString(2, exercise.getInfo());
            preparedStatement.setString(3, exercise.getTrainer().getFiscalCode());
            preparedStatement.executeUpdate();
            return preparedStatement.getGeneratedKeys();
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

    public static ResultSet loadAllLessons(int idCourse) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                SELECT_ALL +
                        FROM_MYDB_LESSON +
                        WHERE_COURSE)) {
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
                        LIMIT_30)) {
            preparedStatement.setString(1, trainerFc);
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
                        "VALUES (?, ?)")) {
            preparedStatement.setInt(1, workoutDayKey);
            preparedStatement.setInt(2, idExercise);
            preparedStatement.executeUpdate();
        }
    }

    public static ResultSet insertWorkoutDay(int workoutPlanKey, String day) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "INSERT INTO mydb.WorkoutDay (WorkoutPlan, Day) VALUES (?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, workoutPlanKey);
            preparedStatement.setString(2, day);
            preparedStatement.executeUpdate();
            return preparedStatement.getGeneratedKeys();
        }
    }

    public static ResultSet loadAllWorkoutDays(int idWorkoutPlan) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "SELECT WorkoutDay.* " +
                "FROM mydb.WorkoutDay " +
                "WHERE WorkoutDay.WorkoutPlan = ?")) {
            preparedStatement.setInt(1, idWorkoutPlan);
            return preparedStatement.executeQuery();
        }
    }

    public static ResultSet loadAllExerciseInWorkoutDays(int idWorkoutDay) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "SELECT Exercise.* " +
                "FROM mydb.Contains join mydb.Exercise on Contains.Exercise = Exercise.idExercise " +
                "WHERE Contains.WorkoutDay = ?")) {
            preparedStatement.setInt(1, idWorkoutDay);
            return preparedStatement.executeQuery();
        }
    }

    public static ResultSet insertWorkoutPlan(String athleteFc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "INSERT INTO mydb.WorkoutPlan (Athlete) VALUES (?)",
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, athleteFc);
            preparedStatement.executeUpdate();
            return preparedStatement.getGeneratedKeys();
        }
    }

    public static void removeWorkoutPlan(int idWorkoutPlan) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "DELETE FROM mydb.WorkoutPlan " +
                "WHERE idWorkoutPlan = ?")) {
            preparedStatement.setInt(1, idWorkoutPlan);
            preparedStatement.executeUpdate();
        }
    }

    public static void insertCourseSubscriber(int idCourse, String athleteFc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "INSERT INTO mydb.Subscribe (Course, Athlete) " +
                "VALUES (?, ?)")){
            preparedStatement.setInt(1, idCourse);
            preparedStatement.setString(2, athleteFc);
            preparedStatement.executeUpdate();
        }
    }

    public static void deleteCourseSubscriber(int idCourse, String athleteFc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "DELETE FROM mydb.Subscribe " +
                "WHERE Course = ? and Athlete = ?")) {
            preparedStatement.setInt(1, idCourse);
            preparedStatement.setString(2, athleteFc);
            preparedStatement.executeUpdate();
        }
    }

    public static ResultSet loadSubscribed(int idCourse) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                SELECT_ALL +
                "FROM mydb.Subscribe " +
                        WHERE_COURSE)) {
            preparedStatement.setInt(1, idCourse);
            return preparedStatement.executeQuery();
        }
    }

    public static ResultSet getSubscribers(int idCourse) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                "SELECT Count(*)" +
                "FROM mydb.Subscribe " +
                        WHERE_COURSE)) {
            preparedStatement.setInt(1, idCourse);
            return preparedStatement.executeQuery();
        }
    }
}
