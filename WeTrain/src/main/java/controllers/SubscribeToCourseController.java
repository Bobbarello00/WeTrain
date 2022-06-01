package controllers;

import beans.CourseBean;
import beans.CourseSearchBean;
import beans.PaymentBean;
import boundaries.PaypalSystemBoundary;
import database.dao_classes.CourseDAO;
import exceptions.DBUnreachableException;
import exceptions.PaymentFailedException;
import exceptions.invalid_data_exception.NoCardInsertedException;
import exceptions.invalid_data_exception.NoIbanInsertedException;
import models.Athlete;
import models.Course;
import models.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubscribeToCourseController extends CourseManagementController{

    private static final float SUBSCRIPTIONTOTRAINERFEE = 5;
    private List<Course> filteredCourseList = new ArrayList<>();
    private final Athlete loggedAthlete;

    public SubscribeToCourseController(Athlete loggedAthlete){
        this.loggedAthlete = loggedAthlete;
    }

    public SubscribeToCourseController() throws DBUnreachableException, SQLException {
        loggedAthlete = (Athlete) new LoginController().getLoggedUser();
    }

    public void subscribeToCourse(CourseBean courseBean) throws SQLException, DBUnreachableException, PaymentFailedException, NoCardInsertedException, NoIbanInsertedException {
        Course selectedCourse;
        selectedCourse = getSelectedCourse(courseBean);
        new CourseDAO().subscribeToCourse(selectedCourse, loggedAthlete);
        try {
            PaypalSystemBoundary paypalSystemBoundary = new PaypalSystemBoundary();
            paypalSystemBoundary.pay(
                    new PaymentBean(selectedCourse.getOwner().getIban(), loggedAthlete.getCardNumber(), loggedAthlete.getCardExpirationDate(), SUBSCRIPTIONTOTRAINERFEE));
        } catch (NoCardInsertedException e) {
            new CourseDAO().unsubscribeFromACourse(loggedAthlete, selectedCourse.getId());
            throw new NoCardInsertedException();
        } catch (NoIbanInsertedException e) {
            new CourseDAO().unsubscribeFromACourse(loggedAthlete, selectedCourse.getId());
            throw new NoIbanInsertedException();
        } catch (PaymentFailedException e) {
            new CourseDAO().unsubscribeFromACourse(loggedAthlete, selectedCourse.getId());
            throw new PaymentFailedException();
        }
        User receiver = selectedCourse.getOwner();
        NotificationsController notificationsController = new NotificationsController();
        notificationsController.sendSubscriptionToACourseNotification(
                loggedAthlete,
                receiver,
                selectedCourse
        );
    }

    private Course getSelectedCourse(CourseBean courseBean) throws DBUnreachableException, SQLException {
        for(Course course: filteredCourseList){
            if(courseBean.getId()==course.getId()){
                return course;
            }
        }
        return new CourseDAO().loadCourse(courseBean.getId());
    }

    public void unsubscribeFromCourse(CourseBean courseBean) throws SQLException, DBUnreachableException {
        new CourseDAO().unsubscribeFromACourse(loggedAthlete, courseBean.getId());
    }

    public List<CourseBean> getLoggedAthleteCourseList() throws SQLException, DBUnreachableException {
        List<Course> courseList = new CourseDAO().loadAllCoursesAthlete(loggedAthlete);
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
