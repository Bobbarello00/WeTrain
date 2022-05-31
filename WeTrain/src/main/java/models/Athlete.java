package models;

import exceptions.invalid_data_exception.ExpiredCardException;
import exceptions.invalid_data_exception.InvalidCardInfoException;
import exceptions.invalid_data_exception.NoCardInsertedException;
import models.record.Card;
import models.record.Credentials;
import models.record.PersonalInfo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public class Athlete extends User implements Serializable {
    private Card card;
    private WorkoutPlan workoutPlan;
    private List<Course> courseList;
    private Trainer trainer;

    public Athlete(String username, PersonalInfo personalInfo, Credentials credentials) {
        super(username, personalInfo, credentials);
    }

    public Athlete(String username, PersonalInfo personalInfo, Credentials credentials, Card card) throws ExpiredCardException {
        super(username, personalInfo, credentials);
        checkCardExpirationDate(card.cardExpirationDate());
        this.card = card;
    }

    public String getCardNumber() throws NoCardInsertedException {
        if(card!=null) {
            return card.cardNumber();
        }else{
            throw new NoCardInsertedException();
        }
    }

    public void setCard(Card card) throws ExpiredCardException {
        checkCardExpirationDate(card.cardExpirationDate());
        this.card = card;
    }

    public YearMonth getCardExpirationDate() {
        return card.cardExpirationDate();
    }

    public void checkCardExpirationDate(YearMonth cardExpirationDate) throws ExpiredCardException {
        if ((cardExpirationDate != null) && !((cardExpirationDate.getYear() > LocalDate.now().getYear()) ||
                ((cardExpirationDate.getYear() == LocalDate.now().getYear()) &&
                        (cardExpirationDate.getMonthValue() > LocalDate.now().getMonthValue())))) {
            throw new ExpiredCardException();
        }
    }

    public WorkoutPlan getWorkoutPlan() {
        return workoutPlan;
    }

    public void setWorkoutPlan(WorkoutPlan workoutPlan) {
        if(workoutPlan != null) {
            this.workoutPlan = new WorkoutPlan();
            this.workoutPlan.addAllWorkoutDays(workoutPlan.getWorkoutDayList());
        } else {
            this.workoutPlan = null;
        }
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }
}
