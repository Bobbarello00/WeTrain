package model;

import java.util.List;

public class Course {
    private String name;
    private List<Lesson> lessons;
    private String owner;
    private String equipment;

    public Course(String name, List<Lesson> lessons, String owner, String equipment){
        this.name = name;
        this.lessons = lessons;
        this.owner = owner;
        this.equipment = equipment;
    }

}