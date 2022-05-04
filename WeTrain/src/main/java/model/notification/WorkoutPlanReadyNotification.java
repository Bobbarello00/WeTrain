package model.notification;

import model.User;

import java.time.LocalDateTime;

public class WorkoutPlanReadyNotification extends Notification{
    /*
    Good news!
    Your Trainer '%s' just uploaded your new workout plan.
    Be sure to check it out from 'Your Workout Plan' page!
    */

    public WorkoutPlanReadyNotification(int id, User sender, User receiver, LocalDateTime date) {
        super(id, sender, receiver, date);
    }

    @Override public String promptMessage() {
        return String.format("""
                Good news!
                Your Trainer '%s' just uploaded your new workout plan.
                Be sure to check it out from 'Your Workout Plan' page!
                """,
                sender.getName() + " " + sender.getSurname());
    }

    @Override public int getType() {
        return NotificationEnum.WORKOUTPLANREADY.ordinal();
    }

    @Override public String getDescription() {
        return null;
    }
}
