package database.Queries;

import exceptions.runtime_exception.ResultSetIsNullException;
import models.Course;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CourseQueries extends Queries{

    public static final String LOAD_COURSE_QUERY = SELECT_ALL +
            FROM_MYDB_COURSE +
            WHERE_ID_COURSE;

    public static final String LOAD_POPULAR_COURSE_QUERY = "SELECT Course.* " +
            "FROM mydb.Course join mydb.Subscribe on Course.idCourse = Subscribe.Course " +
            "GROUP BY idCourse " +
            "ORDER BY COUNT(idCourse) DESC " +
            LIMIT_10;

    public static final String LOAD_ALL_COURSES_ATHLETE_QUERY = SELECT_ALL +
            "FROM mydb.Course join mydb.Subscribe on Subscribe.Course = Course.idCourse " +
            "WHERE Subscribe.Athlete = ?";

    public static final String LOAD_ALL_COURSES_TRAINER_QUERY = SELECT_ALL +
            FROM_MYDB_COURSE +
            WHERE_TRAINER;

    public static final String INSERT_COURSE_QUERY = "INSERT INTO mydb.Course (Name, Description, FitnessLevel, Equipment, Trainer) " +
            "VALUES (?, ?, ?, ?, ?)";

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

    public static ResultSet loadCourse(PreparedStatement preparedStatement, int idCourse) throws SQLException {
        preparedStatement.setInt(1, idCourse);
        return preparedStatement.executeQuery();
    }

    public static ResultSet loadPopularCourse(PreparedStatement preparedStatement) throws SQLException {
        return preparedStatement.executeQuery();
    }

    public static ResultSet loadAllCoursesAthlete(String athleteFc, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, athleteFc);
        return preparedStatement.executeQuery();
    }

    public static ResultSet loadAllCoursesTrainer(String trainerFc, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, trainerFc);
        return preparedStatement.executeQuery();
    }

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

    public static final String DELETE_COURSE_QUERY = "DELETE FROM mydb.Course " +
            WHERE_ID_COURSE;
    public static void deleteCourse(int idCourse, PreparedStatement preparedStatement) throws SQLException {
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