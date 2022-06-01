package viewone.beans_viewone;

import beans.CourseSearchBean;

public class CourseSearchBeanA implements CourseSearchBean {
    private final String fitnessLevel;
    private final Boolean[] days;
    private final String name;

    public CourseSearchBeanA(String name, String fitnessLevel, Boolean[] days) {
        this.fitnessLevel = fitnessLevel;
        this.name = name;
        this.days = days;
    }

    public String getFitnessLevel() {
        return fitnessLevel;
    }

    public Boolean[] getDays() {
        return days;
    }

    public String getName() {
        return name;
    }

}
