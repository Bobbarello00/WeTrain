package model.notification;

import exception.NotificationEnumException;

public enum NotificationEnum {

    SUBSCRIPTIONTOCOURSE,
    SUBSCRIPTIONTOTRAINER,
    REJECTEDREQUEST,
    WORKOUTPLANREADY,
    COMMUNICATION,
    EMAILRECEIVED;

    private static final NotificationEnum[] ENUMS = NotificationEnum.values();

    public static NotificationEnum of(int notificationType) {
        if (notificationType < 0 || notificationType > NotificationEnum.ENUMS.length - 1) {
            throw new NotificationEnumException("Invalid value for NotificationEnum: " + notificationType);
        }
        return ENUMS[notificationType];
    }
}
