package model.notification;

import model.Course;
import model.User;

import java.time.LocalDateTime;

public class SubscriptionToCourseNotification extends Notification {
    private final int courseSubscribers;
    private final Course course;

    /*
    Good news!
    The Athlete '%s' Subscribed to your course '%s'.
    Your course is earning popularity and now its at %d subscribers!
    */

    public SubscriptionToCourseNotification(int id, User sender, User receiver, LocalDateTime date, int courseSubscribers, Course course) {
        super(id, sender, receiver, date);
        this.courseSubscribers = courseSubscribers;
        this.course = course;
    }

    @Override public String promptMessage() {
        return String.format("""
                Good news!
                The Athlete %s Subscribed to your course %s.
                Your course is earning popularity and now its at %d subscribers!
                """,
                sender.getName() + " " + sender.getSurname(),
                course.getName(),
                courseSubscribers);
    }

    @Override public int getType() {
        return NotificationEnum.SUBSCRIPTIONTOCOURSE.ordinal();
    }

    @Override public String getDescription() {
        return null;
    }
}
