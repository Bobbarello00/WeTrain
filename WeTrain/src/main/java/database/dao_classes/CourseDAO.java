package database.dao_classes;

import controller.LoginController;
import database.DatabaseConnectionSingleton;
import database.Query;
import exception.ElementNotFoundException;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    Connection conn = DatabaseConnectionSingleton.getInstance().getConn();

    public void saveCourse(Course course) throws SQLException {
        try(Statement stmt = conn.createStatement()){
            int idCourse = Query.insertCourse(stmt, course);
            course.setId(idCourse);

            for(Lesson lesson: course.getLessonList()){
                lesson.setCourse(course);
                new LessonDAO().saveLesson(lesson);
            }
        }
    }

    //TODO inserimenti in Subscribe vanno fatti in CourseDAO?
    public void subscribeToACourse(Course course) throws SQLException {
        try(Statement stmt = conn.createStatement()){
            Query.insertSubscribe(stmt, course, (Athlete) LoginController.getLoggedUser());
        }
    }

    public Course loadCourse(int id) throws SQLException {
        try(Statement stmt = conn.createStatement(); ResultSet rs = Query.loadCourse(stmt, id)) {
            if(rs.next()){
                Course course = new Course(
                        rs.getInt("idCourse"),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getString("FitnessLevel"),
                        new TrainerDAO().loadTrainer(rs.getString("Trainer")),
                        rs.getString("Equipment")
                );
                course.addAllLessons(new LessonDAO().loadAllLessons(course));
                return course;
            } else {
                throw new ElementNotFoundException();
            }
        }
    }

    public List<Course> loadAllCoursesAthlete(Athlete athlete) throws SQLException {
        try(Statement stmt = conn.createStatement(); ResultSet rs = Query.loadAllCoursesAthlete(stmt, athlete)){
            return loadAllCourses(athlete, rs);
        }
    }

    public List<Course> loadAllCoursesTrainer(Trainer trainer) throws SQLException {
        try(Statement stmt = conn.createStatement(); ResultSet rs = Query.loadAllCoursesTrainer(stmt, trainer)){
            return loadAllCourses(trainer, rs);
        }
    }

    private List<Course> loadAllCourses(User user, ResultSet rs) throws SQLException {
        if(!rs.next()){
            throw new ElementNotFoundException();
        }
        List<Course> myList = new ArrayList<>();
        do {
            Course course;
            if(user instanceof Trainer) {
                course = new Course(
                        rs.getInt("idCourse"),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getString("FitnessLevel"),
                        (Trainer) user,
                        rs.getString("Equipment")
                );
            } else {
                course = new Course(
                        rs.getInt("idCourse"),
                        rs.getString("Name"),
                        rs.getString("Description"),
                        rs.getString("FitnessLevel"),
                        new TrainerDAO().loadTrainer(rs.getString("Trainer")),
                        rs.getString("Equipment")
                );
            }
            course.addAllLessons(new LessonDAO().loadAllLessons(course));
            myList.add(course);
        }while(rs.next());
        return myList;
    }
}
