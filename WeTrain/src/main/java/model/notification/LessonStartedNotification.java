package model.notification;

import model.Course;
import model.User;

import java.time.LocalDateTime;

public class LessonStartedNotification extends Notification{
    private final Course course;
    /*
    Hi!
    Your trainer %s started the lesson of the course %s.
    Join it.
    */

    public LessonStartedNotification(int id, User sender, User receiver, LocalDateTime date, Course course) {
        super(id, sender, receiver, date);
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

    @Override public int getType() {
        return NotificationEnum.LESSONSTARTED.ordinal();
    }

    @Override public String getDescription() {
        return null;
    }
}
