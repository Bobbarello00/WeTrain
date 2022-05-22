package viewone.bean;

public class ExerciseForWorkoutPlanBean extends  ExerciseBean{
    private final String day;

    public ExerciseForWorkoutPlanBean(ExerciseBean exerciseBean, String day) {
        super(exerciseBean.getId(), exerciseBean.getName(), exerciseBean.getInfo());
        this.day = day;
    }

    public String getDay() {
        return day;
    }
}
