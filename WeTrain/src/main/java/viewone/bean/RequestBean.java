package viewone.bean;

import exception.invalid_data_exception.TextOutOfBoundException;
import exception.invalid_data_exception.EmptyFieldsException;

import java.time.LocalDateTime;

public class RequestBean {
    private int id;
    private final LocalDateTime requestDate;
    private String info;
    private final AthleteBean athleteBean;
    private final String trainerFc;

    public RequestBean(int id, LocalDateTime requestDate, String info, AthleteBean athleteBean, String trainer) {
        this.id = id;
        this.requestDate = requestDate;
        this.info = info;
        this.athleteBean = athleteBean;
        this.trainerFc = trainer;
    }

    public RequestBean(String info, AthleteBean athleteBean, String trainerFc) throws TextOutOfBoundException, EmptyFieldsException {
        this.requestDate = LocalDateTime.now();
        setInfo(info);
        this.athleteBean = athleteBean;
        this.trainerFc = trainerFc;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public AthleteBean getAthleteBean() {
        return athleteBean;
    }

    public String getTrainerFc() {
        return trainerFc;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) throws TextOutOfBoundException, EmptyFieldsException {
        if(info.isEmpty()) {
            throw new EmptyFieldsException();
        }
        if(info.length() > 450) {
            throw new TextOutOfBoundException();
        }
        this.info = info;
    }
}
