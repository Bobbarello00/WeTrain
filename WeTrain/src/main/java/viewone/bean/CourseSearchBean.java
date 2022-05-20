package viewone.bean;

public class CourseSearchBean {
    private final String fitnessLevel;
    private final Boolean[] days;
    private final String name;

    public CourseSearchBean(String name, String fitnessLevel, Boolean[] days) {
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
