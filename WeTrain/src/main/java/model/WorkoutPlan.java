package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WorkoutPlan implements Serializable {
    private int id;
    private List<WorkoutDay> workoutDayList;

    public WorkoutPlan(int id){
        this();
        this.id = id;
    }

    public WorkoutPlan(){
        this.workoutDayList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public List<WorkoutDay> getWorkoutDayList() {
        return workoutDayList;
    }

    public void addAllWorkoutDays(List<WorkoutDay> workoutDayList) {
        this.workoutDayList = new ArrayList<>();
        for (WorkoutDay workoutDay : workoutDayList) {
            WorkoutDay newWorkoutDay = new WorkoutDay(workoutDay.getDay());
            newWorkoutDay.addAllExercise(workoutDay.getExerciseList());
            addWorkoutDay(newWorkoutDay);
        }
    }

    public void addWorkoutDay(WorkoutDay workoutDay) {
        workoutDayList.add(workoutDay);
    }

    public void removeWorkoutDay(WorkoutDay workoutDay) {
        workoutDayList.remove(workoutDay);
    }
}
