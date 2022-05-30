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


    public static final String loadAllNotificationsQuery = SELECT_ALL +
            "FROM mydb.Notification " +
            "WHERE Receiver = ? " +
            LIMIT_30;
    public static ResultSet loadAllNotifications(PreparedStatement preparedStatement, User receiver) throws SQLException, DBConnectionFailedException {
        preparedStatement.setString(1, receiver.getFiscalCode());
        preparedStatement.closeOnCompletion();
        return preparedStatement.executeQuery();
    }

    public static final String insertNotificationQuery = "INSERT INTO mydb.Notification (Type, Info, NotificationDate, Sender, Receiver) " +
            "VALUES (?, ?, ?, ?, ?)";
    public static void insertNotification(PreparedStatement preparedStatement, int type, String info, LocalDateTime dateTime, String sender, String receiver) throws SQLException, DBConnectionFailedException {
        preparedStatement.setInt(1, type);
        preparedStatement.setString(2, info);
        preparedStatement.setTimestamp(3, Timestamp.valueOf(dateTime));
        preparedStatement.setString(4, sender);
        preparedStatement.setString(5, receiver);
        preparedStatement.executeUpdate();
    }

    public static final String deleteNotificationQuery = "DELETE FROM mydb.Notification " +
            "WHERE idNotification = ?";
    public static void deleteNotification(PreparedStatement preparedStatement, int idNotification) throws SQLException, DBConnectionFailedException {
        preparedStatement.setInt(1, idNotification);
        preparedStatement.executeUpdate();
    }

    public static final String loadAthleteQuery = SELECT_ALL +
            FROM_MYDB_ATHLETE +
            WHERE_USER;
    public static ResultSet loadAthlete(PreparedStatement preparedStatement, String fc) throws SQLException, DBConnectionFailedException {
        preparedStatement.setString(1, fc);
        return preparedStatement.executeQuery();
    }

    public static final String insertAthleteQuery1 = "INSERT INTO mydb.User (FC, Name, Surname, Username, Birth, Email, Gender, Password) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String insertAthleteQuery2 = "INSERT INTO mydb.Athlete (User) VALUES (?)";
    public static void insertAthlete(PreparedStatement preparedStatement, PreparedStatement preparedStatement1, Athlete athlete) throws SQLException, DBConnectionFailedException {
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

    public static final String updateCardInfoAthleteQuery = UPDATE_MYDB_ATHLETE +
            "SET CardNumber = ?, CardExpirationDate = ? " +
            WHERE_USER;
    public static void updateCardInfoAthlete(PreparedStatement preparedStatement, Athlete athlete, Card card) throws SQLException, DBConnectionFailedException {
        preparedStatement.setString(1, card.cardNumber());
        preparedStatement.setDate(2, Date.valueOf((card.cardExpirationDate()).atDay(1)));
        preparedStatement.setString(3, athlete.getFiscalCode());
        preparedStatement.executeUpdate();
    }

    public static final String removeCardInfoAthleteQuery = UPDATE_MYDB_ATHLETE +
            "SET CardNumber = NULL, CardExpirationDate = NULL " +
            WHERE_USER;
    public static void removeCardInfoAthlete(PreparedStatement preparedStatement, String fc) throws SQLException, DBConnectionFailedException {
        preparedStatement.setString(1, fc);
        preparedStatement.executeUpdate();
    }

    public static final String updateTrainerAthleteQuery = UPDATE_MYDB_ATHLETE +
            "SET Trainer = ? " +
            WHERE_USER;
    public static void updateTrainerAthlete(PreparedStatement preparedStatement, String athleteFc, String trainerFc) throws SQLException, DBConnectionFailedException {
        preparedStatement.setString(1, trainerFc);
        preparedStatement.setString(2, athleteFc);
        preparedStatement.executeUpdate();
    }

    public static final String removeTrainerAthleteQuery = UPDATE_MYDB_ATHLETE +
            "SET Trainer = NULL " +
            WHERE_USER;
    public static void removeTrainerAthlete(String athleteFc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                removeTrainerAthleteQuery)) {
            preparedStatement.setString(1, athleteFc);
            preparedStatement.executeUpdate();
        }
    }

    public static final String countAthleteCoursesQuery = "SELECT COUNT(*) FROM mydb.Subscribe " +
            "WHERE Athlete = ?";
    public static ResultSet countAthleteCourses(String athleteFc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                countAthleteCoursesQuery)) {
            preparedStatement.setString(1, athleteFc);
            return preparedStatement.executeQuery();
        }
    }

    public static final String deleteUserQuery = "DELETE FROM mydb.User " +
            "WHERE FC = ?";
    public static void deleteUser(String userFc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                deleteUserQuery)) {
                preparedStatement.setString(1, userFc);
                preparedStatement.executeUpdate();
            }
        }

    public static final String addWorkoutPlanToAthleteQuery = "UPDATE mydb.Athlete SET WorkoutPlan = ? " +
            WHERE_USER;
    public static void addWorkoutPlanToAthlete(int idWorkoutPlan, String athleteFc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                addWorkoutPlanToAthleteQuery)) {
            preparedStatement.setInt(1, idWorkoutPlan);
            preparedStatement.setString(2, athleteFc);
            preparedStatement.executeUpdate();
        }
    }

    public static final String loadTrainerQuery = SELECT_ALL +
            "FROM mydb.Trainer " +
            WHERE_USER;
    public static ResultSet loadTrainer(String fc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                loadTrainerQuery)) {
            preparedStatement.setString(1, fc);
            return preparedStatement.executeQuery();
        }
    }

    public static final String loadAllTrainersQuery = SELECT_ALL +
            "FROM mydb.Trainer join mydb.Athlete on Athlete.Trainer = Trainer.User " +
            "GROUP BY Trainer.User " +
            "ORDER BY COUNT(Athlete.User) DESC " +
            LIMIT_10;
    public static ResultSet loadAllTrainers() throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                loadAllTrainersQuery)) {
            return preparedStatement.executeQuery();
        }
    }

    public static final String searchTrainerQuery = SELECT_ALL +
            "FROM mydb.Trainer join mydb.User on Trainer.User = User.FC " +
            "WHERE (Name LIKE ? OR Surname LIKE ?) " +
            "GROUP BY User " +
            "ORDER BY COUNT(User) DESC " +
            LIMIT_10;
    public static ResultSet searchTrainer(String name) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                searchTrainerQuery)) {
            String myString = "%%" + name + "%%";
            preparedStatement.setString(1, myString);
            preparedStatement.setString(2, myString);
            return preparedStatement.executeQuery();
        }
    }

    public static final String insertTrainerQuery1 = "INSERT INTO mydb.User (FC, Name, Surname, Username, Birth, Gender, Email, Password) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String insertTrainerQuery2 = "INSERT INTO mydb.Trainer (User) VALUES (?)";
    public static void insertTrainer(Trainer trainer) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                insertTrainerQuery1);
            PreparedStatement preparedStatement1 = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                    insertTrainerQuery2)) {
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

    public static final String updateIbanTrainerQuery = "UPDATE mydb.Trainer SET Iban = ? " +
            WHERE_USER;
    public static void updateIbanTrainer(Trainer trainer) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                updateIbanTrainerQuery)) {
            preparedStatement.setString(1, trainer.getIban());
            preparedStatement.setString(2, trainer.getFiscalCode());
            preparedStatement.executeUpdate();
        }
    }

    public static final String countTrainerSubscribersQuery = "SELECT COUNT(*) " +
            FROM_MYDB_ATHLETE +
            WHERE_TRAINER;
    public static ResultSet countTrainerSubscribers(String trainerFc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                countTrainerSubscribersQuery)) {
            preparedStatement.setString(1, trainerFc);
            return preparedStatement.executeQuery();
        }
    }

    public static final String loadAllTrainerSubscribersQuery = SELECT_ALL +
            FROM_MYDB_ATHLETE +
            "WHERE Trainer = ? " +
            LIMIT_30;
    public static ResultSet loadAllTrainerSubscribers(String trainerFc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                loadAllTrainerSubscribersQuery)) {
            preparedStatement.setString(1, trainerFc);
            return preparedStatement.executeQuery();
        }
    }
    
    public static final String loadUser1Query = SELECT_ALL +
            " FROM mydb.User " +
            " WHERE Email = ? AND Password = ?";
    public static ResultSet loadUser(String email, String password) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                loadUser1Query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            return preparedStatement.executeQuery();
        }
    }

    public static final String loadUser2Query = SELECT_ALL +
            "FROM mydb.User " +
            "WHERE FC = ?";
    public static ResultSet loadUser(String fc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                loadUser2Query)) {
            preparedStatement.setString(1, fc);
            return preparedStatement.executeQuery();
        }
    }

    public static final String loadCourseQuery = SELECT_ALL +
            FROM_MYDB_COURSE +
            WHERE_ID_COURSE;
    public static ResultSet loadCourse(int idCourse) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                loadCourseQuery)) {
            preparedStatement.setInt(1, idCourse);
            return preparedStatement.executeQuery();
        }
    }

    public static final String loadPopularCourseQuery = "SELECT Course.* " +
            "FROM mydb.Course join mydb.Subscribe on Course.idCourse = Subscribe.Course " +
            "GROUP BY idCourse " +
            "ORDER BY COUNT(idCourse) DESC " +
            LIMIT_10;
    public static ResultSet loadPopularCourse() throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                loadPopularCourseQuery)) {
            return preparedStatement.executeQuery();
        }
    }

    public static final String loadAllCoursesAthleteQuery = SELECT_ALL +
            "FROM mydb.Course join mydb.Subscribe on Subscribe.Course = Course.idCourse " +
            "WHERE Subscribe.Athlete = ?";
    public static ResultSet loadAllCoursesAthlete(String athleteFc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                loadAllCoursesAthleteQuery)) {
            preparedStatement.setString(1, athleteFc);
            return preparedStatement.executeQuery();
        }
    }

    public static final String loadAllCoursesTrainerQuery = SELECT_ALL +
            FROM_MYDB_COURSE +
            WHERE_TRAINER;
    public static ResultSet loadAllCoursesTrainer(String trainerFc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                loadAllCoursesTrainerQuery)) {
            preparedStatement.setString(1, trainerFc);
            return preparedStatement.executeQuery();
        }
    }

    public static final String insertCourseQuery = "INSERT INTO mydb.Course (Name, Description, FitnessLevel, Equipment, Trainer) " +
            "VALUES (?, ?, ?, ?, ?)";
    public static int insertCourse(Course course) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                insertCourseQuery, Statement.RETURN_GENERATED_KEYS)) {
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

    public static final String loadStartedLessonUrlQuery = "SELECT StartedLessonUrl " +
            FROM_MYDB_LESSON +
            "WHERE idLesson = ?";
    public static ResultSet loadStartedLessonUrl(int idLesson) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                loadStartedLessonUrlQuery)) {
            preparedStatement.setInt(1, idLesson);
            return preparedStatement.executeQuery();
        }
    }

    public static final String insertLessonStartedLessonUrlQuery = "UPDATE mydb.Lesson " +
            "SET StartedLessonUrl = ? " +
            "WHERE idLesson = ?";
    public static void insertLessonStartedLessonUrl(int idLesson, String url) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                insertLessonStartedLessonUrlQuery)) {
            preparedStatement.setString(1, url);
            preparedStatement.setInt(2, idLesson);
            preparedStatement.executeUpdate();
        }
    }

    public static final String removeLessonStartedLessonUrlQuery = "UPDATE mydb.Lesson " +
            "SET StartedLessonUrl = NULL " +
            WHERE_COURSE;
    public static void removeLessonStartedLessonUrl(int idCourse) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                removeLessonStartedLessonUrlQuery)){
            preparedStatement.setInt(1, idCourse);
            preparedStatement.executeUpdate();
        }
    }

    public static final String modifyCourseQuery = UPDATE_MYDB_COURSE +
            "SET Name = ?, Description = ?, FitnessLevel = ?, Equipment = ?, Trainer = ? " +
            WHERE_ID_COURSE;
    public static void modifyCourse(int idCourse, Course course) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                modifyCourseQuery)){
            preparedStatement.setString(1, course.getName());
            preparedStatement.setString(2, course.getDescription());
            preparedStatement.setString(3, course.getFitnessLevel());
            preparedStatement.setString(4, course.getEquipment());
            preparedStatement.setString(5, course.getOwner().getFiscalCode());
            preparedStatement.setInt(6, idCourse);
            preparedStatement.executeUpdate();
        }
    }

    public static final String deleteCourseQuery = "DELETE FROM mydb.Course " +
            WHERE_ID_COURSE;
    public static void deleteCourse(int idCourse) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                deleteCourseQuery)){
            preparedStatement.setInt(1, idCourse);
            preparedStatement.executeUpdate();
        }
    }

    public static final String loadTrainerExercisesQuery = "SELECT Exercise.* " +
            "FROM mydb.Exercise " +
            WHERE_TRAINER;
    public static ResultSet loadTrainerExercises(String trainerFc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                loadTrainerExercisesQuery)){
            preparedStatement.setString(1, trainerFc);
            return preparedStatement.executeQuery();
        }
    }

    public static final String insertExerciseQuery = "INSERT INTO mydb.Exercise (Name, Info, Trainer) " +
            "VALUES (?, ?, ?)";
    public static ResultSet insertExercise(Exercise exercise) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                insertExerciseQuery,
                Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, exercise.getName());
            preparedStatement.setString(2, exercise.getInfo());
            preparedStatement.setString(3, exercise.getTrainer().getFiscalCode());
            preparedStatement.executeUpdate();
            return preparedStatement.getGeneratedKeys();
        }
    }

    public static final String deleteExerciseQuery = "DELETE FROM mydb.Exercise " +
            "WHERE idExercise = ?";
    public static void deleteExercise(int idExercise) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                deleteExerciseQuery)){
            preparedStatement.setInt(1, idExercise);
            preparedStatement.executeUpdate();
        }
    }

    public static final String loadAllLessonsQuery = SELECT_ALL +
            FROM_MYDB_LESSON +
            WHERE_COURSE;
    public static ResultSet loadAllLessons(int idCourse) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                loadAllLessonsQuery)) {
            preparedStatement.setInt(1, idCourse);
            return preparedStatement.executeQuery();
        }
    }

    public static final String insertLessonQuery = "INSERT INTO mydb.Lesson (LessonDay, LessonStartTime, LessonEndTime, Course) " +
            "VALUES (?, ?, ?, ?)";
    public static void insertLesson(Lesson lesson, int idCourse) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                insertLessonQuery)){
            preparedStatement.setString(1, lesson.getLessonDay());
            preparedStatement.setString(2, lesson.getLessonStartTime().toString());
            preparedStatement.setString(3, lesson.getLessonEndTime().toString());
            preparedStatement.setInt(4, idCourse);
            preparedStatement.executeUpdate();
        }
    }

    public static final String insertRequestQuery = "INSERT INTO mydb.Request (RequestDate, Info, Athlete, Trainer) " +
            "VALUES (?, ?, ?, ?)";
    public static void insertRequest(LocalDateTime requestDate, String info, String athleteFc, String trainerFc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                insertRequestQuery)){
            preparedStatement.setTimestamp(1, Timestamp.valueOf(requestDate));
            preparedStatement.setString(2, info);
            preparedStatement.setString(3, athleteFc);
            preparedStatement.setString(4, trainerFc);
            preparedStatement.executeUpdate();
        }
    }

    public static final String loadTrainerRequestsQuery = "SELECT Request.* " +
            "FROM mydb.Request join mydb.Trainer on Request.Trainer = Trainer.User " +
            "WHERE Trainer = ? " +
            LIMIT_30;
    public static ResultSet loadTrainerRequests(String trainerFc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                loadTrainerRequestsQuery)) {
            preparedStatement.setString(1, trainerFc);
            return preparedStatement.executeQuery();
        }
    }

    public static final String deleteRequestQuery = "DELETE FROM mydb.Request " +
            "WHERE idRequest = ?";
    public static void deleteRequest(int idRequest) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                deleteRequestQuery)){
            preparedStatement.setInt(1, idRequest);
            preparedStatement.executeUpdate();
        }
    }

    public static final String insertExerciseInWorkoutDayQuery = "INSERT INTO mydb.Contains (WorkoutDay, Exercise) " +
            "VALUES (?, ?)";
    public static void insertExerciseInWorkoutDay(int idExercise, int workoutDayKey) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                insertExerciseInWorkoutDayQuery)) {
            preparedStatement.setInt(1, workoutDayKey);
            preparedStatement.setInt(2, idExercise);
            preparedStatement.executeUpdate();
        }
    }

    public static final String insertWorkoutDayQuery = "INSERT INTO mydb.WorkoutDay (WorkoutPlan, Day) VALUES (?, ?)";
    public static ResultSet insertWorkoutDay(int workoutPlanKey, String day) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                insertWorkoutDayQuery,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, workoutPlanKey);
            preparedStatement.setString(2, day);
            preparedStatement.executeUpdate();
            return preparedStatement.getGeneratedKeys();
        }
    }

    public static final String loadAllWorkoutDaysQuery = "SELECT WorkoutDay.* " +
            "FROM mydb.WorkoutDay " +
            "WHERE WorkoutDay.WorkoutPlan = ?";
    public static ResultSet loadAllWorkoutDays(int idWorkoutPlan) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                loadAllWorkoutDaysQuery)) {
            preparedStatement.setInt(1, idWorkoutPlan);
            return preparedStatement.executeQuery();
        }
    }

    public static final String loadAllExerciseInWorkoutDaysQuery = "SELECT Exercise.* " +
            "FROM mydb.Contains join mydb.Exercise on Contains.Exercise = Exercise.idExercise " +
            "WHERE Contains.WorkoutDay = ?";
    public static ResultSet loadAllExerciseInWorkoutDays(int idWorkoutDay) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                loadAllExerciseInWorkoutDaysQuery)) {
            preparedStatement.setInt(1, idWorkoutDay);
            return preparedStatement.executeQuery();
        }
    }

    public static final String insertWorkoutPlanQuery = "INSERT INTO mydb.WorkoutPlan (Athlete) VALUES (?)";
    public static ResultSet insertWorkoutPlan(String athleteFc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                insertWorkoutPlanQuery,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, athleteFc);
            preparedStatement.executeUpdate();
            return preparedStatement.getGeneratedKeys();
        }
    }

    public static final String removeWorkoutPlanQuery = "DELETE FROM mydb.WorkoutPlan " +
            "WHERE idWorkoutPlan = ?";
    public static void removeWorkoutPlan(int idWorkoutPlan) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                removeWorkoutPlanQuery)) {
            preparedStatement.setInt(1, idWorkoutPlan);
            preparedStatement.executeUpdate();
        }
    }

    public static final String insertCourseSubscriberQuery = "INSERT INTO mydb.Subscribe (Course, Athlete) " +
            "VALUES (?, ?)";
    public static void insertCourseSubscriber(int idCourse, String athleteFc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                insertCourseSubscriberQuery)){
            preparedStatement.setInt(1, idCourse);
            preparedStatement.setString(2, athleteFc);
            preparedStatement.executeUpdate();
        }
    }

    public static final String deleteCourseSubscriberQuery = "DELETE FROM mydb.Subscribe " +
            "WHERE Course = ? and Athlete = ?";
    public static void deleteCourseSubscriber(int idCourse, String athleteFc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                deleteCourseSubscriberQuery)) {
            preparedStatement.setInt(1, idCourse);
            preparedStatement.setString(2, athleteFc);
            preparedStatement.executeUpdate();
        }
    }

    public static final String loadSubscribedQuery = SELECT_ALL +
            "FROM mydb.Subscribe " +
            WHERE_COURSE;
    public static ResultSet loadSubscribed(int idCourse) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                loadSubscribedQuery)) {
            preparedStatement.setInt(1, idCourse);
            return preparedStatement.executeQuery();
        }
    }

    public static final String getSubscribersQuery = "SELECT Count(*)" +
            "FROM mydb.Subscribe " +
            WHERE_COURSE;
    public static ResultSet getSubscribers(int idCourse) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                getSubscribersQuery)) {
            preparedStatement.setInt(1, idCourse);
            return preparedStatement.executeQuery();
        }
    }
}
