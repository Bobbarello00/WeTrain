package viewone.bean;

public class ExerciseForWorkoutPlanBean {
    private ExerciseBean exerciseBean;
    private String day;

    public ExerciseForWorkoutPlanBean(ExerciseBean exerciseBean, String day) {
        this.exerciseBean = exerciseBean;
        this.day = day;
    }

    public ExerciseBean getExerciseBean() {
        return exerciseBean;
    }

    public String getDay() {
        return day;
    }
}
