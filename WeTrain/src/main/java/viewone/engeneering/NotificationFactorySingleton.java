package viewone.engeneering;

import database.dao_classes.CourseDAO;
import exception.DBConnectionFailedException;
import model.Course;
import model.User;
import model.notification.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import model.notification.NotificationEnum;

import static model.notification.NotificationEnum.*;

public class NotificationFactorySingleton {
    private static final NotificationFactorySingleton instance = new NotificationFactorySingleton();

    private NotificationFactorySingleton() {}

    public static NotificationFactorySingleton getInstance() {
        return instance;
    }

    public Notification createCommunicationNotification(User sender, User receiver, Course course, String text) {
        return new CommunicationNotification(
                sender,
                receiver,
                text,
                course);
    }

    public Notification createRejectedRequestNotification(User sender, User receiver) {
        return new RejectedRequestNotification(
                sender,
                receiver
        );
    }

    public Notification createCourseModifiedNotification(User sender, User receiver, Course course) {
        return new CourseModifiedNotification(
                sender,
                receiver,
                course
        );
    }

    public Notification createLessonStartedNotification(User sender, User receiver, Course course) {
        return new LessonStartedNotification(
                sender,
                receiver,
                course
        );
    }


    public Notification createSubscribeToTrainerNotification(User sender, User receiver, int subscribers) {
        return new SubscriptionToTrainerNotification(
                sender,
                receiver,
                subscribers
        );
    }

    public Notification createSubscribeToCourseNotification(User sender, User receiver, Course course, int subscribers) {
        return new SubscriptionToCourseNotification(
                sender,
                receiver,
                course,
                subscribers
        );
    }

    public Notification createWorkoutPlanReadyNotification(User sender, User receiver) {
        return new WorkoutPlanReadyNotification(
                sender,
                receiver
        );
    }

    public Notification createNotification(int idNotification, int type, String info, LocalDateTime dateTime, User sender, User receiver) throws DBConnectionFailedException, SQLException {
        NotificationEnum type1 = NotificationEnum.of(type);
        if(type1 == SUBSCRIPTIONTOTRAINER) {
            return new SubscriptionToTrainerNotification(
                    idNotification,
                    sender,
                    receiver,
                    Integer.parseInt(info),
                    dateTime
            );
        } else if (type1 == SUBSCRIPTIONTOCOURSE) {
            /*return new SubscriptionToCourseNotification(
                    idNotification,
                    sender,
                    receiver,
                    Integer.parseInt(info),
                    dateTime
            );*/
        } else if (type1 == REJECTEDREQUEST) {

        } else if (type1 == WORKOUTPLANREADY) {

        } else if (type1 == COMMUNICATION) {
            String[] params = info.split("-");
            return new CommunicationNotification(
                    idNotification,
                    sender,
                    receiver,
                    params[2],
                    new CourseDAO().loadCourse(Integer.parseInt(params[1])),
                    dateTime
            );
        }
        return null;

    }
}
