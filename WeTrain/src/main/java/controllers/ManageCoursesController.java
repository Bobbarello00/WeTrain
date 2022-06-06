package controllers;

import database.dao_classes.CourseDAO;
import database.dao_classes.TrainerDAO;
import exceptions.DBUnreachableException;
import exceptions.invalid_data_exception.EmptyFieldsException;
import exceptions.invalid_data_exception.InvalidTimeException;
import models.Course;
import models.Lesson;
import models.Trainer;
import beans.CommunicationBean;
import beans.CourseBean;
import beans.LessonBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManageCoursesController extends CourseManagementController{

    private final LoginController loginController = new LoginController();
    private final NotificationsController notificationsController = new NotificationsController();

    public void createCourse(CourseBean bean) throws SQLException, DBUnreachableException, InvalidTimeException {
        Trainer trainer = new TrainerDAO().loadTrainer(bean.getOwner());
        Course course = new Course(
                bean.getName(),
                bean.getDescription(),
                bean.getFitnessLevel(),
                trainer,
                bean.getEquipment(),
                getLessonFromBean(bean.getLessonBeanList()));
        new CourseDAO().saveCourse(course);
    }

    private List<Lesson> getLessonFromBean(List<LessonBean> lessonBeanList) throws InvalidTimeException {
        List<Lesson> list = new ArrayList<>();
        for(LessonBean bean: lessonBeanList) {
            boolean cond1 = bean.getLessonStartTime().getHour() > bean.getLessonEndTime().getHour();
            boolean cond2 = bean.getLessonStartTime().getHour() == bean.getLessonEndTime().getHour();
            boolean cond3 = bean.getLessonStartTime().getMinute() > bean.getLessonEndTime().getMinute();
            if(cond1 || (cond2 && cond3)) {
                throw new InvalidTimeException();
            }
            Lesson lesson = new Lesson(
                    bean.getLessonDay(),
                    bean.getLessonStartTime(),
                    bean.getLessonEndTime()
            );
            list.add(lesson);
        }
        return list;
    }

    public List<CourseBean> getCourseList() throws DBUnreachableException, SQLException {
        List<Course> courseList = new CourseDAO().loadAllCoursesTrainer((Trainer) loginController.getLoggedUser());
        return getCourseBeanList(courseList);
    }

    public void deleteCourse(CourseBean courseBean) throws DBUnreachableException, SQLException {
        new CourseDAO().deleteCourse(courseBean.getId());
    }

    public void modifyCourse(CourseBean courseBean, CourseBean courseToModify) throws SQLException, DBUnreachableException, EmptyFieldsException, InvalidTimeException {
        Trainer trainer = (Trainer) loginController.getLoggedUser();
        new CourseDAO().modifyCourse(
                new Course(
                        courseToModify.getName(),
                        courseToModify.getDescription(),
                        courseToModify.getFitnessLevel(),
                        trainer,
                        courseToModify.getEquipment(),
                        getLessonFromBean(courseToModify.getLessonBeanList())
                ),
                new Course(
                        courseBean.getName(),
                        courseBean.getDescription(),
                        courseBean.getFitnessLevel(),
                        trainer,
                        courseBean.getEquipment(),
                        getLessonFromBean(courseBean.getLessonBeanList())
                ));
        notificationsController.sendCourseCommunicationNotification(
                new CommunicationBean(
                        """
                        ATTENTION!
                        The trainer %s modified the course %s.
                        Be sure to check the modification!
                        """,
                        courseBean
                )
        );
    }
}
