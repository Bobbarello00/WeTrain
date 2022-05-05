package model.notification;

import model.Course;
import model.User;

import java.time.LocalDateTime;

public class SubscriptionToCourseNotification extends Notification {
    private final int courseSubscribers;
    private final Course course;

    public SubscriptionToCourseNotification(int id, User sender, User receiver, int courseSubscribers, Course course, LocalDateTime dateTime) {
        super(id, sender, receiver, dateTime);
        this.courseSubscribers = courseSubscribers;
        this.course = course;
    }

    public SubscriptionToCourseNotification(User sender, User receiver, Course course, int courseSubscribers) {
        super(sender, receiver);
        this.course = course;
        this.courseSubscribers = courseSubscribers;
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

    @Override public NotificationEnum getType() {
        return NotificationEnum.SUBSCRIPTIONTOCOURSE;
    }

    @Override public String getDescription() {
        return course.getId() + "-" + courseSubscribers;
    }
}
