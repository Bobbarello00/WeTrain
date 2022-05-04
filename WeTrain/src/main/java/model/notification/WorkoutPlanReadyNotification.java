package model.notification;

public class WorkoutPlanReadyNotification extends Notification{

    /*
    Good news!
    Your Trainer '%s' just uploaded your new workout plan.
    Be sure to check it out from 'Your Workout Plan' page!
    */

    @Override public String promptMessage() {
        return null;
    }

    @Override public int getType() {
        return NotificationEnum.WORKOUTPLANREADY.ordinal();
    }

    @Override public String getDescription() {
        return null;
    }
}
