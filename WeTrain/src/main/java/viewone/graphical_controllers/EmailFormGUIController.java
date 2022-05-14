package viewone.graphical_controllers;

import boundary.EmailSystemBoundary;
import controller.NotificationsController;
import exception.BrowsingNotSupportedException;
import exception.DBUnreachableException;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import viewone.bean.EmailBean;
import viewone.bean.EmailReceivedNotificationBean;
import viewone.bean.UserBean;
import viewone.engeneering.LoggedUserSingleton;

import java.io.IOException;
import java.net.URISyntaxException;
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
                    sender,
                    receiver,
                    objectTextField.getText(),
                    emailTextArea.getText()
            ));
            notificationsController.sendEmailReceivedNotification(new EmailReceivedNotificationBean(
                    sender,
                    receiver
            ));
        } catch (SQLException | URISyntaxException | IOException e) {
            e.printStackTrace();
        } catch (DBUnreachableException e) {
            e.alertAndLogOff();
        } catch (BrowsingNotSupportedException e) {
            e.alert();
        }
        close();
    }
}
