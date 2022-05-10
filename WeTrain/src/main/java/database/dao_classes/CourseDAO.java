package database.dao_classes;

import controller.LoginController;
import database.Queries;
import exception.DBConnectionFailedException;
import exception.ElementNotFoundException;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    private static final String IDCOURSE = "idCourse";
    private static final String COURSENAME = "Name";
    private static final String DESCRIPTION = "Description";
    private static final String FITNESSLEVEL = "FitnessLevel";
    private static final String EQUIPMENT = "Equipment";
    private static final String TRAINER = "Trainer";
    private static final String STARTEDLESSONURL = "startedLessonUrl";

    private final LoginController loginController = new LoginController();

    public CourseDAO() {}

    public void deleteCourse(int idCourse) throws SQLException, DBConnectionFailedException {
        Queries.deleteCourse(idCourse);
    }

    public void modifyCourse(int idCourse, Course course) throws SQLException, DBConnectionFailedException {
        Queries.modifyCourse(idCourse, course);
    }

    public void saveCourse(Course course) throws SQLException, DBConnectionFailedException {
        int idCourse = Queries.insertCourse(course);
        course.setId(idCourse);

        for(Lesson lesson: course.getLessonList()){
            new LessonDAO().saveLesson(lesson, course);
        }
    }

    //TODO inserimenti in Subscribe vanno fatti in CourseDAO?
    public void subscribeToACourse(int idCourse) throws SQLException, DBConnectionFailedException {
        Queries.insertCourseSubscriber(idCourse, (loginController.getLoggedUser()).getFiscalCode());

    }

    public void unsubscribeFromACourse(int idCourse) throws SQLException, DBConnectionFailedException {
        Queries.deleteCourseSubscriber(idCourse, loginController.getLoggedUser().getFiscalCode());
    }

    public Course loadCourse(int idCourse) throws SQLException, DBConnectionFailedException {
        try(ResultSet rs = Queries.loadCourse(idCourse)) {
            if(rs.next()){
                Course course = new Course(
                        rs.getInt(IDCOURSE),
                        rs.getString(COURSENAME),
                        rs.getString(DESCRIPTION),
                        rs.getString(FITNESSLEVEL),
                        new TrainerDAO().loadTrainer(rs.getString(TRAINER)),
                        rs.getString(EQUIPMENT),
                        rs.getString(STARTEDLESSONURL)
                );
                course.addAllLessons(new LessonDAO().loadAllLessons(course));
                return course;
            } else {
                throw new ElementNotFoundException();
            }
        }
    }

    public List<Course> loadAllCoursesAthlete(Athlete athlete) throws SQLException, DBConnectionFailedException {
        return loadAllCourses(athlete, Queries.loadAllCoursesAthlete(athlete.getFiscalCode()));
    }
    public List<Course> loadPopularCourses() throws SQLException, DBConnectionFailedException {
        return loadAllCourses(null, Queries.loadPopularCourse());
    }

    public List<Course> loadAllCoursesTrainer(Trainer trainer) throws SQLException, DBConnectionFailedException {
        return loadAllCourses(trainer, Queries.loadAllCoursesTrainer(trainer.getFiscalCode()));
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
                        rs.getString(EQUIPMENT),
                        rs.getString(STARTEDLESSONURL)
                );
            } else {
                course = new Course(
                        rs.getInt(IDCOURSE),
                        rs.getString(COURSENAME),
                        rs.getString(DESCRIPTION),
                        rs.getString(FITNESSLEVEL),
                        new TrainerDAO().loadTrainer(rs.getString(TRAINER)),
                        rs.getString(EQUIPMENT),
                        rs.getString(STARTEDLESSONURL)
                );
            }

            course.addAllLessons(new LessonDAO().loadAllLessons(course));
            myList.add(course);
        }while(rs.next());
        return myList;
    }

    public List<Course> searchCourses(String name, String fitnessLevel, Boolean[] days) throws SQLException, DBConnectionFailedException {
        return loadAllCourses(loginController.getLoggedUser(), Queries.searchCourse(name, fitnessLevel, days));
    }

    public void setStartedLessonUrl(String url, int idCourse) throws SQLException, DBConnectionFailedException {
        Queries.insertCourseStartedLessonUrl(idCourse, url);
    }

    public String loadStartedLessonUrl(int idCourse) throws SQLException, DBConnectionFailedException {
        try(ResultSet rs = Queries.loadCourseStartedLessonUrl(idCourse)){
            if(rs.next()){
                return rs.getString("StartedLessonUrl");
            }else{
                return null;
            }
        }
    }

    public int getSubscribersNumber(int idCourse) throws SQLException, DBConnectionFailedException {
        try(ResultSet rs = Queries.getSubscribers(idCourse)){
            if(rs.next()) {
                return rs.getInt(1);
            } else {
                throw new RuntimeException();
            }
        }
    }
}
