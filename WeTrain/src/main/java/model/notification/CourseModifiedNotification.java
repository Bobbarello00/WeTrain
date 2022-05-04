package model.notification;

import model.Course;
import model.User;
import java.time.LocalDateTime;

public class CourseModifiedNotification extends Notification{
    public Course course;
    /*
    ATTENTION!
    The trainer %s modified the course %s.
    Check the modification!
    */

    public CourseModifiedNotification(int id, User sender, User receiver, Course course, LocalDateTime dateTime) {
        super(id, sender, receiver, dateTime);
        this.course = course;
    }

    public CourseModifiedNotification(User sender, User receiver, Course course) {
        super(sender, receiver);
        this.course = course;
    }

    @Override public String promptMessage() {
        return String.format("""
                ATTENTION!
                The trainer %s modified the course %s.
                Be sure to check the modification!
                """,
                sender.getName() + " " + sender.getSurname(),
                course.getName());
    }

    @Override public int getType() {
        return NotificationEnum.COURSEMODIFIED.ordinal();
    }

    @Override public String getDescription() {
        return null;
    }
}
