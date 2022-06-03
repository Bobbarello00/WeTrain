package viewtwo.graphical_controllers;

import controllers.SatisfyWorkoutRequestsController;
import controllers.SendEmailController;
import engineering.AlertGenerator;
import engineering.LoggedUserSingleton;
import exceptions.BrowsingNotSupportedException;
import exceptions.DBUnreachableException;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import beans.TrainerBean;
import beans.UserBean;
import viewtwo.PageSwitchSimple;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

public class EmailFormGUIController {

    @FXML private TextArea emailBody;
    @FXML private TextField emailObject;

    private String filename;
    private String path;
    private UserBean receiver;

    public void setBackPathAndReceiver(String filename, String pathString, UserBean userBean) {
        this.filename = filename;
        path = pathString;
        receiver = userBean;
    }

    @FXML void backButtonAction() throws IOException {
        PageSwitchSimple.switchPage(filename, path);
    }

    @FXML void sendEmailButtonAction() {
        try {
            UserBean sender = LoggedUserSingleton.getInstance();
            if(sender instanceof TrainerBean) {
                new SatisfyWorkoutRequestsController().sendClarificationEmail(
                        sender,
                        receiver,
                        emailObject.getText(),
                        emailBody.getText()
                );
            } else {
                new SendEmailController().sendEmail(
                        sender,
                        receiver,
                        emailObject.getText(),
                        emailBody.getText()
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
            PageSwitchSimple.logOff();
        } catch (BrowsingNotSupportedException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
        }
    }

}
