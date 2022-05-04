package viewone.bean;

public class CommunicationBean {
    private String text;
    private CourseBean courseBean;

    public CommunicationBean(String text, CourseBean courseBean) {
        this.text = text;
        this.courseBean = courseBean;
    }

    public String getText() {
        return text;
    }

    public CourseBean getCourseBean() {
        return courseBean;
    }
}
