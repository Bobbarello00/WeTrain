package viewtwo.beans_viewtwo;

import beans.DayBean;

import java.time.DayOfWeek;

public class DayBeanB implements DayBean {
    private final int day;

    public DayBeanB(int day) {
        this.day = day;
    }

    public String getDay() {
        return DayOfWeek.of(day).name();
    }
}
