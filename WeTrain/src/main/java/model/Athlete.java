package model;

import exception.ExpiredCardException;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public class Athlete extends User implements Serializable {
    private String cardNumber;
    private YearMonth cardExpirationDate;
    private WorkoutPlan workoutPlan;
    private List<Course> courseList;
    private Trainer trainer;

    public Athlete(String name, String surname, String username, LocalDate dateOfBirth, String fc, char gender, String email, String password) {
        super(name, surname, username, dateOfBirth, fc, gender, email, password);
    }

    public Athlete(String name, String surname, String username, LocalDate dateOfBirth, String fc, char gender, String email, String password, String cardNumber, YearMonth cardExpirationDate) throws ExpiredCardException {
        super(name, surname, username, dateOfBirth, fc, gender, email, password);
        this.cardNumber = cardNumber;
        this.setCardExpirationDate(cardExpirationDate);
    }

    public void changeCardInfo(String newCardNumber, YearMonth newCardExpirationDate){
        this.cardNumber = newCardNumber;
        this.cardExpirationDate = newCardExpirationDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public YearMonth getCardExpirationDate() {
        return cardExpirationDate;
    }

    public void setCardExpirationDate(YearMonth cardExpirationDate) throws ExpiredCardException {
        if(cardExpirationDate != null) {
            if ((cardExpirationDate.getYear() > LocalDate.now().getYear()) ||
                    ((cardExpirationDate.getYear() == LocalDate.now().getYear()) &&
                            (cardExpirationDate.getMonthValue() > LocalDate.now().getMonthValue()))) {
                this.cardExpirationDate = cardExpirationDate;
            } else {
                throw new ExpiredCardException();
            }
        }
    }

    public WorkoutPlan getWorkoutPlan() {
        return workoutPlan;
    }

    public void setWorkoutPlan(WorkoutPlan workoutPlan) {
        this.workoutPlan = workoutPlan;
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
