package model.notification;

public enum NotificationEnum {

    SUBSCRIPTION,
    DENIAL,
    CONFIRM,
    COMUNICATION;

    private static final NotificationEnum[] ENUMS = NotificationEnum.values();

    public static NotificationEnum of(int notificationType) {
        if (notificationType < 1 || notificationType > NotificationEnum.ENUMS.length) {
            //throw new DateTimeException("Invalid value for DayOfWeek: " + dayOfWeek);
        }
        return ENUMS[notificationType - 1];
    }
}
