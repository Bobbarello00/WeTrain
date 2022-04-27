package model;

import java.util.List;

public class WorkoutPlan {
    private int id;
    private List<WorkoutDay> workoutDayList;

    public WorkoutPlan(int id){
        this.id = id;
    }

    public WorkoutPlan(){}

    public int getId() {
        return id;
    }

    public List<WorkoutDay> getWorkoutDayList() {
        return workoutDayList;
    }

    public void addAllWorkoutDays(List<WorkoutDay> workoutDayList) {
        this.workoutDayList = workoutDayList;
    }
}
