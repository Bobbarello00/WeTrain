package viewone.graphical_controllers;

import boundary.EmailSystemBoundary;
import controller.NotificationsController;
import exception.DBConnectionFailedException;
import exception.invalidDataException.InvalidIbanException;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import viewone.MainPane;
import viewone.bean.EmailBean;
import viewone.bean.EmailReceivedNotificationBean;
import viewone.bean.NotificationBean;
import viewone.bean.UserBean;
import viewone.engeneering.LoggedUserSingleton;
import viewone.engeneering.NotificationFactorySingleton;

import java.sql.SQLException;

public class EmailFormGUIController extends  AbstractFormGUIController{
    @FXML private TextArea emailTextArea;
    @FXML private TextField objectTextField;

    private UserBean receiver;
    private final EmailSystemBoundary emailSystemBoundary = new EmailSystemBoundary();
    private final NotificationsController notificationsController = new NotificationsController();

    public void setReceiver(UserBean userBean) {
        receiver = userBean;
    }

    @Override protected void sendAction() {
        try {
            UserBean sender = LoggedUserSingleton.getInstance();
            emailSystemBoundary.sendEmail(new EmailBean(
                    LoggedUserSingleton.getInstance(),
                    receiver,
                    objectTextField.getText(),
                    emailTextArea.getText()
            ));
            notificationsController.sendEmailReceivedNotification(new EmailReceivedNotificationBean(
                    sender,
                    receiver
            ));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DBConnectionFailedException e) {
            e.alertAndLogOff();
        }
        close();
    }
}
