package viewone.graphical_controllers;

import controller.NotificationsController;
import exception.DBConnectionFailedException;
import exception.invalidDataException.InvalidIbanException;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import viewone.MainPane;
import viewone.bean.EmailBean;
import viewone.bean.UserBean;
import viewone.engeneering.LoggedUserSingleton;
import viewone.engeneering.NotificationFactorySingleton;

import java.sql.SQLException;

public class EmailFormGUIController extends  AbstractFormGUIController{
    @FXML private TextArea emailTextArea;
    @FXML private TextField objectTextField;

    private
    private final NotificationsController notificationsController = new NotificationsController();

    public void setReceiver(UserBean userBean) {

    }
    
    @Override protected void sendAction() {
        //TODO CREAZIONE NOTIFICA E INVIO FITTIZIO DELL'EMAIL
        try {
            notificationsController.sendEmailReceivedNotification(new EmailBean(
                    LoggedUserSingleton.getInstance(),

            ));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DBConnectionFailedException e) {
            throw new RuntimeException(e);
        } catch (InvalidIbanException e) {
            throw new RuntimeException(e);
        }
        close();
    }
}
