package model;

import java.time.LocalDateTime;

public class Request {
    private int id;
    private LocalDateTime requestDate;
    private String info;
    private Athlete athlete;
    private Trainer trainer;

    public Request(int id, LocalDateTime requestDate, String info, Athlete athlete, Trainer trainer){
        this.id = id;
        this.requestDate = requestDate;
        this.info = info;
        this.athlete = athlete;
        this.trainer = trainer;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public Athlete getAthlete() {
        return athlete;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
