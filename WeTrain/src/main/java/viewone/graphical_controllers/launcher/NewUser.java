package viewone.graphical_controllers.launcher;

import model.Athlete;
import model.Trainer;
import model.User;

public class NewUser {
    private static User user;

    private String birth;

    private NewUser() {}

    public static void setInstance(Athlete athlete){
        user = athlete;
    }

    public static void setInstance(Trainer trainer){
        user = trainer;
    }

    public static User getInstance(){
        return user;
    }
}
