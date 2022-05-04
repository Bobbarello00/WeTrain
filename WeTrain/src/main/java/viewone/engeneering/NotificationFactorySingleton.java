package viewone.engeneering;

import model.Course;
import model.User;
import model.notification.*;

import java.time.LocalDateTime;

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

    public Notification createNotification(int idNotification, int type, String info, LocalDateTime dateTime, User sender, User receiver) {
        /*switch (NotificationEnum.of(type)) {
            case (SUBSCRIPTIONTOTRAINER) -> {
                return new SubscriptionToTrainerNotification(
                        idNotification,
                        sender,
                        receiver,
                        Integer.parseInt(info),
                        dateTime
                );
            }
            case (SUBSCRIPTIONTOCOURSE) -> {
                List<String> stringList = info;
                return new SubscriptionToCourseNotification(
                        idNotification,
                        sender,
                        receiver,
                        Integer.parseInt(info),
                        dateTime
                );
            }
            case (REJECTEDREQUEST) -> {
                return new RejectedRequestNotification();
            }
            case (WORKOUTPLANREADY) -> {
                return new WorkoutPlanReadyNotification();
            }
            case (NotificationEnum.COMMUNICATION) {
                return new CommunicationNotification();
            }
        }
        return new RejectedRequestNotification();*/
        return null;
    }
}
