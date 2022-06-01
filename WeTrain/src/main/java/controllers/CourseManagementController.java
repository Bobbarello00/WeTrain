package controllers;

import models.Course;
import models.Lesson;
import viewone.beans.CourseBean;
import viewone.beans.LessonBean;

import java.util.ArrayList;
import java.util.List;

public abstract class CourseManagementController {

    protected List<CourseBean> getCourseBeanList(List<Course> courseList) {
        List<CourseBean> beanList = new ArrayList<>();
        for(Course course : courseList) {
            CourseBean courseBean = new CourseBean(
                    course.getId(),
                    course.getName(),
                    course.getDescription(),
                    course.getFitnessLevel(),
                    course.getOwner().getName() + " " + course.getOwner().getSurname(),
                    course.getEquipment());

            List<LessonBean> lessonBeanList = new ArrayList<>();
            for(Lesson lesson: course.getLessonList()){
                lessonBeanList.add(new LessonBean(
                        lesson.getId(),
                        lesson.getLessonDay(),
                        lesson.getLessonStartTime(),
                        lesson.getLessonEndTime()));
            }
            courseBean.setLessonBeanList(lessonBeanList);
            beanList.add(courseBean);
        }
        return beanList;
    }
}
