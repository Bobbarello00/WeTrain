package controller;

import database.dao_classes.CourseDAO;
import model.Athlete;
import model.Course;
import model.LoggedUserSingleton;
import viewone.bean.CourseEssentialBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseManagementAthleteController {

    public static List<CourseEssentialBean> getCourseList() throws SQLException {
        List<Course> courseList = new CourseDAO().loadAllCoursesAthlete((Athlete) LoggedUserSingleton.getInstance());
        List<CourseEssentialBean> beanList = new ArrayList<>();
        for(Course course : courseList) {
            beanList.add(new CourseEssentialBean(course.getId(), course.getName(),  course.getOwner().getName() + course.getOwner().getSurname()));
        }
        return beanList;
    }
}
