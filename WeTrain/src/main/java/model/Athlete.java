package model;

import java.time.LocalDate;
import java.util.List;

public class Athlete extends User {
    private String cardNumber;
    private LocalDate cardExpirationDate;
    private WorkoutPlan workoutPlan;
    private List<Course> courseList;

    public Athlete(String name, String surname, String username, LocalDate dateOfBirth, String fc, char gender, String email, String password){
        super(name, surname, username, dateOfBirth, fc, gender, email, password);
    }

    public Athlete(String name, String surname, String username, LocalDate dateOfBirth, String fc, char gender, String email, String password, String cardNumber, LocalDate cardExpirationDate){
        super(name, surname, username, dateOfBirth, fc, gender, email, password);
        this.cardNumber = cardNumber;
        this.cardExpirationDate = cardExpirationDate;
    }

    public void changeCardInfo(String newCardNumber, LocalDate newCardExpirationDate){
        this.cardNumber = newCardNumber;
        this.cardExpirationDate = newCardExpirationDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getCardExpirationDate() {
        return cardExpirationDate;
    }

    public void setCardExpirationDate(LocalDate cardExpirationDate) {
        this.cardExpirationDate = cardExpirationDate;
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
}
