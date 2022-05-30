package database.Queries;

import models.Lesson;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LessonQueries extends Queries{

    private LessonQueries() {}

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
}