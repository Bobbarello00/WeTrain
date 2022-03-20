package model;

public class Exercise{
    private String id;
    private String name;
    private String information;
    private Trainer trainer;
    public Exercise(String name, String information, Trainer trainer){
        this.name = name;
        this.information = information;
        this.trainer = trainer;
    }

}
