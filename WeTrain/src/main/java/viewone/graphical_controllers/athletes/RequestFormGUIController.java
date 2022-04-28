package viewone.graphical_controllers.athletes;

import exception.DBConnectionFailedException;
import exception.ExpiredCardException;
import exception.InvalidCardInfoException;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import viewone.MainPane;
import viewone.bean.RequestBean;
import viewone.bean.UserBean;
import viewone.engeneering.LoggedUserSingleton;
import viewone.engeneering.UserInfoCarrier;
import viewone.graphical_controllers.AbstractFormGUIController;

import java.sql.SQLException;

public class RequestFormGUIController extends AbstractFormGUIController {

    @FXML private TextArea requestInfoTextArea;
    private UserBean trainer;

    public void setTrainer(UserBean trainer) {
        this.trainer = trainer;
    }

    @Override protected void sendAction() {
        try {
            UserInfoCarrier userInfoCarrier = LoggedUserSingleton.getUserInfo();
            RequestBean requestBean = new RequestBean(requestInfoTextArea.getText(), userInfoCarrier.getFiscalCode(), userInfoCarrier.getUsername(), trainer.getFiscalCode());
        } catch (ExpiredCardException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InvalidCardInfoException e) {
            throw new RuntimeException(e);
        } catch (DBConnectionFailedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Request Sent!");
        //TODO CREAZIONE NOTIFICA E INVIO FITTIZIO DELLA RICHIESTA
        ((Stage) sendButton.getScene().getWindow()).close();
        MainPane.getInstance().setDisable(false);
    }


}

