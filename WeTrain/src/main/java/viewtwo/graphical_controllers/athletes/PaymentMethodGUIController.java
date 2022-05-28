package viewtwo.graphical_controllers.athletes;

import controller.ProfileManagementController;
import engeneering.AlertGenerator;
import viewone.LoggedUserSingleton;
import exception.DBUnreachableException;
import exception.invalid_data_exception.InvalidDataException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import viewone.bean.AthleteBean;
import viewone.bean.CardInfoBean;
import viewtwo.PageSwitchSimple;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentMethodGUIController implements Initializable {

    @FXML private Label cardNumberLabel;
    @FXML private Label cardExpirationDateLabel;
    @FXML private Button editButton;
    @FXML private Pane editPane;
    @FXML private TextField newCardNumberTextField;
    @FXML private TextField newExpirationDateTextField;

    private AthleteBean athlete;

    private final ProfileManagementController profileManagementController = new ProfileManagementController();

    @FXML void backButtonAction() {

    }

    @FXML void editCancelButtonAction() {

    }

    @FXML void editConfirmButtonAction() {
        try {
            CardInfoBean cardInfoBean = new CardInfoBean(
                    newCardNumberTextField.getText(),
                    newExpirationDateTextField.getText());
            profileManagementController.updateAthleteCardInfo(cardInfoBean);
             athlete = (AthleteBean) LoggedUserSingleton.getInstance();
            setLabel(athlete.getCardNumber() == null, cardNumberLabel, athlete.getCardNumber());
            setLabel(athlete.getCardExpirationDate() == null, cardExpirationDateLabel, athlete.getCardExpirationDate().toString());
            setVisiblePane(false);
        } catch (InvalidDataException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSimple.logOff();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void setLabel(boolean condition, Label cardNumberLabel, String text) {
        if(condition) {
            cardNumberLabel.setText("Not inserted");
        } else {
            cardNumberLabel.setText(text);
        }
    }

    @FXML void editCardNumberButtonAction() {
        setVisiblePane(true);
    }

    private void setVisiblePane(boolean bool) {
        editPane.setVisible(bool);
        editPane.setDisable(!bool);
        editButton.setDisable(bool);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        athlete = (AthleteBean) LoggedUserSingleton.getInstance();
        cardNumberLabel.setText(athlete.getCardNumber());
        cardExpirationDateLabel.setText(athlete.getCardExpirationDate().toString());
    }
}