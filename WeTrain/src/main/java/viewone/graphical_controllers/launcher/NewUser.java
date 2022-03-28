package viewone.graphical_controllers.launcher;

import viewone.bean.AthleteBean;
import viewone.bean.TrainerBean;
import viewone.bean.UserBean;
//TODO
public class NewUser {
    private static UserBean user;

    private String birth;

    private NewUser() {}

    public static void setInstance(AthleteBean athlete){
        user = athlete;
    }

    public static void setInstance(TrainerBean trainer){
        user = trainer;
    }

    public static UserBean getInstance(){
        return user;
    }
}
