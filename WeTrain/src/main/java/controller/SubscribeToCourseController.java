package controller;

import boundary.PaypalSystemBoundary;
import database.dao_classes.CourseDAO;
import exception.DBUnreachableException;
import exception.FatalErrorException;
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
    private List<Course> filteredCourseList;

    public void subscribeToCourse(CourseBean courseBean) throws SQLException, DBUnreachableException, PaymentFailedException {
        LoginController loginController = new LoginController();
        Course selectedCourse;
        selectedCourse = getSelectedCourse(courseBean);
        if(selectedCourse != null) {
            Athlete athlete = (Athlete) loginController.getLoggedUser();
            new CourseDAO().subscribeToCourse(selectedCourse, athlete);
            try {
                PaypalSystemBoundary paypalSystemBoundary = new PaypalSystemBoundary();
                paypalSystemBoundary.pay(
                        new PaymentBean(selectedCourse.getOwner().getIban(), athlete.getCardNumber(), athlete.getCardExpirationDate(), SUBSCRIPTIONTOTRAINERFEE));
            } catch (PaymentFailedException e) {
                new CourseDAO().unsubscribeFromACourse(selectedCourse.getId());
                throw new PaymentFailedException();
            }
            User sender = loginController.getLoggedUser();
            User receiver = selectedCourse.getOwner();
            NotificationsController notificationsController = new NotificationsController();
            notificationsController.sendSubscriptionToACourseNotification(
                    sender,
                    receiver,
                    selectedCourse
            );
        }else{
            throw new FatalErrorException();
        }
    }

    private Course getSelectedCourse(CourseBean courseBean) {
        for(Course course: filteredCourseList){
            if(courseBean.getId()==course.getId()){
                return course;
            }
        }
        return null;
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
        filteredCourseList = new CourseDAO().searchCoursesByFilters(courseSearchBean.getName(), courseSearchBean.getFitnessLevel(), courseSearchBean.getDays());
        return getCourseBeanList(filteredCourseList);
    }
}
