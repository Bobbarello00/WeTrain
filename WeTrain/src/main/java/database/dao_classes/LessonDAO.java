package database.dao_classes;

import database.DatabaseConnectionSingleton;
import database.Queries.LessonQueries;
import exceptions.DBConnectionFailedException;
import exceptions.DBUnreachableException;
import exceptions.runtime_exception.ResultSetIsNullException;
import models.Course;
import models.Lesson;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LessonDAO {

    public void saveLesson(Lesson lesson, Course course) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                LessonQueries.INSERT_LESSON_QUERY)){
            LessonQueries.insertLesson(preparedStatement, lesson, course.getId());
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public List<Lesson> loadAllLessons(int idCourse) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                LessonQueries.LOAD_ALL_LESSONS_QUERY); ResultSet rs = LessonQueries.loadAllLessons(idCourse, preparedStatement)){
            List<Lesson> myList = new ArrayList<>();
            while(rs.next()){
                myList.add(new Lesson(
                        rs.getInt("idLesson"),
                        rs.getString("LessonDay"),
                        rs.getTime("LessonStartTime").toLocalTime(),
                        rs.getTime("LessonEndTime").toLocalTime(),
                        rs.getString("StartedLessonUrl"))
                );
            }
            return myList;
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public String loadStartedLessonUrl(int idLesson) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                LessonQueries.LOAD_STARTED_LESSON_URL_QUERY); ResultSet rs = LessonQueries.loadStartedLessonUrl(preparedStatement, idLesson)){
            if(rs.next()){
                return rs.getString("StartedLessonUrl");
            }else{
                throw new ResultSetIsNullException();
            }
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public void setStartedLessonUrl(String url, int idLesson) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                LessonQueries.INSERT_LESSON_STARTED_LESSON_URL_QUERY)) {
            LessonQueries.insertLessonStartedLessonUrl(preparedStatement, idLesson, url);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public void deleteStartedLessonUrl(int idCourse) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                LessonQueries.REMOVE_LESSON_STARTED_LESSON_URL_QUERY)){
            LessonQueries.removeLessonStartedLessonUrl(preparedStatement, idCourse);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }
}
