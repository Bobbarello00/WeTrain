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
        return new DenialNotification();
    }

    public Notification createSubscribeNotification() {
        return new SubscriptionNotification();
    }

    public Notification createNotification(int idNotification, int type, String info, LocalDateTime notificationDate, User user) {
        switch (type) {
            case (1) -> {
                return new SubscriptionNotification();
            }
            case (2) -> {
                return new DenialNotification();
            }
            case (3) -> {
                return new ConfirmNotification();
            }
        }
        return new DenialNotification();
    }
}
