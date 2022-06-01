package viewone.beans_viewone;

import beans.DayBean;

public class DayBeanA implements DayBean {
    private final String day;

    public DayBeanA(String day) {
        this.day = day;
    }

    public String getDay() {
        return day;
    }
}
