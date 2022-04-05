package controller;

import database.dao_classes.CourseDAO;
import model.Athlete;
import model.Course;
import model.Lesson;
import model.LoggedUserSingleton;
import viewone.bean.CourseBean;
import viewone.bean.CourseEssentialBean;
import viewone.bean.LessonBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseManagementAthleteController {

    public static List<CourseEssentialBean> getCourseList() throws SQLException {
        List<Course> courseList = new CourseDAO().loadAllCoursesAthlete((Athlete) LoggedUserSingleton.getInstance());
        List<CourseEssentialBean> beanList = new ArrayList<>();
        for(Course course : courseList) {
            beanList.add(new CourseEssentialBean(course.getId(), course.getName(),  course.getOwner().getName() + " " + course.getOwner().getSurname()));
        }
        return beanList;
    }

    public static CourseBean getCourse(int id) throws SQLException {
        Course course = new CourseDAO().loadCourse(id);
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
}
