package database.dao_classes;

import database.Queries;
import exception.DBConnectionFailedException;
import exception.DBUnreachableException;
import model.Course;
import model.Lesson;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LessonDAO {

    public void saveLesson(Lesson lesson, Course course) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = Queries.insertLesson(lesson, course.getId())){
            preparedStatement.executeUpdate();
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public List<Lesson> loadAllLessons(Course course) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = Queries.loadAllLessons(course.getId());
            ResultSet rs = preparedStatement.executeQuery()){
            List<Lesson> myList = new ArrayList<>();
            while(rs.next()){
                myList.add(new Lesson(
                        rs.getInt("idLesson"),
                        rs.getString("LessonDay"),
                        rs.getTime("LessonStartTime").toLocalTime(),
                        rs.getTime("LessonEndTime").toLocalTime()));
            }
            return myList;
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }
}
