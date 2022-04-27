package controller;

import database.dao_classes.CourseDAO;
import database.dao_classes.TrainerDAO;
import exception.DBConnectionFailedException;
import model.Course;
import model.Lesson;
import model.Trainer;
import viewone.bean.CourseBean;
import viewone.bean.LessonBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseManagementTrainerController {

    public void createCourse(CourseBean bean) throws SQLException, DBConnectionFailedException {
        Trainer trainer = new TrainerDAO().loadTrainer(bean.getOwner());
        Course course = new Course(bean.getName(), bean.getDescription(), bean.getFitnessLevel(), trainer, bean.getEquipment());
        if(bean.getLessonBeanList() != null){
            course.addAllLessons(setLesson(bean.getLessonBeanList()));
        }
        new CourseDAO().saveCourse(course);
    }

    private List<Lesson> setLesson(List<LessonBean> lessonBeanList) {
        List<Lesson> list = new ArrayList<>();
        for(LessonBean bean: lessonBeanList) {
            Lesson lesson = new Lesson(bean.getLessonDay(), bean.getLessonStartTime(), bean.getLessonEndTime());
            list.add(lesson);
        }
        return list;
    }

}
