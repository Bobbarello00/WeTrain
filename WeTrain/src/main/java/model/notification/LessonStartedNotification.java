package model.notification;

import model.Course;
import model.User;

import java.time.LocalDateTime;

public class LessonStartedNotification extends Notification{
    private final Course course;

    public LessonStartedNotification(int id, User sender, User receiver, Course course, LocalDateTime dateTime) {
        super(id, sender, receiver, dateTime);
        this.course = course;
    }

    public LessonStartedNotification(User sender, User receiver, Course course) {
        super(sender, receiver);
        this.course = course;
    }

    @Override public String promptMessage() {
        return String.format("""
                Hi!
                Your trainer %s started the lesson of the course %s.
                Join it.
                """,
                sender.getName() + " " + sender.getSurname(),
                course.getName());
    }

    @Override public NotificationEnum getType() {
        return NotificationEnum.LESSONSTARTED;
    }

    @Override public String getDescription() {
        return null;
    }
}
