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

    public Notification createDenialNotification() {
        return new RejectedRequestNotification();
    }

    public Notification createSubscribeNotification() {
        return new SubscriptionToTrainerNotification();
    }

    public Notification createNotification(int idNotification, int type, String info, LocalDateTime notificationDate, User user) {
        switch (type) {
            case (1) -> {
                return new SubscriptionToTrainerNotification();
            }
            case (2) -> {
                return new RejectedRequestNotification();
            }
            case (3) -> {
                return new WorkoutPlanReadyNotification();
            }
        }
        return new RejectedRequestNotification();
    }
}
