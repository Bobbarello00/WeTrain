package viewtwo.beans_viewtwo;

import beans.ExerciseBean;
import beans.ExerciseForWorkoutPlanBean;

import java.time.DayOfWeek;

public class ExerciseForWorkoutPlanBeanB implements ExerciseForWorkoutPlanBean {
    private final int day;
    private final ExerciseBean  exerciseBean;

    public ExerciseForWorkoutPlanBeanB(ExerciseBean exerciseBean, int day) {
        this.day = day;
        this.exerciseBean = exerciseBean;
    }

    public String getDay() {
        return DayOfWeek.of(day).name();
    }

    @Override public ExerciseBean getExerciseBean() {
        return exerciseBean;
    }
}
