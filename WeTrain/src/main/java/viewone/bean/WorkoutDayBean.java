package viewone.bean;

import java.util.List;

public class WorkoutDayBean {

    List<ExerciseBean> exerciseBeanList;

    public WorkoutDayBean() {}

    public List<ExerciseBean> getExerciseBeanList() {
        return exerciseBeanList;
    }

    public void setExerciseBeanList(List<ExerciseBean> exerciseBeanList) {
        this.exerciseBeanList = exerciseBeanList;
    }
}
