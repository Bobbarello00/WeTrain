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
}
