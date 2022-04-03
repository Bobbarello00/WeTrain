package database.dao_classes;

import database.DatabaseConnectionSingleton;
import database.Query;
import model.Athlete;
import model.Course;
import model.Trainer;
import model.WorkoutDay;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    Connection conn = DatabaseConnectionSingleton.getInstance().getConn();

    public void saveCourse(Course course) throws SQLException {
        try(Statement stmt = conn.createStatement()){
            Query.insertCourse(stmt, course);
        }
    }

    public List<Course> loadAllCoursesAthlete(Athlete athlete) throws SQLException {
        try(Statement stmt = conn.createStatement(); ResultSet rs = Query.loadAllCoursesAthlete(stmt, athlete)){
            List<Course> myList = new ArrayList<>();
            while(rs.next()){
                Trainer trainer = new TrainerDAO().loadTrainer(rs.getString("Trainer"));
                Course course = new Course(rs.getInt("idCourse"), rs.getString("Name"), rs.getString("Description"), rs.getString("FitnessLevel"), trainer, rs.getString("Equipment"));
                course.addAllLessons(new LessonDAO().loadAllLessons(course));
                myList.add(course);
            }
            return myList;
        }
    }
    public List<Course> loadAllCoursesTrainer(Trainer trainer) throws SQLException {
        try(Statement stmt = conn.createStatement(); ResultSet rs = Query.loadAllCoursesTrainer(stmt, trainer)){
            List<Course> myList = new ArrayList<>();
            while(rs.next()){
                Course course = new Course(rs.getInt("idCourse"), rs.getString("Name"), rs.getString("Description"), rs.getString("FitnessLevel"), trainer, rs.getString("Equipment"));
                course.addAllLessons(new LessonDAO().loadAllLessons(course));
                myList.add(course);
            }
            return myList;
        }
    }
}
