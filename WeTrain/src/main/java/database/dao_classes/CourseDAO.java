package database.dao_classes;

import controller.LoginController;
import database.Queries;
import exception.DBConnectionFailedException;
import exception.DBUnreachableException;
import exception.ElementNotFoundException;
import exception.runtime_exception.ResultSetIsNullException;
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

    public void deleteCourse(int idCourse) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = Queries.deleteCourse(idCourse)){
            preparedStatement.executeUpdate();
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public void modifyCourse(int idCourse, Course course) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = Queries.modifyCourse(idCourse, course)) {
            preparedStatement.executeUpdate();
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public void saveCourse(Course course) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = Queries.insertCourse(course)) {
            preparedStatement.executeUpdate();
            int idCourse;
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    idCourse = generatedKeys.getInt(1);
                } else {
                    throw new ResultSetIsNullException();
                }
            }
            course.setId(idCourse);

            for (Lesson lesson : course.getLessonList()) {
                new LessonDAO().saveLesson(lesson, course);
            }
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    //TODO inserimenti in Subscribe vanno fatti in CourseDAO?
    public void subscribeToACourse(int idCourse) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = Queries.insertCourseSubscriber(idCourse, (loginController.getLoggedUser()).getFiscalCode())){
            preparedStatement.executeUpdate();
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public void unsubscribeFromACourse(int idCourse) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = Queries.deleteCourseSubscriber(idCourse, loginController.getLoggedUser().getFiscalCode())){
            preparedStatement.executeUpdate();
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public Course loadCourse(int idCourse) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = Queries.loadCourse(idCourse)) {
            ResultSet rs = preparedStatement.executeQuery();
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
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public List<Course> loadAllCoursesAthlete(Athlete athlete) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = Queries.loadAllCoursesAthlete(athlete.getFiscalCode());
            ResultSet rs = preparedStatement.executeQuery()) {
            return loadAllCourses(athlete, rs);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }
    public List<Course> loadPopularCourses() throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = Queries.loadPopularCourse();
            ResultSet rs = preparedStatement.executeQuery()) {
            return loadAllCourses(null, rs);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public List<Course> loadAllCoursesTrainer(Trainer trainer) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = Queries.loadAllCoursesTrainer(trainer.getFiscalCode());
            ResultSet rs = preparedStatement.executeQuery()) {
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

    public List<Course> searchCourses(String name, String fitnessLevel, Boolean[] days) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = Queries.searchCourse(name, fitnessLevel, days)){
            return loadAllCourses(loginController.getLoggedUser(), preparedStatement.executeQuery());
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public void setStartedLessonUrl(String url, int idCourse) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = Queries.insertCourseStartedLessonUrl(idCourse, url)){
            preparedStatement.executeUpdate();
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public String loadStartedLessonUrl(int idCourse) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = Queries.loadCourseStartedLessonUrl(idCourse); ResultSet rs = preparedStatement.executeQuery()){
            if(rs.next()){
                return rs.getString("StartedLessonUrl");
            }else{
                throw new ResultSetIsNullException();
            }
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public int getSubscribersNumber(int idCourse) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = Queries.getSubscribers(idCourse); ResultSet rs = preparedStatement.executeQuery()){
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
