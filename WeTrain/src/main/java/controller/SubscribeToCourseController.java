package controller;

import boundary.PaypalSystemBoundary;
import database.dao_classes.CourseDAO;
import exception.DBUnreachableException;
import exception.PaymentFailedException;
import model.Athlete;
import model.Course;
import model.User;
import viewone.bean.CourseBean;
import viewone.bean.CourseSearchBean;
import viewone.bean.PaymentBean;

import java.sql.SQLException;
import java.util.List;

public class SubscribeToCourseController extends CourseManagementController{

    private static final float SUBSCRIPTIONTOTRAINERFEE = 5;

    public void subscribeToCourse(CourseBean courseBean) throws SQLException, DBUnreachableException, PaymentFailedException {
        LoginController loginController = new LoginController();
        Course course = new CourseDAO().loadCourse(courseBean.getId());
        Athlete athlete = (Athlete) loginController.getLoggedUser();
        new CourseDAO().subscribeToCourse(course.getId(), athlete.getFiscalCode());
        try {
            PaypalSystemBoundary paypalSystemBoundary = new PaypalSystemBoundary();
            paypalSystemBoundary.pay(
                    new PaymentBean(course.getOwner().getIban(), athlete.getCardNumber(), athlete.getCardExpirationDate(), SUBSCRIPTIONTOTRAINERFEE));
        }catch(PaymentFailedException e){
            new CourseDAO().unsubscribeFromACourse(course.getId());
            throw new PaymentFailedException();
        }
        User sender = loginController.getLoggedUser();
        User receiver = course.getOwner();
        NotificationsController notificationsController = new NotificationsController();
        notificationsController.sendSubscriptionToACourseNotification(
                sender,
                receiver,
                course
        );
    }

    public void unsubscribeFromCourse(CourseBean courseBean) throws SQLException, DBUnreachableException {
        new CourseDAO().unsubscribeFromACourse(courseBean.getId());
    }

    public List<CourseBean> getLoggedAthleteCourseList() throws SQLException, DBUnreachableException {
        LoginController loginController = new LoginController();
        List<Course> courseList = new CourseDAO().loadAllCoursesAthlete((Athlete) loginController.getLoggedUser());
        return getCourseBeanList(courseList);
    }

    public List<CourseBean> getPopularCourseList() throws SQLException, DBUnreachableException {
        List<Course> popularCourses = new CourseDAO().loadPopularCourses();
        return getCourseBeanList(popularCourses);
    }

    public List<CourseBean> searchCourse(CourseSearchBean courseSearchBean) throws SQLException, DBUnreachableException {
        List<Course> courseList = new CourseDAO().searchCoursesByFilters(courseSearchBean.getName(), courseSearchBean.getFitnessLevel(), courseSearchBean.getDays());
        return getCourseBeanList(courseList);
    }
}
