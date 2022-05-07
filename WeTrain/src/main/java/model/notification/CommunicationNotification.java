package model.notification;

import model.Course;
import model.User;

import java.time.LocalDateTime;

public class CommunicationNotification extends Notification{
    private final String text;
    private final Course course;


    public CommunicationNotification(int id, User sender, User receiver, String text, Course course, LocalDateTime dateTime) {
        super(id, sender, receiver, dateTime);
        this.text = text;
        this.course = course;
    }

    public CommunicationNotification(User sender, User receiver, String text, Course course) {
        super(sender, receiver);
        this.text = text;
        this.course = course;
    }

    @Override public String promptMessage() {
        return String.format("""
                COMMUNICATION FROM COURSE "%s":
                
                %s
                """,
                course.getName(),
                text);
    }

    @Override public NotificationEnum getType() {
        return NotificationEnum.COMMUNICATION;
    }

    @Override public String getDescription() {
        return course.getId() + "-" + text;
    }

    public Course getCourse() {
        return course;
    }
}
