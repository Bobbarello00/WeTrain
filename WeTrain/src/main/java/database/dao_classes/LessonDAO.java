package database.dao_classes;

import database.Queries;
import exception.DBConnectionFailedException;
import exception.DBUnreachableException;
import exception.runtime_exception.ResultSetIsNullException;
import model.Course;
import model.Lesson;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LessonDAO {

    public void saveLesson(Lesson lesson, Course course) throws SQLException, DBUnreachableException {
        try{
            Queries.insertLesson(lesson, course.getId());
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public List<Lesson> loadAllLessons(int idCourse) throws SQLException, DBUnreachableException {
        try(ResultSet rs = Queries.loadAllLessons(idCourse)){
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
        try(ResultSet rs = Queries.loadStartedLessonUrl(idLesson)){
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
        try{
            Queries.insertLessonStartedLessonUrl(idLesson, url);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public void deleteStartedLessonUrl(int idCourse) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = Queries.removeLessonStartedLessonUrl(idCourse)){
            preparedStatement.executeUpdate();
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }
}
