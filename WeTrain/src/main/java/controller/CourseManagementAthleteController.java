package controller;

import database.dao_classes.CourseDAO;
import exception.DBConnectionFailedException;
import exception.ImATrainerException;
import model.*;
import viewone.bean.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseManagementAthleteController extends CourseManagementController{

    private final LoginController loginController = new LoginController();

    public boolean checkSubscription(CourseBean courseBean) throws SQLException, DBConnectionFailedException, ImATrainerException {
        User user = loginController.getLoggedUser();
        if(user instanceof Trainer) {
            throw new ImATrainerException();
        }
        Athlete athlete = (Athlete) user;
        List<Course> courseList = athlete.getCourseList();
        for(Course course: courseList){
            if(course.getId() == courseBean.getId()){
                return true;
            }
        }
        return false;
    }

    public void subscribeToACourse(CourseBean courseBean) throws SQLException, DBConnectionFailedException {
        new CourseDAO().subscribeToACourse(new CourseDAO().loadCourse(courseBean.getId()));
    }

    public void unsubscribeFromACourse(CourseBean courseBean) throws SQLException, DBConnectionFailedException {
        new CourseDAO().unsubscribeFromACourse(courseBean.getId());
    }

    public List<CourseBean> getCourseList() throws SQLException, DBConnectionFailedException {
        List<Course> courseList = new CourseDAO().loadAllCoursesAthlete((Athlete) loginController.getLoggedUser());
        return getCourseBeanList(courseList);
    }

    public List<CourseBean> getPopularCourseList() throws SQLException, DBConnectionFailedException {
        List<Course> popularCourses = new CourseDAO().loadPopularCourses();
        return getCourseBeanList(popularCourses);
    }

    public List<CourseBean> searchCourse(CourseSearchBean courseSearchBean) throws SQLException, DBConnectionFailedException {
        List<Course> courseList = new CourseDAO().searchCourses(courseSearchBean.getName(), courseSearchBean.getFitnessLevel(), courseSearchBean.getDays());
        return getCourseBeanList(courseList);
    }
}
