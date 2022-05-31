package test;

import controllers.LoginController;
import controllers.SubscribeToCourseController;
import org.junit.jupiter.api.Test;
import viewone.beans.CourseBean;

import static org.junit.jupiter.api.Assertions.*;

class TestSubscribeToCourseController {
    private static final String EMAIL = "andrea@gmail.com";
    private static final String PASSWORD = "Ciaociao00!";
    private static final String COURSE_OWNER_FC = "RGNMAR00E21D662Q";
    public static final String COURSE_DESCRIPTION = "A form of high intensity interval training, " +
            "CrossFit is a strength and conditioning workout that is made up of functional " +
            "movement performed at a high intensity level. These movements are actions that " +
            "you perform in your day-to-day life, like squatting, pulling, pushing etc... " +
            "but be aware, this course is intended for experienced users.";
    public static final String COURSE_FITNESS_LEVEL = "Advanced";
    public static final String COURSE_EQUIPMENT = "No equipment is required, but be sure to bring a towel... " +
            "you are going to sweat a lot that's for sure!";
    public static final String COURSE_NAME = "Crossfit";
    private CourseBean courseBean = new CourseBean(COURSE_NAME,
            COURSE_DESCRIPTION,
            COURSE_FITNESS_LEVEL,
            COURSE_OWNER_FC,
            COURSE_EQUIPMENT);
    /*@author Testing:  Edoardo Manenti
                        Matricola 0278821
    */
    //TODO login in loginController
    @Test
    void testSubscriptionToCourse() {
        boolean flag = false;
        SubscribeToCourseController subscribeToCourseController = new SubscribeToCourseController();
        LoginController loginController = new LoginController();
        subscribeToCourseController.subscribeToCourse(courseBean);
        assertTrue(flag);
    }

}