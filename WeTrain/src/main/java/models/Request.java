package models;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Request implements Serializable {
    private final int id;
    private final LocalDateTime requestDate;
    private final String info;
    private final Athlete athlete;
    private final Trainer trainer;

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
}
