package database.Queries;

import exceptions.runtime_exception.ResultSetIsNullException;
import models.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public abstract class Queries {

    protected static final String SELECT_ALL = "SELECT * ";
    protected static final String WHERE_USER = "WHERE User = ?";
    protected static final String LIMIT_10 = "LIMIT 10";
    protected static final String LIMIT_30 = "LIMIT 30";
    protected static final String UPDATE_MYDB_ATHLETE = "UPDATE mydb.Athlete ";
    protected static final String FROM_MYDB_COURSE = "FROM mydb.Course ";
    protected static final String WHERE_ID_COURSE = "WHERE idCourse = ?";
    protected static final String UPDATE_MYDB_COURSE = "UPDATE mydb.Course ";
    protected static final String WHERE_COURSE = "WHERE Course = ?";
    protected static final String FROM_MYDB_ATHLETE = "FROM mydb.Athlete ";
    protected static final String WHERE_TRAINER = "WHERE Trainer = ?";
    protected static final String FROM_MYDB_LESSON = "FROM mydb.Lesson ";

    public static final String DELETE_USER_QUERY = "DELETE FROM mydb.User " +
            "WHERE FC = ?";
    public static void deleteUser(PreparedStatement preparedStatement, String userFc) throws SQLException {
        preparedStatement.setString(1, userFc);
        preparedStatement.executeUpdate();
    }

    public static final String LOAD_TRAINER_QUERY = SELECT_ALL +
            "FROM mydb.Trainer " +
            WHERE_USER;
    public static ResultSet loadTrainer(String fc, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, fc);
        return preparedStatement.executeQuery();
    }

    public static final String LOAD_ALL_TRAINERS_QUERY = SELECT_ALL +
            "FROM mydb.Trainer join mydb.Athlete on Athlete.Trainer = Trainer.User " +
            "GROUP BY Trainer.User " +
            "ORDER BY COUNT(Athlete.User) DESC " +
            LIMIT_10;
    public static ResultSet loadAllTrainers(PreparedStatement preparedStatement) throws SQLException {
        return preparedStatement.executeQuery();
    }

    public static final String SEARCH_TRAINER_QUERY = SELECT_ALL +
            "FROM mydb.Trainer join mydb.User on Trainer.User = User.FC " +
            "WHERE (Name LIKE ? OR Surname LIKE ?) " +
            "GROUP BY User " +
            "ORDER BY COUNT(User) DESC " +
            LIMIT_10;
    public static ResultSet searchTrainer(PreparedStatement preparedStatement, String name) throws SQLException {
        String myString = "%%" + name + "%%";
        preparedStatement.setString(1, myString);
        preparedStatement.setString(2, myString);
        return preparedStatement.executeQuery();
    }

    public static final String INSERT_TRAINER_QUERY_1 = "INSERT INTO mydb.User (FC, Name, Surname, Username, Birth, Gender, Email, Password) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String INSERT_TRAINER_QUERY_2 = "INSERT INTO mydb.Trainer (User) VALUES (?)";
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

    public static final String UPDATE_IBAN_TRAINER_QUERY = "UPDATE mydb.Trainer SET Iban = ? " +
            WHERE_USER;
    public static void updateIbanTrainer(PreparedStatement preparedStatement, Trainer trainer) throws SQLException {
        preparedStatement.setString(1, trainer.getIban());
        preparedStatement.setString(2, trainer.getFiscalCode());
        preparedStatement.executeUpdate();
    }

    public static final String COUNT_TRAINER_SUBSCRIBERS_QUERY = "SELECT COUNT(*) " +
            FROM_MYDB_ATHLETE +
            WHERE_TRAINER;
    public static ResultSet countTrainerSubscribers(PreparedStatement preparedStatement, String trainerFc) throws SQLException {
        preparedStatement.setString(1, trainerFc);
        return preparedStatement.executeQuery();
    }

    public static final String LOAD_ALL_TRAINER_SUBSCRIBERS_QUERY = SELECT_ALL +
            FROM_MYDB_ATHLETE +
            "WHERE Trainer = ? " +
            LIMIT_30;
    public static ResultSet loadAllTrainerSubscribers(String trainerFc, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, trainerFc);
        return preparedStatement.executeQuery();
    }
    
    public static final String LOAD_USER_1_QUERY = SELECT_ALL +
            " FROM mydb.User " +
            " WHERE Email = ? AND Password = ?";
    public static ResultSet loadUser(PreparedStatement preparedStatement, String email, String password) throws SQLException {
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        return preparedStatement.executeQuery();
    }

    public static final String LOAD_USER_2_QUERY = SELECT_ALL +
            "FROM mydb.User " +
            "WHERE FC = ?";
    public static ResultSet loadUser(String fc, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, fc);
        return preparedStatement.executeQuery();
    }

    public static final String LOAD_COURSE_QUERY = SELECT_ALL +
            FROM_MYDB_COURSE +
            WHERE_ID_COURSE;
    public static ResultSet loadCourse(PreparedStatement preparedStatement, int idCourse) throws SQLException {
        preparedStatement.setInt(1, idCourse);
        return preparedStatement.executeQuery();
    }

    public static final String LOAD_POPULAR_COURSE_QUERY = "SELECT Course.* " +
            "FROM mydb.Course join mydb.Subscribe on Course.idCourse = Subscribe.Course " +
            "GROUP BY idCourse " +
            "ORDER BY COUNT(idCourse) DESC " +
            LIMIT_10;
    public static ResultSet loadPopularCourse(PreparedStatement preparedStatement) throws SQLException {
        return preparedStatement.executeQuery();
    }

    public static final String LOAD_ALL_COURSES_ATHLETE_QUERY = SELECT_ALL +
            "FROM mydb.Course join mydb.Subscribe on Subscribe.Course = Course.idCourse " +
            "WHERE Subscribe.Athlete = ?";
    public static ResultSet loadAllCoursesAthlete(String athleteFc, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, athleteFc);
        return preparedStatement.executeQuery();
    }

    public static final String LOAD_ALL_COURSES_TRAINER_QUERY = SELECT_ALL +
            FROM_MYDB_COURSE +
            WHERE_TRAINER;
    public static ResultSet loadAllCoursesTrainer(String trainerFc, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, trainerFc);
        return preparedStatement.executeQuery();
    }

    public static final String INSERT_COURSE_QUERY = "INSERT INTO mydb.Course (Name, Description, FitnessLevel, Equipment, Trainer) " +
            "VALUES (?, ?, ?, ?, ?)";
    public static int insertCourse(PreparedStatement preparedStatement, Course course) throws SQLException {
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
    public static ResultSet searchCourse(PreparedStatement preparedStatement, String name, String fitnessLevel, boolean condition, int index, List<String> dayStringList) throws SQLException {
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
    public static ResultSet loadStartedLessonUrl(PreparedStatement preparedStatement, int idLesson) throws SQLException {
        preparedStatement.setInt(1, idLesson);
        return preparedStatement.executeQuery();
    }

    public static final String INSERT_LESSON_STARTED_LESSON_URL_QUERY = "UPDATE mydb.Lesson " +
            "SET StartedLessonUrl = ? " +
            "WHERE idLesson = ?";
    public static void insertLessonStartedLessonUrl(PreparedStatement preparedStatement, int idLesson, String url) throws SQLException {
        preparedStatement.setString(1, url);
        preparedStatement.setInt(2, idLesson);
        preparedStatement.executeUpdate();
    }

    public static final String REMOVE_LESSON_STARTED_LESSON_URL_QUERY = "UPDATE mydb.Lesson " +
            "SET StartedLessonUrl = NULL " +
            WHERE_COURSE;
    public static void removeLessonStartedLessonUrl(PreparedStatement preparedStatement, int idCourse) throws SQLException {
        preparedStatement.setInt(1, idCourse);
        preparedStatement.executeUpdate();
    }

    public static final String MODIFY_COURSE_QUERY = UPDATE_MYDB_COURSE +
            "SET Name = ?, Description = ?, FitnessLevel = ?, Equipment = ?, Trainer = ? " +
            WHERE_ID_COURSE;
    public static void modifyCourse(PreparedStatement preparedStatement, int idCourse, Course course) throws SQLException {
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
    public static void deleteCourse(int idCourse, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, idCourse);
        preparedStatement.executeUpdate();
    }

    public static final String LOAD_TRAINER_EXERCISES_QUERY = "SELECT Exercise.* " +
            "FROM mydb.Exercise " +
            WHERE_TRAINER;
    public static ResultSet loadTrainerExercises(String trainerFc, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, trainerFc);
        return preparedStatement.executeQuery();
    }

    public static final String INSERT_EXERCISE_QUERY = "INSERT INTO mydb.Exercise (Name, Info, Trainer) " +
            "VALUES (?, ?, ?)";
    public static ResultSet insertExercise(PreparedStatement preparedStatement, Exercise exercise) throws SQLException {
        preparedStatement.setString(1, exercise.getName());
        preparedStatement.setString(2, exercise.getInfo());
        preparedStatement.setString(3, exercise.getTrainer().getFiscalCode());
        preparedStatement.executeUpdate();
        return preparedStatement.getGeneratedKeys();
    }

    public static final String DELETE_EXERCISE_QUERY = "DELETE FROM mydb.Exercise " +
            "WHERE idExercise = ?";
    public static void deleteExercise(PreparedStatement preparedStatement, int idExercise) throws SQLException {
        preparedStatement.setInt(1, idExercise);
        preparedStatement.executeUpdate();
    }

    public static final String LOAD_ALL_LESSONS_QUERY = SELECT_ALL +
            FROM_MYDB_LESSON +
            WHERE_COURSE;
    public static ResultSet loadAllLessons(int idCourse, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, idCourse);
        return preparedStatement.executeQuery();
    }

    public static final String INSERT_LESSON_QUERY = "INSERT INTO mydb.Lesson (LessonDay, LessonStartTime, LessonEndTime, Course) " +
            "VALUES (?, ?, ?, ?)";
    public static void insertLesson(PreparedStatement preparedStatement, Lesson lesson, int idCourse) throws SQLException {
        preparedStatement.setString(1, lesson.getLessonDay());
        preparedStatement.setString(2, lesson.getLessonStartTime().toString());
        preparedStatement.setString(3, lesson.getLessonEndTime().toString());
        preparedStatement.setInt(4, idCourse);
        preparedStatement.executeUpdate();
    }

    public static final String INSERT_REQUEST_QUERY = "INSERT INTO mydb.Request (RequestDate, Info, Athlete, Trainer) " +
            "VALUES (?, ?, ?, ?)";
    public static void insertRequest(PreparedStatement preparedStatement, LocalDateTime requestDate, String info, String athleteFc, String trainerFc) throws SQLException {
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
    public static ResultSet loadTrainerRequests(String trainerFc, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, trainerFc);
        return preparedStatement.executeQuery();
    }

    public static final String DELETE_REQUEST_QUERY = "DELETE FROM mydb.Request " +
            "WHERE idRequest = ?";
    public static void deleteRequest(PreparedStatement preparedStatement, int idRequest) throws SQLException {
        preparedStatement.setInt(1, idRequest);
        preparedStatement.executeUpdate();
    }

    public static final String INSERT_EXERCISE_IN_WORKOUT_DAY_QUERY = "INSERT INTO mydb.Contains (WorkoutDay, Exercise) " +
            "VALUES (?, ?)";
    public static void insertExerciseInWorkoutDay(PreparedStatement preparedStatement, int idExercise, int workoutDayKey) throws SQLException {
        preparedStatement.setInt(1, workoutDayKey);
        preparedStatement.setInt(2, idExercise);
        preparedStatement.executeUpdate();
    }

    public static final String INSERT_WORKOUT_DAY_QUERY = "INSERT INTO mydb.WorkoutDay (WorkoutPlan, Day) VALUES (?, ?)";
    public static ResultSet insertWorkoutDay(PreparedStatement preparedStatement, int workoutPlanKey, String day) throws SQLException {
        preparedStatement.setInt(1, workoutPlanKey);
        preparedStatement.setString(2, day);
        preparedStatement.executeUpdate();
        return preparedStatement.getGeneratedKeys();
    }

    public static final String LOAD_ALL_WORKOUT_DAYS_QUERY = "SELECT WorkoutDay.* " +
            "FROM mydb.WorkoutDay " +
            "WHERE WorkoutDay.WorkoutPlan = ?";
    public static ResultSet loadAllWorkoutDays(PreparedStatement preparedStatement, int idWorkoutPlan) throws SQLException {
        preparedStatement.setInt(1, idWorkoutPlan);
        return preparedStatement.executeQuery();
    }

    public static final String LOAD_ALL_EXERCISE_IN_WORKOUT_DAYS_QUERY = "SELECT Exercise.* " +
            "FROM mydb.Contains join mydb.Exercise on Contains.Exercise = Exercise.idExercise " +
            "WHERE Contains.WorkoutDay = ?";
    public static ResultSet loadAllExerciseInWorkoutDays(PreparedStatement preparedStatement, int idWorkoutDay) throws SQLException {
        preparedStatement.setInt(1, idWorkoutDay);
        return preparedStatement.executeQuery();
    }

    public static final String INSERT_WORKOUT_PLAN_QUERY = "INSERT INTO mydb.WorkoutPlan (Athlete) VALUES (?)";
    public static ResultSet insertWorkoutPlan(PreparedStatement preparedStatement, String athleteFc) throws SQLException {
        preparedStatement.setString(1, athleteFc);
        preparedStatement.executeUpdate();
        return preparedStatement.getGeneratedKeys();
    }

    public static final String INSERT_COURSE_SUBSCRIBER_QUERY = "INSERT INTO mydb.Subscribe (Course, Athlete) " +
            "VALUES (?, ?)";
    public static void insertCourseSubscriber(PreparedStatement preparedStatement, int idCourse, String athleteFc) throws SQLException {
        preparedStatement.setInt(1, idCourse);
        preparedStatement.setString(2, athleteFc);
        preparedStatement.executeUpdate();
    }

    public static final String DELETE_COURSE_SUBSCRIBER_QUERY = "DELETE FROM mydb.Subscribe " +
            "WHERE Course = ? and Athlete = ?";
    public static void deleteCourseSubscriber(PreparedStatement preparedStatement, int idCourse, String athleteFc) throws SQLException {
        preparedStatement.setInt(1, idCourse);
        preparedStatement.setString(2, athleteFc);
        preparedStatement.executeUpdate();
    }

    public static final String LOAD_SUBSCRIBED_QUERY = SELECT_ALL +
            "FROM mydb.Subscribe " +
            WHERE_COURSE;
    public static ResultSet loadSubscribed(PreparedStatement preparedStatement, int idCourse) throws SQLException {
        preparedStatement.setInt(1, idCourse);
        return preparedStatement.executeQuery();
    }

    public static final String GET_SUBSCRIBERS_QUERY = "SELECT Count(*)" +
            "FROM mydb.Subscribe " +
            WHERE_COURSE;
    public static ResultSet getSubscribers(PreparedStatement preparedStatement, int idCourse) throws SQLException {
        preparedStatement.setInt(1, idCourse);
        return preparedStatement.executeQuery();
    }
}
