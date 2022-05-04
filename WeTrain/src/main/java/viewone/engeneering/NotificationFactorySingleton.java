package viewone.engeneering;

import model.notification.*;
import model.User;

import java.time.LocalDateTime;

public class NotificationFactorySingleton {
    private static final NotificationFactorySingleton instance = new NotificationFactorySingleton();

    private NotificationFactorySingleton() {}

    public static NotificationFactorySingleton getInstance() {
        return instance;
    }

   /* public Notification createDenialNotification() {
        return new RejectedRequestNotification();
    }

    public Notification createSubscribeNotification() {
        return new SubscriptionToTrainerNotification();
    }*/

    public Notification createNotification(int idNotification, int type, String info, LocalDateTime notificationDate, User sender, User receiver) {
        /*switch (NotificationEnum.of(type)) {
            case (SUBSCRIPTIONTOTRAINER) -> {
                return new SubscriptionToTrainerNotification();
            }
            case (SUBSCRIPTIONTOCOURSE) -> {
                return new SubscriptionToCourseNotification();
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
