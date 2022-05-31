package engeneering;

import models.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ExerciseCatalogue extends Observable{
    private final ArrayList<Exercise> exerciseList;

    public ExerciseCatalogue(ArrayList<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public void removeExercise(Exercise exerciseToDelete) {
        for(int i = 0; i < exerciseList.size(); i++) {
            if(exerciseList.get(i).getId() == exerciseToDelete.getId()) {
                exerciseList.remove(i);
                break;
            }
        }
        super.notifyObservers(exerciseToDelete);
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public void addExercise(Exercise exercise) {
        exerciseList.add(exercise);
    }
}
