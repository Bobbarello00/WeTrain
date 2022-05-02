package viewone.engeneering;

import model.notification.DenialNotification;
import model.notification.Notification;
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
        return new DenialNotification();
    }

    public Notification createNotification(int idNotification, int type, String info, LocalDateTime notificationDate, User user) {
        switch(type){
            case 0 -> {
                return new DenialNotification();
            }
        }
        return new DenialNotification();
    }
}
