package beans;

import java.util.ArrayList;
import java.util.List;

public class WorkoutPlanBean {
    private int id;
    private List<WorkoutDayBean> workoutDayList;

    public WorkoutPlanBean(int id) {
        this.id = id;
        this.workoutDayList = new ArrayList<>();
    }

    public WorkoutPlanBean() {
        this.workoutDayList = new ArrayList<>();
    }

    public List<WorkoutDayBean> getWorkoutDayList() {
        return workoutDayList;
    }

    public void setWorkoutDayList(List<WorkoutDayBean> workoutDayList) {
        this.workoutDayList = workoutDayList;
    }

    public int getId() {
        return id;
    }

    public void addWorkoutDayBean(WorkoutDayBean workoutDayBean) {
        workoutDayList.add(workoutDayBean);
    }
}
