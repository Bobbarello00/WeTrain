package exceptions;

public class NoScheduledLessonException extends PersonalizedException{

    public NoScheduledLessonException() {
        super(
                "NO LESSON AVAILABLE",
                "There aren't any scheduled lessons now.",
                "Check the course schedule."
        );
    }

    public NoScheduledLessonException(String title) {
        super(
                title,
                "There aren't any scheduled lessons now.",
                "Check the course schedule."
        );
    }
}
