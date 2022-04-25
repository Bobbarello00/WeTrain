package viewone.bean;

import java.util.List;

public class WorkoutPlanBean {
    private final int id;
    private List<WorkoutDayBean> workoutDayList;

    public WorkoutPlanBean(int id) {
        this.id = id;
        this.workoutDayList = null;
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
}
