package viewone.beans;

import java.util.ArrayList;
import java.util.List;

public class CourseBean {

    private int id;
    private final String name;
    private final String owner;
    private final String description;
    private final String fitnessLevel;
    private final String equipment;
    private List<LessonBean> lessonBeanList;

    public CourseBean(int id, String name, String description, String fitnessLevel, String owner, String equipment){
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.description = description;
        this.fitnessLevel = fitnessLevel;
        this.equipment = equipment;
        lessonBeanList = new ArrayList<>();
    }

    public CourseBean(String name, String description, String fitnessLevel, String owner, String equipment){
        this.name = name;
        this.owner = owner;
        this.description = description;
        this.fitnessLevel = fitnessLevel;
        this.equipment = equipment;
        lessonBeanList = new ArrayList<>();
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<LessonBean> getLessonBeanList() {
        return lessonBeanList;
    }

    public void setLessonBeanList(List<LessonBean> lessonBeanList) {
        this.lessonBeanList = new ArrayList<>();
        for(LessonBean lessonBean: lessonBeanList) {
            this.lessonBeanList.add(new LessonBean(
                    lessonBean.getId(),
                    lessonBean.getLessonDay(),
                    lessonBean.getLessonStartTime(),
                    lessonBean.getLessonEndTime()
            ));
        }
    }

    public String getDescription() {
        return description;
    }

    public String getEquipment() {
        return equipment;
    }

    public String getFitnessLevel() {
        return fitnessLevel;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

}
