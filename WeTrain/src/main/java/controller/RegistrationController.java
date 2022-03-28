package controller;

import model.Athlete;
import model.Trainer;
import model.User;

import java.util.Objects;
//TODO
public class RegistrationController {
    private User user;

    public void setUser(ProfileBean profile) {
        if(Objects.equals(profile, "Athlete")) {
            user = new Athlete();
        } else {
            user = new Trainer();
        }
    }

    public

}
