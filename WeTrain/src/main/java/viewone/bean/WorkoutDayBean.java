package viewone.bean;

import java.util.List;

public class WorkoutDayBean {

    private String day;
    private String info;
    List<ExerciseBean> exerciseBeanList;

    public WorkoutDayBean(String day, String info) {
        this.day = day;
        this.info = info;
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

    public String getInfo() {
        return info;
    }
}
