package viewone.bean;

import java.util.ArrayList;
import java.util.List;

public class WorkoutDayBean {

    private final String day;
    List<ExerciseBean> exerciseBeanList;

    public WorkoutDayBean(String day) {
        this.day = day;
        this.exerciseBeanList = new ArrayList<>();
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

    public void addExerciseBean(ExerciseBean exerciseBean) {
        exerciseBeanList.add(exerciseBean);
    }

}
