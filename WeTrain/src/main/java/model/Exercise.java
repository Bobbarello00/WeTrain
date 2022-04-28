package model;

import java.io.Serializable;

public class Exercise implements Serializable {
    private int id;
    private String name;
    private String info;
    private Trainer trainer;

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

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }
}
