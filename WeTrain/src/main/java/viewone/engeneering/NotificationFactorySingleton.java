package viewone.engeneering;

import model.notification.DenialNotification;
import model.notification.Notification;
import model.User;
import model.notification.NotificationEnum;
import model.notification.SubscriptionNotification;

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
        switch(NotificationEnum.of(type)){
            case (NotificationEnum.SUBSCRIPTION) -> {
                return new DenialNotification();
            }
        }
        return new DenialNotification();
    }
}
