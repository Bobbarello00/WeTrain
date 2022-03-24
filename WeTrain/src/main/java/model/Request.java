package model;

import java.time.LocalDateTime;

public class Request {
    private int id;
    private LocalDateTime requestDate;
    private Athlete athlete;
    private Trainer trainer;
    public Request(int id, LocalDateTime requestDate, Athlete athlete, Trainer trainer){
        this.id = id;
        this.requestDate = requestDate;
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
}
