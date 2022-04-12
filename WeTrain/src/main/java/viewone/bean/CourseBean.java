package viewone.bean;

import java.util.List;

public class CourseBean  extends CourseEssentialBean{

    private String description;
    private String fitnessLevel;
    private String equipment;
    private List<LessonBean> lessonBeanList;

    public CourseBean(int id, String name, String description, String fitnessLevel, String owner, String equipment){
        super(id, name, owner);
        this.description = description;
        this.fitnessLevel = fitnessLevel;
        this.equipment = equipment;
    }

    public CourseBean(String name, String description, String fitnessLevel, String owner, String equipment){
        super(name, owner);
        this.description = description;
        this.fitnessLevel = fitnessLevel;
        this.equipment = equipment;
    }

    public List<LessonBean> getLessonBeanList() {
        return lessonBeanList;
    }

    public void setLessonBeanList(List<LessonBean> lessonBeanList) {
        this.lessonBeanList = lessonBeanList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getFitnessLevel() {
        return fitnessLevel;
    }

    public void setFitnessLevel(String fitnessLevel) {
        this.fitnessLevel = fitnessLevel;
    }
}
