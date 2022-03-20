package model;

public class WorkoutDay {
    private int id;
    WorkoutPlan workoutPlan;
    public WorkoutDay(int id, WorkoutPlan workoutPlan){
        this.id = id;
        this.workoutPlan = workoutPlan;
    }
}
