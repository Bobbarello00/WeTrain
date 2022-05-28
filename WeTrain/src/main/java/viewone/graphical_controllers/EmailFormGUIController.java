package viewone.graphical_controllers;

import controller.SatisfyWorkoutRequestsController;
import exception.BrowsingNotSupportedException;
import exception.DBUnreachableException;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import viewone.PageSwitchSizeChange;
import viewone.bean.UserBean;
import engeneering.AlertGenerator;
import viewone.LoggedUserSingleton;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

public class EmailFormGUIController extends  AbstractFormGUIController{
    @FXML private TextArea emailTextArea;
    @FXML private TextField objectTextField;

    private UserBean receiver;
    private final SatisfyWorkoutRequestsController satisfyWorkoutRequestsController = new SatisfyWorkoutRequestsController();

    public EmailFormGUIController() throws DBUnreachableException, SQLException {}

    public void setReceiver(UserBean userBean) {
        receiver = userBean;
    }

    @Override protected void sendAction() {
        try {
            UserBean sender = LoggedUserSingleton.getInstance();
            satisfyWorkoutRequestsController.sendClarificationEmail(
                    sender,
                    receiver,
                    objectTextField.getText(),
                    emailTextArea.getText());
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