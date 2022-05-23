package viewone.engeneering;

import model.Exercise;

import java.util.List;

public class ExerciseCatalogue extends Observable{
    private final List<Exercise> exerciseList;


    public ExerciseCatalogue(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    public void notifyDeletedExercise(Exercise exerciseToDelete) {
        for(int i = 0; i < exerciseList.size(); i++) {
            if(exerciseList.get(i).getId() == exerciseToDelete.getId()) {
                exerciseList.remove(i);
                break;
            }
        }
        for (Observer observer : this.observers) {
            observer.update(exerciseToDelete);
        }
    }

    public List<Exercise> getExerciseList() {
        return exerciseList;
    }

    public void addExercise(Exercise exercise) {
        exerciseList.add(exercise);
    }
}
