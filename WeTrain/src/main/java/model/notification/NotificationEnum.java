package model.notification;

import exception.NotificationEnumException;

public enum NotificationEnum {

    SUBSCRIPTIONTOCOURSE,
    SUBSCRIPTIONTOTRAINER,
    REJECTEDREQUEST,
    WORKOUTPLANREADY,
    COMMUNICATION,
    COURSEMODIFIED,
    LESSONSTARTED;

    private static final NotificationEnum[] ENUMS = NotificationEnum.values();

    public static NotificationEnum of(int notificationType) {
        if (notificationType < 1 || notificationType > NotificationEnum.ENUMS.length) {
            throw new NotificationEnumException("Invalid value for NotificationEnum: " + notificationType);
        }
        return ENUMS[notificationType - 1];
    }
}
