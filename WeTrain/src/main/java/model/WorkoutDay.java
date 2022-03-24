package model;

import java.util.List;

public class WorkoutDay {
    private int id;
    private WorkoutPlan workoutPlan;
    private int day;
    private List<Exercise> listExercise;

    public WorkoutDay(int id, WorkoutPlan workoutPlan){
        this.id = id;
        this.workoutPlan = workoutPlan;
    }

    public List<Exercise> getListExercise(){
        return listExercise;
    }

    public void removeExercise(Exercise exercise){
        listExercise.remove(exercise);
    }

    public void insertExercise(Exercise exercise){
        listExercise.add(exercise);
    }

    public int getId() {
        return id;
    }

    public WorkoutPlan getWorkoutPlan(){
        return workoutPlan;
    }
}
