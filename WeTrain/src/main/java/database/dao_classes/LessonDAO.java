package database.dao_classes;

import database.Queries;
import exception.DBConnectionFailedException;
import model.Course;
import model.Lesson;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LessonDAO {

    public LessonDAO() {}

    public void saveLesson(Lesson lesson, Course course) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = Queries.insertLesson(lesson, course.getId())){
            preparedStatement.executeUpdate();
        }
    }

    public List<Lesson> loadAllLessons(Course course) throws SQLException, DBConnectionFailedException {
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
        }
    }
}
