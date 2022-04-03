package model;

import java.util.List;

public class WorkoutDay {
    private Integer id;
    private final WorkoutPlan workoutPlan;
    private int day;
    private List<Exercise> exerciseList;

    public WorkoutDay(int id, WorkoutPlan workoutPlan){
        this.id = id;
        this.workoutPlan = workoutPlan;
    }

    public WorkoutDay(WorkoutPlan workoutPlan){
        this.workoutPlan = workoutPlan;
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

    public WorkoutPlan getWorkoutPlan(){
        return workoutPlan;
    }
}
