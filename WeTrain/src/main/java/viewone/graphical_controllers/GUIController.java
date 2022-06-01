package viewone.graphical_controllers;

import engeneering.AlertGenerator;
import engeneering.LoggedUserSingleton;
import exceptions.DBUnreachableException;
import viewone.PageSwitchSizeChange;
import beans.UserBean;

import java.util.List;
import java.util.Objects;

public abstract class GUIController {

    protected UserBean getLoggedUser(){
        try {
            return Objects.requireNonNull(LoggedUserSingleton.getInstance());
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSizeChange.logOff();
            return null;
        }
    }
}
