package viewone.bean;

import java.util.List;

public class CourseBean {

    private int id;
    private String name;
    private String owner;
    private String description;
    private String fitnessLevel;
    private String equipment;
    private List<LessonBean> lessonBeanList;

    public CourseBean(int id, String name, String description, String fitnessLevel, String owner, String equipment){
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.description = description;
        this.fitnessLevel = fitnessLevel;
        this.equipment = equipment;
    }

    public CourseBean(String name, String description, String fitnessLevel, String owner, String equipment){
        this.name = name;
        this.owner = owner;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
