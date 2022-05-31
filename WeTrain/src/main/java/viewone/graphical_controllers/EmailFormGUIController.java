package viewone.graphical_controllers;

import controllers.SatisfyWorkoutRequestsController;
import controllers.SendEmailController;
import exceptions.BrowsingNotSupportedException;
import exceptions.DBUnreachableException;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import viewone.PageSwitchSizeChange;
import viewone.beans.AthleteBean;
import viewone.beans.UserBean;
import engeneering.AlertGenerator;
import engeneering.LoggedUserSingleton;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

public class EmailFormGUIController extends  AbstractFormGUIController{
    @FXML private TextArea emailTextArea;
    @FXML private TextField objectTextField;

    private UserBean receiver;

    public void setReceiver(UserBean userBean) {
        receiver = userBean;
    }

    @Override protected void sendAction() {
        try {
            UserBean sender = LoggedUserSingleton.getInstance();
            if(sender instanceof AthleteBean) {
                new SendEmailController().sendEmail(
                        sender,
                        receiver,
                        objectTextField.getText(),
                        emailTextArea.getText()
                );
            } else {
                new SatisfyWorkoutRequestsController().sendClarificationEmail(
                        sender,
                        receiver,
                        objectTextField.getText(),
                        emailTextArea.getText()
                );
            }
        } catch (SQLException | URISyntaxException | IOException e) {
            e.printStackTrace();
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSizeChange.logOff();
        } catch (BrowsingNotSupportedException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
        }
        close();
    }
}