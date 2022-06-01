package beans;

import exceptions.invalid_data_exception.EmptyFieldsException;

public class CommunicationBean {
    private String text;
    private CourseBean courseBean;

    public CommunicationBean(String text, CourseBean courseBean) throws EmptyFieldsException {
        if(text.isEmpty()) {
            throw new EmptyFieldsException();
        }
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
