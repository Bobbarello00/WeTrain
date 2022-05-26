package model;

import viewone.engeneering.ExerciseCatalogue;
import viewone.engeneering.Observer;

public class WorkoutDayObserver extends WorkoutDay implements Observer {
    private final ExerciseCatalogue exerciseCatalogue;
    private final WorkoutPlan workoutPlan;

    public WorkoutDayObserver(String day, ExerciseCatalogue exerciseCatalogue, WorkoutPlan workoutPlan) {
        super(day);
        this.workoutPlan = workoutPlan;
        this.exerciseCatalogue = exerciseCatalogue;
        exerciseCatalogue.addObserver(this);
    }

    public void detachFromObservable() {
        exerciseCatalogue.removeObserver(this);
    }

    @Override public void update(Exercise deletedExercise) {
        for(int i = 0; i < exerciseList.size(); i++) {
            if(exerciseList.get(i).getId() == deletedExercise.getId()) {
                exerciseList.remove(i);
                break;
            }
        }
        if(exerciseList.isEmpty()) {
            detachFromObservable();
            workoutPlan.removeWorkoutDay(this);
        }
    }
}
