package model;

import java.util.List;

public class WorkoutPlan {
    private int id;
    private Athlete athlete;
    private List<WorkoutDay> workoutDayList;

    public WorkoutPlan(int id, Athlete athlete){
        this.id = id;
        this.athlete = athlete;
    }

    public WorkoutPlan(){}

    public int getId() {
        return id;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public List<WorkoutDay> getWorkoutDayList() {
        return workoutDayList;
    }

    public void addAllWorkoutDays(List<WorkoutDay> workoutDayList) {
        this.workoutDayList = workoutDayList;
    }
}
