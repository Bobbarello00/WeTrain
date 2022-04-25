package viewone.bean;

import java.util.List;

public class WorkoutDayBean {

    private String day;
    List<ExerciseBean> exerciseBeanList;

    public WorkoutDayBean(String day) {
        this.day = day;
    }

    public List<ExerciseBean> getExerciseBeanList() {
        return exerciseBeanList;
    }

    public void setExerciseBeanList(List<ExerciseBean> exerciseBeanList) {
        this.exerciseBeanList = exerciseBeanList;
    }

    public String getDay() {
        return day;
    }
}
