package viewone.graphical_controllers;

import boundary.EmailSystemBoundary;
import controller.NotificationsController;
import exception.DBUnreachableException;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import viewone.bean.EmailBean;
import viewone.bean.EmailReceivedNotificationBean;
import viewone.bean.UserBean;
import viewone.engeneering.LoggedUserSingleton;

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
        } catch (DBUnreachableException e) {
            e.alertAndLogOff();
        }
        close();
    }
}
