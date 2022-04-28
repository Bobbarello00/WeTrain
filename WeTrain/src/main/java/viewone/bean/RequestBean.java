package viewone.bean;

import model.Athlete;
import model.Trainer;

import java.time.LocalDateTime;

public class RequestBean {
    private int id;
    private LocalDateTime requestDate;
    private String info;
    private String athlete;
    private String trainer;

    public RequestBean(int id, LocalDateTime requestDate, String info, String athlete, String trainer){
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

    public String getAthlete() {
        return athlete;
    }

    public String getTrainer() {
        return trainer;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
