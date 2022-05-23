package viewone.engeneering;

import model.Exercise;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {
    private final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    public void notifyObservers(Exercise exerciseToDelete){
        for (Observer observer : this.observers) {
            observer.update(exerciseToDelete);
        }
    }
}
