package model;

import java.util.ArrayList;
import java.util.List;

public class ExerciseCatalogue {
    private List<Exercise> exerciseList;

    public ExerciseCatalogue(){
        this.exerciseList = new ArrayList<Exercise>();
    }

    public static List<Exercise> filterExercisesByName(String exerciseName){
        return ;
    }

    public void addExercise(Exercise newExercise){
        exerciseList.add(newExercise);
    }
}
