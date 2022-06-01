package viewone.beans_viewone;

import beans.ExerciseBean;
import beans.ExerciseForWorkoutPlanBean;

public class ExerciseForWorkoutPlanBeanA implements ExerciseForWorkoutPlanBean {
    private final String day;
    private final ExerciseBean  exerciseBean;

    public ExerciseForWorkoutPlanBeanA(ExerciseBean exerciseBean, String day) {
        this.day = day;
        this.exerciseBean = exerciseBean;
    }

    @Override public String getDay() {
        return day;
    }

    @Override public ExerciseBean getExerciseBean() {
        return exerciseBean;
    }
}
