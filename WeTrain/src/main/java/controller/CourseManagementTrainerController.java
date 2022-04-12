package controller;

import database.dao_classes.CourseDAO;
import database.dao_classes.TrainerDAO;
import model.Course;
import model.Lesson;
import model.Trainer;
import viewone.bean.CourseBean;
import viewone.bean.LessonBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseManagementTrainerController {

    private CourseManagementTrainerController() {}

    private static class SingletonManager {
        private static final CourseManagementTrainerController INSTANCE = new CourseManagementTrainerController();
    }

    public static CourseManagementTrainerController getInstance() {
        return CourseManagementTrainerController.SingletonManager.INSTANCE;
    }

    public void createCourse(CourseBean bean) throws SQLException {
        Trainer trainer = new TrainerDAO().loadTrainer(bean.getOwner());
        Course course = new Course(bean.getName(), bean.getDescription(), bean.getFitnessLevel(), trainer, bean.getEquipment());
        if(bean.getLessonBeanList() != null){
            course.addAllLessons(setLesson(bean.getLessonBeanList(), course));
        }
        new CourseDAO().saveCourse(course);
    }

    private List<Lesson> setLesson(List<LessonBean> lessonBeanList, Course course) {
        List<Lesson> list = new ArrayList<>();
        for(LessonBean bean: lessonBeanList) {
            Lesson lesson = new Lesson(course, bean.getLessonDay(), bean.getLessonStartTime(), bean.getLessonEndTime());
            list.add(lesson);
        }
        return list;
    }

}
