package models.notification;

import models.User;

import java.time.LocalDateTime;

public class WorkoutPlanReadyNotification extends Notification{

    public WorkoutPlanReadyNotification(int id, User sender, User receiver, LocalDateTime dateTime) {
        super(id, sender, receiver, dateTime);
    }

    public WorkoutPlanReadyNotification(User sender, User receiver) {
        super(sender, receiver);
    }

    @Override public String promptMessage() {
        return String.format("""
                Good news!
                Your Trainer '%s' just uploaded your new workout plan.
                Be sure to check it out from 'Your Workout Plan' page!
                """,
                sender.getName() + " " + sender.getSurname());
    }

    @Override public NotificationEnum getType() {
        return NotificationEnum.WORKOUTPLANREADY;
    }

    @Override public String getDescription() {
        return "";
    }
}
