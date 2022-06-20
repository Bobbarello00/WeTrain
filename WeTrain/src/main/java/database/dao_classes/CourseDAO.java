package database.dao_classes;

import controllers.LoginController;
import database.DatabaseConnectionSingleton;
import database.queries.CourseQueries;
import exceptions.DBConnectionFailedException;
import exceptions.DBUnreachableException;
import exceptions.ElementNotFoundException;
import exceptions.runtime_exception.ResultSetIsNullException;
import models.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    private static final String IDCOURSE = "idCourse";
    private static final String COURSENAME = "Name";
    private static final String DESCRIPTION = "Description";
    private static final String FITNESSLEVEL = "FitnessLevel";
    private static final String EQUIPMENT = "Equipment";
    private static final String TRAINER = "Trainer";

    private final LoginController loginController = new LoginController();

    public void deleteCourse(int idCourse) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                CourseQueries.DELETE_COURSE_QUERY)){
            CourseQueries.deleteCourse(idCourse, preparedStatement);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public void modifyCourse(Course courseToModify, Course course) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                CourseQueries.MODIFY_COURSE_QUERY)){
            CourseQueries.modifyCourse(preparedStatement, courseToModify.getId(), course);
            new LessonDAO().modifyAllLessons(courseToModify, course);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public void saveCourse(Course course) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                CourseQueries.INSERT_COURSE_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            int idCourse = CourseQueries.insertCourse(preparedStatement, course);
            course.setId(idCourse);
            for (Lesson lesson : course.getLessonList()) {
                new LessonDAO().saveLesson(lesson, course.getId());
            }
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public void subscribeToCourse(Course course, Athlete athlete) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                CourseQueries.INSERT_COURSE_SUBSCRIBER_QUERY)){
            CourseQueries.insertOrDeleteCourseSubscriber(preparedStatement, course.getId(), athlete.getFiscalCode());
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public void unsubscribeFromACourse(Athlete athlete, int idCourse) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                CourseQueries.DELETE_COURSE_SUBSCRIBER_QUERY)) {
            CourseQueries.insertOrDeleteCourseSubscriber(preparedStatement, idCourse, athlete.getFiscalCode());
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public Course loadCourse(int idCourse) throws SQLException, DBUnreachableException, ElementNotFoundException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                CourseQueries.LOAD_COURSE_QUERY); ResultSet rs = CourseQueries.loadCourseOrSubscribers(preparedStatement, idCourse)) {
            if(rs.next()){
                return new Course(
                        rs.getInt(IDCOURSE),
                        rs.getString(COURSENAME),
                        rs.getString(DESCRIPTION),
                        rs.getString(FITNESSLEVEL),
                        new TrainerDAO().loadTrainer(rs.getString(TRAINER)),
                        rs.getString(EQUIPMENT),
                        new LessonDAO().loadAllLessons(rs.getInt(IDCOURSE))
                );
            } else {
                throw new ElementNotFoundException();
            }
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public List<Course> loadAllCoursesAthlete(Athlete athlete) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                CourseQueries.LOAD_ALL_COURSES_ATHLETE_QUERY); ResultSet rs = CourseQueries.loadAllCoursesAthlete(athlete.getFiscalCode(), preparedStatement)) {
            return loadAllCourses(athlete, rs);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }
    public List<Course> loadPopularCourses() throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                CourseQueries.LOAD_POPULAR_COURSE_QUERY); ResultSet rs = CourseQueries.loadPopularCourse(preparedStatement)) {
            return loadAllCourses(null, rs);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public List<Course> loadAllCoursesTrainer(Trainer trainer) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                CourseQueries.LOAD_ALL_COURSES_TRAINER_QUERY); ResultSet rs = CourseQueries.loadAllCoursesTrainer(trainer.getFiscalCode(), preparedStatement)) {
            return loadAllCourses(trainer, rs);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    private List<Course> loadAllCourses(User user, ResultSet rs) throws SQLException, DBUnreachableException {
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
                        new LessonDAO().loadAllLessons(rs.getInt(IDCOURSE))
                );
            } else {
                course = new Course(
                        rs.getInt(IDCOURSE),
                        rs.getString(COURSENAME),
                        rs.getString(DESCRIPTION),
                        rs.getString(FITNESSLEVEL),
                        new TrainerDAO().loadTrainer(rs.getString(TRAINER)),
                        rs.getString(EQUIPMENT),
                        new LessonDAO().loadAllLessons(rs.getInt(IDCOURSE))
                );
            }

            myList.add(course);
        }while(rs.next());
        return myList;
    }

    public List<Course> searchCoursesByFilters(String name, String fitnessLevel, Boolean[] days) throws SQLException, DBUnreachableException {
        boolean condition = true;
        List<String> dayStringList = new ArrayList<>(7);
        for (int i = 1; i <= 7; i++) {
            dayStringList.add(DayOfWeek.of(i).name());
        }

        StringBuilder queryString = new StringBuilder();

        for (int i = 0; i < 7; i++) {
            if (Boolean.TRUE.equals(days[i])) {
                condition = false;
                queryString.append(CourseQueries.SEARCH_COURSE_QUERY_QUERY_STRING);
            }
        }

        String nestedQuery = CourseQueries.SEARCH_COURSE_QUERY_NESTED_QUERY +
                queryString + ")";
        try{
            if (condition) {
                try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                        CourseQueries.SEARCH_COURSE_QUERY_TRUE); ResultSet rs = CourseQueries.searchCourse(preparedStatement, name, fitnessLevel, true, days, dayStringList)){
                    return loadAllCourses(loginController.getLoggedUser(), rs);
                }
            } else {
                try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                        CourseQueries.SEARCH_COURSE_QUERY_FALSE +
                        nestedQuery); ResultSet rs = CourseQueries.searchCourse(preparedStatement, name, fitnessLevel, false, days, dayStringList)){
                    return loadAllCourses(loginController.getLoggedUser(), rs);
                }
            }
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public int getSubscribersNumber(int idCourse) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                CourseQueries.GET_SUBSCRIBERS_QUERY); ResultSet rs = CourseQueries.loadCourseOrSubscribers(preparedStatement, idCourse)){
            if(rs.next()) {
                return rs.getInt(1);
            } else {
                throw new ResultSetIsNullException();
            }
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }
}
