package database;

import exception.DBConnectionFailedException;
import exception.runtime_exception.ResultSetIsNullException;
import model.*;
import model.record.Card;

import java.sql.*;
import java.time.LocalDateTime;
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


    public static final String LOAD_ALL_NOTIFICATIONS_QUERY = SELECT_ALL +
            "FROM mydb.Notification " +
            "WHERE Receiver = ? " +
            LIMIT_30;
    public static ResultSet loadAllNotifications(PreparedStatement preparedStatement, User receiver) throws SQLException, DBConnectionFailedException {
        preparedStatement.setString(1, receiver.getFiscalCode());
        preparedStatement.closeOnCompletion();
        return preparedStatement.executeQuery();
    }

    public static final String INSERT_NOTIFICATION_QUERY = "INSERT INTO mydb.Notification (Type, Info, NotificationDate, Sender, Receiver) " +
            "VALUES (?, ?, ?, ?, ?)";
    public static void insertNotification(PreparedStatement preparedStatement, int type, String info, LocalDateTime dateTime, String sender, String receiver) throws SQLException, DBConnectionFailedException {
        preparedStatement.setInt(1, type);
        preparedStatement.setString(2, info);
        preparedStatement.setTimestamp(3, Timestamp.valueOf(dateTime));
        preparedStatement.setString(4, sender);
        preparedStatement.setString(5, receiver);
        preparedStatement.executeUpdate();
    }

    public static final String DELETE_NOTIFICATION_QUERY = "DELETE FROM mydb.Notification " +
            "WHERE idNotification = ?";
    public static void deleteNotification(PreparedStatement preparedStatement, int idNotification) throws SQLException, DBConnectionFailedException {
        preparedStatement.setInt(1, idNotification);
        preparedStatement.executeUpdate();
    }

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

    public static final String UPDATE_CARD_INFO_ATHLETE_QUERY = UPDATE_MYDB_ATHLETE +
            "SET CardNumber = ?, CardExpirationDate = ? " +
            WHERE_USER;
    public static void updateCardInfoAthlete(PreparedStatement preparedStatement, Athlete athlete, Card card) throws SQLException, DBConnectionFailedException {
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
    public static void updateTrainerAthlete(PreparedStatement preparedStatement, String athleteFc, String trainerFc) throws SQLException, DBConnectionFailedException {
        preparedStatement.setString(1, trainerFc);
        preparedStatement.setString(2, athleteFc);
        preparedStatement.executeUpdate();
    }

    public static final String REMOVE_TRAINER_ATHLETE_QUERY = UPDATE_MYDB_ATHLETE +
            "SET Trainer = NULL " +
            WHERE_USER;
    public static void removeTrainerAthlete(PreparedStatement preparedStatement, String athleteFc) throws SQLException, DBConnectionFailedException {
        preparedStatement.setString(1, athleteFc);
        preparedStatement.executeUpdate();
    }

    public static final String COUNT_ATHLETE_COURSES_QUERY = "SELECT COUNT(*) FROM mydb.Subscribe " +
            "WHERE Athlete = ?";
    public static ResultSet countAthleteCourses(PreparedStatement preparedStatement, String athleteFc) throws SQLException, DBConnectionFailedException {
        preparedStatement.setString(1, athleteFc);
        return preparedStatement.executeQuery();
    }

    public static final String DELETE_USER_QUERY = "DELETE FROM mydb.User " +
            "WHERE FC = ?";
    public static void deleteUser(PreparedStatement preparedStatement, String userFc) throws SQLException, DBConnectionFailedException {
            preparedStatement.setString(1, userFc);
            preparedStatement.executeUpdate();
        }

    public static final String ADD_WORKOUT_PLAN_TO_ATHLETE_QUERY = "UPDATE mydb.Athlete SET WorkoutPlan = ? " +
            WHERE_USER;
    public static void addWorkoutPlanToAthlete(PreparedStatement preparedStatement, int idWorkoutPlan, String athleteFc) throws SQLException, DBConnectionFailedException {
        preparedStatement.setInt(1, idWorkoutPlan);
        preparedStatement.setString(2, athleteFc);
        preparedStatement.executeUpdate();
    }

    public static final String LOAD_TRAINER_QUERY = SELECT_ALL +
            "FROM mydb.Trainer " +
            WHERE_USER;
    public static ResultSet loadTrainer(PreparedStatement preparedStatement, String fc) throws SQLException, DBConnectionFailedException {
        preparedStatement.setString(1, fc);
        return preparedStatement.executeQuery();
    }

    public static final String LOAD_ALL_TRAINERS_QUERY = SELECT_ALL +
            "FROM mydb.Trainer join mydb.Athlete on Athlete.Trainer = Trainer.User " +
            "GROUP BY Trainer.User " +
            "ORDER BY COUNT(Athlete.User) DESC " +
            LIMIT_10;
    public static ResultSet loadAllTrainers(PreparedStatement preparedStatement) throws SQLException, DBConnectionFailedException {
        return preparedStatement.executeQuery();
    }

    public static final String SEARCH_TRAINER_QUERY = SELECT_ALL +
            "FROM mydb.Trainer join mydb.User on Trainer.User = User.FC " +
            "WHERE (Name LIKE ? OR Surname LIKE ?) " +
            "GROUP BY User " +
            "ORDER BY COUNT(User) DESC " +
            LIMIT_10;
    public static ResultSet searchTrainer(PreparedStatement preparedStatement, String name) throws SQLException, DBConnectionFailedException {
        String myString = "%%" + name + "%%";
        preparedStatement.setString(1, myString);
        preparedStatement.setString(2, myString);
        return preparedStatement.executeQuery();
    }

    public static final String INSERT_TRAINER_QUERY_1 = "INSERT INTO mydb.User (FC, Name, Surname, Username, Birth, Gender, Email, Password) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String INSERT_TRAINER_QUERY_2 = "INSERT INTO mydb.Trainer (User) VALUES (?)";
    public static void insertTrainer(PreparedStatement preparedStatement, PreparedStatement preparedStatement1, Trainer trainer) throws SQLException, DBConnectionFailedException {
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

    public static final String UPDATE_IBAN_TRAINER_QUERY = "UPDATE mydb.Trainer SET Iban = ? " +
            WHERE_USER;
    public static void updateIbanTrainer(PreparedStatement preparedStatement, Trainer trainer) throws SQLException, DBConnectionFailedException {
        preparedStatement.setString(1, trainer.getIban());
        preparedStatement.setString(2, trainer.getFiscalCode());
        preparedStatement.executeUpdate();
    }

    public static final String COUNT_TRAINER_SUBSCRIBERS_QUERY = "SELECT COUNT(*) " +
            FROM_MYDB_ATHLETE +
            WHERE_TRAINER;
    public static ResultSet countTrainerSubscribers(PreparedStatement preparedStatement, String trainerFc) throws SQLException, DBConnectionFailedException {
        preparedStatement.setString(1, trainerFc);
        return preparedStatement.executeQuery();
    }

    public static final String LOAD_ALL_TRAINER_SUBSCRIBERS_QUERY = SELECT_ALL +
            FROM_MYDB_ATHLETE +
            "WHERE Trainer = ? " +
            LIMIT_30;
    public static ResultSet loadAllTrainerSubscribers(PreparedStatement preparedStatement, String trainerFc) throws SQLException, DBConnectionFailedException {
        preparedStatement.setString(1, trainerFc);
        return preparedStatement.executeQuery();
    }
    
    public static final String LOAD_USER_1_QUERY = SELECT_ALL +
            " FROM mydb.User " +
            " WHERE Email = ? AND Password = ?";
    public static ResultSet loadUser(PreparedStatement preparedStatement, String email, String password) throws SQLException, DBConnectionFailedException {
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        return preparedStatement.executeQuery();
    }

    public static final String LOAD_USER_2_QUERY = SELECT_ALL +
            "FROM mydb.User " +
            "WHERE FC = ?";
    public static ResultSet loadUser(PreparedStatement preparedStatement, String fc) throws SQLException, DBConnectionFailedException {
        preparedStatement.setString(1, fc);
        return preparedStatement.executeQuery();
    }

    public static final String LOAD_COURSE_QUERY = SELECT_ALL +
            FROM_MYDB_COURSE +
            WHERE_ID_COURSE;
    public static ResultSet loadCourse(PreparedStatement preparedStatement, int idCourse) throws SQLException, DBConnectionFailedException {
        preparedStatement.setInt(1, idCourse);
        return preparedStatement.executeQuery();
    }

    public static final String LOAD_POPULAR_COURSE_QUERY = "SELECT Course.* " +
            "FROM mydb.Course join mydb.Subscribe on Course.idCourse = Subscribe.Course " +
            "GROUP BY idCourse " +
            "ORDER BY COUNT(idCourse) DESC " +
            LIMIT_10;
    public static ResultSet loadPopularCourse(PreparedStatement preparedStatement) throws SQLException, DBConnectionFailedException {
        return preparedStatement.executeQuery();
    }

    public static final String LOAD_ALL_COURSES_ATHLETE_QUERY = SELECT_ALL +
            "FROM mydb.Course join mydb.Subscribe on Subscribe.Course = Course.idCourse " +
            "WHERE Subscribe.Athlete = ?";
    public static ResultSet loadAllCoursesAthlete(PreparedStatement preparedStatement, String athleteFc) throws SQLException, DBConnectionFailedException {
        preparedStatement.setString(1, athleteFc);
        return preparedStatement.executeQuery();
    }

    public static final String LOAD_ALL_COURSES_TRAINER_QUERY = SELECT_ALL +
            FROM_MYDB_COURSE +
            WHERE_TRAINER;
    public static ResultSet loadAllCoursesTrainer(PreparedStatement preparedStatement, String trainerFc) throws SQLException, DBConnectionFailedException {
        preparedStatement.setString(1, trainerFc);
        return preparedStatement.executeQuery();
    }

    public static final String INSERT_COURSE_QUERY = "INSERT INTO mydb.Course (Name, Description, FitnessLevel, Equipment, Trainer) " +
            "VALUES (?, ?, ?, ?, ?)";
    public static int insertCourse(PreparedStatement preparedStatement, Course course) throws SQLException, DBConnectionFailedException {
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

    public static final String SEARCH_COURSE_QUERY_QUERY_STRING = "AND Lesson.LessonDay != ? ";
    public static final String SEARCH_COURSE_QUERY_NESTED_QUERY = "(SELECT * " +
            FROM_MYDB_LESSON +
            "WHERE Lesson.Course = Course.idCourse ";
    public static final String SEARCH_COURSE_QUERY_FALSE = SELECT_ALL +
            FROM_MYDB_COURSE +
            "WHERE Name LIKE ? " +
            "AND FitnessLevel = ? " +
            "AND NOT EXISTS ";
    public static final String SEARCH_COURSE_QUERY_TRUE = SELECT_ALL +
            FROM_MYDB_COURSE +
            "WHERE Name LIKE ? " +
            "AND FitnessLevel = ?";
    public static ResultSet searchCourse(PreparedStatement preparedStatement, String name, String fitnessLevel, Boolean[] days, boolean condition, int index, List<String> dayStringList) throws SQLException, DBConnectionFailedException {
        String myString = "%%" + name + "%%";
        if (condition) {
            preparedStatement.setString(1, myString);
            preparedStatement.setString(2, fitnessLevel);
        } else {
            preparedStatement.setString(1, myString);
            preparedStatement.setString(2, fitnessLevel);
            preparedStatement.setString(3, dayStringList.get(index));
        }
        return preparedStatement.executeQuery();
    }

    public static final String LOAD_STARTED_LESSON_URL_QUERY = "SELECT StartedLessonUrl " +
            FROM_MYDB_LESSON +
            "WHERE idLesson = ?";
    public static ResultSet loadStartedLessonUrl(PreparedStatement preparedStatement, int idLesson) throws SQLException, DBConnectionFailedException {
        preparedStatement.setInt(1, idLesson);
        return preparedStatement.executeQuery();
    }

    public static final String INSERT_LESSON_STARTED_LESSON_URL_QUERY = "UPDATE mydb.Lesson " +
            "SET StartedLessonUrl = ? " +
            "WHERE idLesson = ?";
    public static void insertLessonStartedLessonUrl(PreparedStatement preparedStatement, int idLesson, String url) throws SQLException, DBConnectionFailedException {
        preparedStatement.setString(1, url);
        preparedStatement.setInt(2, idLesson);
        preparedStatement.executeUpdate();
    }

    public static final String REMOVE_LESSON_STARTED_LESSON_URL_QUERY = "UPDATE mydb.Lesson " +
            "SET StartedLessonUrl = NULL " +
            WHERE_COURSE;
    public static void removeLessonStartedLessonUrl(PreparedStatement preparedStatement, int idCourse) throws SQLException, DBConnectionFailedException {
        preparedStatement.setInt(1, idCourse);
        preparedStatement.executeUpdate();
    }

    public static final String MODIFY_COURSE_QUERY = UPDATE_MYDB_COURSE +
            "SET Name = ?, Description = ?, FitnessLevel = ?, Equipment = ?, Trainer = ? " +
            WHERE_ID_COURSE;
    public static void modifyCourse(PreparedStatement preparedStatement, int idCourse, Course course) throws SQLException, DBConnectionFailedException {
        preparedStatement.setString(1, course.getName());
        preparedStatement.setString(2, course.getDescription());
        preparedStatement.setString(3, course.getFitnessLevel());
        preparedStatement.setString(4, course.getEquipment());
        preparedStatement.setString(5, course.getOwner().getFiscalCode());
        preparedStatement.setInt(6, idCourse);
        preparedStatement.executeUpdate();
    }

    public static final String DELETE_COURSE_QUERY = "DELETE FROM mydb.Course " +
            WHERE_ID_COURSE;
    public static void deleteCourse(PreparedStatement preparedStatement, int idCourse) throws SQLException, DBConnectionFailedException {
        preparedStatement.setInt(1, idCourse);
        preparedStatement.executeUpdate();
    }

    public static final String LOAD_TRAINER_EXERCISES_QUERY = "SELECT Exercise.* " +
            "FROM mydb.Exercise " +
            WHERE_TRAINER;
    public static ResultSet loadTrainerExercises(PreparedStatement preparedStatement, String trainerFc) throws SQLException, DBConnectionFailedException {
        preparedStatement.setString(1, trainerFc);
        return preparedStatement.executeQuery();
    }

    public static final String INSERT_EXERCISE_QUERY = "INSERT INTO mydb.Exercise (Name, Info, Trainer) " +
            "VALUES (?, ?, ?)";
    public static ResultSet insertExercise(PreparedStatement preparedStatement, Exercise exercise) throws SQLException, DBConnectionFailedException {
        preparedStatement.setString(1, exercise.getName());
        preparedStatement.setString(2, exercise.getInfo());
        preparedStatement.setString(3, exercise.getTrainer().getFiscalCode());
        preparedStatement.executeUpdate();
        return preparedStatement.getGeneratedKeys();
    }

    public static final String DELETE_EXERCISE_QUERY = "DELETE FROM mydb.Exercise " +
            "WHERE idExercise = ?";
    public static void deleteExercise(PreparedStatement preparedStatement, int idExercise) throws SQLException, DBConnectionFailedException {
        preparedStatement.setInt(1, idExercise);
        preparedStatement.executeUpdate();
    }

    public static final String LOAD_ALL_LESSONS_QUERY = SELECT_ALL +
            FROM_MYDB_LESSON +
            WHERE_COURSE;
    public static ResultSet loadAllLessons(PreparedStatement preparedStatement, int idCourse) throws SQLException, DBConnectionFailedException {
        preparedStatement.setInt(1, idCourse);
        return preparedStatement.executeQuery();
    }

    public static final String INSERT_LESSON_QUERY = "INSERT INTO mydb.Lesson (LessonDay, LessonStartTime, LessonEndTime, Course) " +
            "VALUES (?, ?, ?, ?)";
    public static void insertLesson(PreparedStatement preparedStatement, Lesson lesson, int idCourse) throws SQLException, DBConnectionFailedException {
        preparedStatement.setString(1, lesson.getLessonDay());
        preparedStatement.setString(2, lesson.getLessonStartTime().toString());
        preparedStatement.setString(3, lesson.getLessonEndTime().toString());
        preparedStatement.setInt(4, idCourse);
        preparedStatement.executeUpdate();
    }

    public static final String INSERT_REQUEST_QUERY = "INSERT INTO mydb.Request (RequestDate, Info, Athlete, Trainer) " +
            "VALUES (?, ?, ?, ?)";
    public static void insertRequest(PreparedStatement preparedStatement, LocalDateTime requestDate, String info, String athleteFc, String trainerFc) throws SQLException, DBConnectionFailedException {
        preparedStatement.setTimestamp(1, Timestamp.valueOf(requestDate));
        preparedStatement.setString(2, info);
        preparedStatement.setString(3, athleteFc);
        preparedStatement.setString(4, trainerFc);
        preparedStatement.executeUpdate();
    }

    public static final String LOAD_TRAINER_REQUESTS_QUERY = "SELECT Request.* " +
            "FROM mydb.Request join mydb.Trainer on Request.Trainer = Trainer.User " +
            "WHERE Trainer = ? " +
            LIMIT_30;
    public static ResultSet loadTrainerRequests(PreparedStatement preparedStatement, String trainerFc) throws SQLException, DBConnectionFailedException {
        preparedStatement.setString(1, trainerFc);
        return preparedStatement.executeQuery();
    }

    public static final String DELETE_REQUEST_QUERY = "DELETE FROM mydb.Request " +
            "WHERE idRequest = ?";
    public static void deleteRequest(PreparedStatement preparedStatement, int idRequest) throws SQLException, DBConnectionFailedException {
        preparedStatement.setInt(1, idRequest);
        preparedStatement.executeUpdate();
    }

    public static final String INSERT_EXERCISE_IN_WORKOUT_DAY_QUERY = "INSERT INTO mydb.Contains (WorkoutDay, Exercise) " +
            "VALUES (?, ?)";
    public static void insertExerciseInWorkoutDay(PreparedStatement preparedStatement, int idExercise, int workoutDayKey) throws SQLException, DBConnectionFailedException {
        preparedStatement.setInt(1, workoutDayKey);
        preparedStatement.setInt(2, idExercise);
        preparedStatement.executeUpdate();
    }

    public static final String INSERT_WORKOUT_DAY_QUERY = "INSERT INTO mydb.WorkoutDay (WorkoutPlan, Day) VALUES (?, ?)";
    public static ResultSet insertWorkoutDay(PreparedStatement preparedStatement, int workoutPlanKey, String day) throws SQLException, DBConnectionFailedException {
        preparedStatement.setInt(1, workoutPlanKey);
        preparedStatement.setString(2, day);
        preparedStatement.executeUpdate();
        return preparedStatement.getGeneratedKeys();
    }

    public static final String LOAD_ALL_WORKOUT_DAYS_QUERY = "SELECT WorkoutDay.* " +
            "FROM mydb.WorkoutDay " +
            "WHERE WorkoutDay.WorkoutPlan = ?";
    public static ResultSet loadAllWorkoutDays(PreparedStatement preparedStatement, int idWorkoutPlan) throws SQLException, DBConnectionFailedException {
        preparedStatement.setInt(1, idWorkoutPlan);
        return preparedStatement.executeQuery();
    }

    public static final String LOAD_ALL_EXERCISE_IN_WORKOUT_DAYS_QUERY = "SELECT Exercise.* " +
            "FROM mydb.Contains join mydb.Exercise on Contains.Exercise = Exercise.idExercise " +
            "WHERE Contains.WorkoutDay = ?";
    public static ResultSet loadAllExerciseInWorkoutDays(PreparedStatement preparedStatement, int idWorkoutDay) throws SQLException, DBConnectionFailedException {
        preparedStatement.setInt(1, idWorkoutDay);
        return preparedStatement.executeQuery();
    }

    public static final String INSERT_WORKOUT_PLAN_QUERY = "INSERT INTO mydb.WorkoutPlan (Athlete) VALUES (?)";
    public static ResultSet insertWorkoutPlan(PreparedStatement preparedStatement, String athleteFc) throws SQLException, DBConnectionFailedException {
        preparedStatement.setString(1, athleteFc);
        preparedStatement.executeUpdate();
        return preparedStatement.getGeneratedKeys();
    }

    public static final String REMOVE_WORKOUT_PLAN_QUERY = "DELETE FROM mydb.WorkoutPlan " +
            "WHERE idWorkoutPlan = ?";
    public static void removeWorkoutPlan(PreparedStatement preparedStatement, int idWorkoutPlan) throws DBConnectionFailedException, SQLException {
        preparedStatement.setInt(1, idWorkoutPlan);
        preparedStatement.executeUpdate();
    }

    public static final String INSERT_COURSE_SUBSCRIBER_QUERY = "INSERT INTO mydb.Subscribe (Course, Athlete) " +
            "VALUES (?, ?)";
    public static void insertCourseSubscriber(PreparedStatement preparedStatement, int idCourse, String athleteFc) throws SQLException, DBConnectionFailedException {
        preparedStatement.setInt(1, idCourse);
        preparedStatement.setString(2, athleteFc);
        preparedStatement.executeUpdate();
    }

    public static final String DELETE_COURSE_SUBSCRIBER_QUERY = "DELETE FROM mydb.Subscribe " +
            "WHERE Course = ? and Athlete = ?";
    public static void deleteCourseSubscriber(PreparedStatement preparedStatement, int idCourse, String athleteFc) throws SQLException, DBConnectionFailedException {
        preparedStatement.setInt(1, idCourse);
        preparedStatement.setString(2, athleteFc);
        preparedStatement.executeUpdate();
    }

    public static final String LOAD_SUBSCRIBED_QUERY = SELECT_ALL +
            "FROM mydb.Subscribe " +
            WHERE_COURSE;
    public static ResultSet loadSubscribed(PreparedStatement preparedStatement, int idCourse) throws SQLException, DBConnectionFailedException {
        preparedStatement.setInt(1, idCourse);
        return preparedStatement.executeQuery();
    }

    public static final String GET_SUBSCRIBERS_QUERY = "SELECT Count(*)" +
            "FROM mydb.Subscribe " +
            WHERE_COURSE;
    public static ResultSet getSubscribers(PreparedStatement preparedStatement, int idCourse) throws SQLException, DBConnectionFailedException {
        preparedStatement.setInt(1, idCourse);
        return preparedStatement.executeQuery();
    }
}
