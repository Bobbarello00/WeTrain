package database.dao_classes;

import database.DatabaseConnectionSingleton;
import database.Queries.Queries;
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
                Queries.INSERT_LESSON_QUERY)){
            Queries.insertLesson(preparedStatement, lesson, course.getId());
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public List<Lesson> loadAllLessons(int idCourse) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                Queries.LOAD_ALL_LESSONS_QUERY); ResultSet rs = Queries.loadAllLessons(idCourse, preparedStatement)){
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
                Queries.LOAD_STARTED_LESSON_URL_QUERY); ResultSet rs = Queries.loadStartedLessonUrl(preparedStatement, idLesson)){
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
                Queries.INSERT_LESSON_STARTED_LESSON_URL_QUERY)) {
            Queries.insertLessonStartedLessonUrl(preparedStatement, idLesson, url);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public void deleteStartedLessonUrl(int idCourse) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                Queries.REMOVE_LESSON_STARTED_LESSON_URL_QUERY)){
            Queries.removeLessonStartedLessonUrl(preparedStatement, idCourse);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }
}
