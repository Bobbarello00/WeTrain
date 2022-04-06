package database.dao_classes;

import database.DatabaseConnectionSingleton;
import database.Query;
import model.Course;
import model.Lesson;
import model.Notification;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LessonDAO {
    Connection conn = DatabaseConnectionSingleton.getInstance().getConn();

    public void saveLesson(Lesson lesson) throws SQLException {
        try(Statement stmt = conn.createStatement()){
            Query.insertLesson(stmt,lesson);
        }
    }

    public List<Lesson> loadAllLessons(Course course) throws SQLException {
        try(Statement stmt = conn.createStatement(); ResultSet rs = Query.loadAllLessons(stmt, course)){
            List<Lesson> myList = new ArrayList<>();
            while(rs.next()){
                myList.add(new Lesson(
                        rs.getInt("idLesson"),
                        course,
                        rs.getString("LessonDay"),
                        rs.getTime("LessonStartTime"),
                        rs.getTime("LessonEndTime")));
            }
            return myList;
        }
    }
}
