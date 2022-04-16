package controller;

import database.dao_classes.CourseDAO;
import model.Athlete;
import model.Course;
import model.Lesson;
import viewone.bean.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseManagementAthleteController {

    private final LoginController loginController = LoginController.getInstance();

    private CourseManagementAthleteController() {}

    private static class SingletonManager {
        private static final CourseManagementAthleteController INSTANCE = new CourseManagementAthleteController();
    }

    public static CourseManagementAthleteController getInstance() {
        return CourseManagementAthleteController.SingletonManager.INSTANCE;
    }

    public void subscribeToACourse(CourseBean courseBean) throws SQLException {
        new CourseDAO().subscribeToACourse(new CourseDAO().loadCourse(courseBean.getId()));
    }

    public List<CourseEssentialBean> getCourseList() throws SQLException {
        List<Course> courseList = new CourseDAO().loadAllCoursesAthlete((Athlete) loginController.getLoggedUser());
        return getBeanList(courseList);
    }

    public List<CourseEssentialBean> getPopularCourseList() throws SQLException {
        List<Course> popularCourses = new CourseDAO().loadPopularCourses();
        return getBeanList(popularCourses);
    }

    public CourseBean getCourse(IdBean bean) throws SQLException {
        Course course = new CourseDAO().loadCourse(bean.getId());
        CourseBean courseBean = new CourseBean(
                course.getId(),
                course.getName(),
                course.getDescription(),
                course.getFitnessLevel(),
                course.getOwner().getName() + " " + course.getOwner().getSurname(),
                course.getEquipment());
        if(course.getLessonList() != null){
            List<LessonBean> lessonBeanList = new ArrayList<>();
            for(Lesson lesson: course.getLessonList()){
                lessonBeanList.add(new LessonBean(
                        lesson.getLessonDay(),
                        lesson.getLessonStartTime(),
                        lesson.getLessonEndTime()));
            }
            courseBean.setLessonBeanList(lessonBeanList);
        } else {
            courseBean.setLessonBeanList(null);
        }

        return courseBean;
    }

    private List<CourseEssentialBean> getBeanList(List<Course> courseList) {
        List<CourseEssentialBean> beanList = new ArrayList<>();
        for(Course course : courseList) {
            beanList.add(new CourseEssentialBean(course.getId(), course.getName(),  course.getOwner().getName() + " " + course.getOwner().getSurname()));
        }
        return beanList;
    }

    public List<CourseEssentialBean> searchCourse(CourseSearchBean courseSearchBean) throws SQLException {
        List<Course> courseList = new CourseDAO().searchCourses(courseSearchBean.getName(), courseSearchBean.getFitnessLevel(), courseSearchBean.getDays());
        return getBeanList(courseList);
    }
}
