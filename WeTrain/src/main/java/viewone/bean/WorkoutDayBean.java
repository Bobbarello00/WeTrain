package viewone.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WorkoutDayBean {

    private String day;
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

    public void removeExerciseBean(ExerciseBean exerciseBean) {
        for(ExerciseBean exercise: exerciseBeanList){
            if(Objects.equals(exercise.getName(), exerciseBean.getName())){
                exerciseBeanList.remove(exercise);
                return;
            }
        }
        System.out.println("L'esercizio non appartiene a nessun WorkoutDay");
    }
}
