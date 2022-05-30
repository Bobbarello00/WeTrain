package database.dao_classes;

import controller.LoginController;
import database.DatabaseConnectionSingleton;
import database.Queries;
import exception.DBConnectionFailedException;
import exception.DBUnreachableException;
import exception.ElementNotFoundException;
import exception.runtime_exception.ResultSetIsNullException;
import model.*;

import java.sql.*;
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
                Queries.deleteCourseQuery)){
            Queries.deleteCourse(preparedStatement, idCourse);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public void modifyCourse(int idCourse, Course course) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                Queries.modifyCourseQuery)){
            Queries.modifyCourse(preparedStatement, idCourse, course);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public void saveCourse(Course course) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                Queries.insertCourseQuery, Statement.RETURN_GENERATED_KEYS)) {
            int idCourse = Queries.insertCourse(preparedStatement, course);
            course.setId(idCourse);
            for (Lesson lesson : course.getLessonList()) {
                new LessonDAO().saveLesson(lesson, course);
            }
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public void subscribeToCourse(Course course, Athlete athlete) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                Queries.insertCourseSubscriberQuery)){
            Queries.insertCourseSubscriber(preparedStatement, course.getId(), athlete.getFiscalCode());
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public void unsubscribeFromACourse(int idCourse) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                Queries.deleteCourseSubscriberQuery)) {
            Queries.deleteCourseSubscriber(preparedStatement, idCourse, loginController.getLoggedUser().getFiscalCode());
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public Course loadCourse(int idCourse) throws SQLException, DBUnreachableException, ElementNotFoundException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                Queries.loadCourseQuery);ResultSet rs = Queries.loadCourse(preparedStatement, idCourse)) {
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
                Queries.loadAllCoursesAthleteQuery);ResultSet rs = Queries.loadAllCoursesAthlete(preparedStatement, athlete.getFiscalCode())) {
            return loadAllCourses(athlete, rs);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }
    public List<Course> loadPopularCourses() throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                Queries.loadPopularCourseQuery);ResultSet rs = Queries.loadPopularCourse(preparedStatement)) {
            return loadAllCourses(null, rs);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public List<Course> loadAllCoursesTrainer(Trainer trainer) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                Queries.loadAllCoursesTrainerQuery);ResultSet rs = Queries.loadAllCoursesTrainer(preparedStatement, trainer.getFiscalCode())) {
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
        int index = 0;
        List<String> dayStringList = new ArrayList<>(7);
        for (int i = 1; i <= 7; i++) {
            dayStringList.add(DayOfWeek.of(i).name());
        }

        StringBuilder queryString = new StringBuilder();

        for (int i = 0; i < 7; i++) {
            if (Boolean.TRUE.equals(days[i])) {
                condition = false;
                index = i;
                queryString.append("AND Lesson.LessonDay != ? ");
            }
        }

        String nestedQuery = "(SELECT * " +
                FROM_MYDB_LESSON +
                "WHERE Lesson.Course = Course.idCourse " +
                queryString + ")";
        try{
            if (condition) {
                try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(SELECT_ALL +
                        FROM_MYDB_COURSE +
                        "WHERE Name LIKE ? " +
                        "AND FitnessLevel = ?"); ResultSet rs = Queries.searchCourse(preparedStatement, name, fitnessLevel, days, true, index, dayStringList)){
                    return loadAllCourses(loginController.getLoggedUser(), rs);
                }
            } else {
                try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(SELECT_ALL +
                        FROM_MYDB_COURSE +
                        "WHERE Name LIKE ? " +
                        "AND FitnessLevel = ? " +
                        "AND NOT EXISTS " +
                        nestedQuery); ResultSet rs = Queries.searchCourse(preparedStatement, name, fitnessLevel, days, false, index, dayStringList)){
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
                Queries.getSubscribersQuery); ResultSet rs = Queries.getSubscribers(preparedStatement, idCourse)){
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
