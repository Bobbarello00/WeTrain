package model;

import java.io.Serializable;

public class Exercise implements Serializable {
    private int id;
    private final String name;
    private final String info;
    private final Trainer trainer;

    public Exercise(int id, String name, String information, Trainer trainer){
        this.id = id;
        this.name = name;
        this.info = information;
        this.trainer = trainer;
    }

    public Exercise(String name, String information, Trainer trainer){
        this.name = name;
        this.info = information;
        this.trainer = trainer;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public Trainer getTrainer() {
        return trainer;
    }
}
