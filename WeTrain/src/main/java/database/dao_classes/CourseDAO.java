package database.dao_classes;

import controller.LoginController;
import database.DatabaseConnectionSingleton;
import database.Query;
import exception.DBConnectionFailedException;
import exception.ElementNotFoundException;
import model.*;

import java.net.SocketException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    Connection conn = DatabaseConnectionSingleton.getInstance().getConn();
    private static final String IDCOURSE = "idCourse";
    private static final String COURSENAME = "Name";
    private static final String DESCRIPTION = "Description";
    private static final String FITNESSLEVEL = "FitnessLevel";
    private static final String EQUIPMENT = "Equipment";
    private static final String TRAINER = "Trainer";

    private final LoginController loginController = new LoginController();

    public CourseDAO() throws DBConnectionFailedException {}

    public void deleteCourse(int idCourse) throws SQLException {
        try(Statement stmt = conn.createStatement()) {
            Query.deleteCourse(stmt, idCourse);
        }
    }

    public void saveCourse(Course course) throws SQLException, DBConnectionFailedException {
        int idCourse = Query.insertCourse(course);
        course.setId(idCourse);

        for(Lesson lesson: course.getLessonList()){
            new LessonDAO().saveLesson(lesson, course);
        }
    }

    //TODO inserimenti in Subscribe vanno fatti in CourseDAO?
    public void subscribeToACourse(Course course) throws SQLException, DBConnectionFailedException {
        try(Statement stmt = conn.createStatement()){
            Query.insertSubscribe(stmt, course, (Athlete) loginController.getLoggedUser());
        }
    }

    public void unsubscribeFromACourse(int idCourse) throws SQLException, DBConnectionFailedException {
        try(Statement stmt = conn.createStatement()){
            Query.deleteSubscriber(stmt, idCourse, loginController.getLoggedUser().getFiscalCode());
        }
    }

    public Course loadCourse(int id) throws SQLException, DBConnectionFailedException {
        try(Statement stmt = conn.createStatement(); ResultSet rs = Query.loadCourse(stmt, id)) {
            if(rs.next()){
                Course course = new Course(
                        rs.getInt(IDCOURSE),
                        rs.getString(COURSENAME),
                        rs.getString(DESCRIPTION),
                        rs.getString(FITNESSLEVEL),
                        new TrainerDAO().loadTrainer(rs.getString(TRAINER)),
                        rs.getString(EQUIPMENT)
                );
                course.addAllLessons(new LessonDAO().loadAllLessons(course));
                return course;
            } else {
                throw new ElementNotFoundException();
            }
        }
    }

    public List<Course> loadAllCoursesAthlete(Athlete athlete) throws SQLException, DBConnectionFailedException {
        try(Statement stmt = conn.createStatement(); ResultSet rs = Query.loadAllCoursesAthlete(stmt, athlete)){
            return loadAllCourses(athlete, rs);
        }
    }
    public List<Course> loadPopularCourses() throws SQLException, DBConnectionFailedException {
        try(Statement stmt = conn.createStatement(); ResultSet rs = Query.loadPopularCourse(stmt)){
            return loadAllCourses(null, rs);
        }
    }

    public List<Course> loadAllCoursesTrainer(Trainer trainer) throws SQLException, DBConnectionFailedException {
        try(Statement stmt = conn.createStatement(); ResultSet rs = Query.loadAllCoursesTrainer(stmt, trainer)){
            return loadAllCourses(trainer, rs);
        }
    }

    private List<Course> loadAllCourses(User user, ResultSet rs) throws SQLException, DBConnectionFailedException {
        List<Course> myList = new ArrayList<>();
        if(!rs.next()){
            return myList;
        }
        do {
            Course course;
            if(user instanceof Trainer) {
                course = new Course(
                        rs.getInt(IDCOURSE),
                        rs.getString(COURSENAME),
                        rs.getString(DESCRIPTION),
                        rs.getString(FITNESSLEVEL),
                        (Trainer) user,
                        rs.getString(EQUIPMENT)
                );
            } else {
                course = new Course(
                        rs.getInt(IDCOURSE),
                        rs.getString(COURSENAME),
                        rs.getString(DESCRIPTION),
                        rs.getString(FITNESSLEVEL),
                        new TrainerDAO().loadTrainer(rs.getString(TRAINER)),
                        rs.getString(EQUIPMENT)
                );
            }

            course.addAllLessons(new LessonDAO().loadAllLessons(course));
            myList.add(course);
        }while(rs.next());
        return myList;
    }

    public List<Course> searchCourses(String name, String fitnessLevel, Boolean[] days) throws SQLException, DBConnectionFailedException {
        try(Statement stmt = conn.createStatement(); ResultSet rs = Query.searchCourse(stmt, name, fitnessLevel, days)){
            return loadAllCourses(loginController.getLoggedUser(), rs);
        }
    }
}
