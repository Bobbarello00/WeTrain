package viewone.engeneering;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {

    protected final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }
}
