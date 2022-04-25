package model;

import java.util.List;

public class WorkoutDay {
    private Integer id;
    private String day;
    private String info;
    private List<Exercise> exerciseList;

    public WorkoutDay(int id, String day, String info){
        this.id = id;
        this.day = day;
        this.info = info;
    }

    public List<Exercise> getExerciseList(){
        return exerciseList;
    }

    public void removeExercise(Exercise exercise){
        exerciseList.remove(exercise);
    }

    public void addExercise(Exercise exercise){
        exerciseList.add(exercise);
    }

    public void addAllExercise(List<Exercise> list){
        exerciseList = list;
    }

    public int getId() {
        return id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getInfo() {
        return info;
    }
}
