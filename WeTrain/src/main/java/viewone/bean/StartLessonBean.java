package viewone.bean;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class StartLessonBean {
    private final CourseBean courseBean;
    private String url;

    public StartLessonBean(CourseBean courseBean, String url) throws IOException {
        this.courseBean = courseBean;
        setUrl(url);
    }

    public CourseBean getCourseBean() {
        return courseBean;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) throws IOException {
        URL urlToCheck = new URL(url);
        URLConnection conn = urlToCheck.openConnection();
        conn.connect();
        this.url = String.valueOf(urlToCheck);
    }
}
