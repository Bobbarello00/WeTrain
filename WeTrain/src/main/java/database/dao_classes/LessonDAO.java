package database.dao_classes;

import database.DatabaseConnectionSingleton;
import database.Queries;
import exception.DBConnectionFailedException;
import model.Course;
import model.Lesson;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LessonDAO {
    Connection conn = DatabaseConnectionSingleton.getInstance().getConn();

    public LessonDAO() throws DBConnectionFailedException {
    }

    public void saveLesson(Lesson lesson, Course course) throws SQLException {
        try(Statement stmt = conn.createStatement()){
            Queries.insertLesson(stmt,lesson, course);
        }
    }

    public List<Lesson> loadAllLessons(Course course) throws SQLException {
        try(Statement stmt = conn.createStatement(); ResultSet rs = Queries.loadAllLessons(stmt, course)){
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
