package viewone.bean;

public class CourseSearchBean {
    private String fitnessLevel;
    private Boolean[] days;
    private String name;

    public CourseSearchBean(String name, String fitnessLevel, Boolean[] days) {
        this.fitnessLevel = fitnessLevel;
        this.name = name;
        this.days = days;
    }

    public String getFitnessLevel() {
        return fitnessLevel;
    }

    public void setFitnessLevel(String fitnessLevel) {
        this.fitnessLevel = fitnessLevel;
    }

    public Boolean[] getDays() {
        return days;
    }

    public void setDays(Boolean[] days) {
        this.days = days;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
