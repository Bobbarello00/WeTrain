package viewone.bean;

public class StartLessonBean {
    private final CourseBean courseBean;
    private final String url;

    public StartLessonBean(CourseBean courseBean, String url){
        this.courseBean = courseBean;
        this.url = url;
    }

    public CourseBean getCourseBean() {
        return courseBean;
    }

    public String getUrl() {
        return url;
    }

}
