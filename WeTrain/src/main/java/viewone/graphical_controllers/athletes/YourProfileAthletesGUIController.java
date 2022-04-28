package viewone.graphical_controllers.athletes;

import controller.ProfileManagementController;
import exception.DBConnectionFailedException;
import exception.ExpiredCardException;
import exception.InvalidCardInfoException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import viewone.bean.AthleteBean;
import viewone.bean.CardInfoBean;
import viewone.engeneering.FatalCaseManager;
import viewone.engeneering.LoggedUserSingleton;
import viewone.graphical_controllers.ProfileGUIController;

import java.net.URL;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.util.Objects;
import java.util.ResourceBundle;

public class YourProfileAthletesGUIController extends ProfileGUIController implements Initializable {
    @FXML private Label paymentMethodLabel;
    @FXML private Button editButton;
    @FXML private Pane editPane;
    @FXML private TextField newCardNumber;
    @FXML private TextField newExpirationDate;

    private AthleteBean athlete;

    private final ProfileManagementController profileManagementController = new ProfileManagementController();

    @FXML private void editConfirmation() {
        if(!Objects.equals(newCardNumber.getText(), "")
                & !Objects.equals(newExpirationDate.getText(), "")) {
            try{
                CardInfoBean cardInfoBean = new CardInfoBean(
                        newCardNumber.getText(),
                        newExpirationDate.getText());
                profileManagementController.updateAthleteCardInfo(cardInfoBean);
                athlete = (AthleteBean) LoggedUserSingleton.getInstance();
                editPane.setDisable(true);
                editPane.setVisible(false);
                setPaymentMethodLabel();
                editButton.setDisable(false);
                editButton.setVisible(true);
                paymentMethodLabel.setVisible(true);
            } catch (SQLException e){
                e.printStackTrace();
                //TODO GESTIONE EXCEPTION
            } catch (ExpiredCardException | InvalidCardInfoException e) {
                e.alert();
            } catch (DBConnectionFailedException e) {
                ((Stage) editButton.getScene().getWindow()).close();
                e.alertAndLogOff();
            }
        }
    }

    @FXML private void editAbort(){
        editPane.setDisable(true);
        editPane.setVisible(false);
        editButton.setDisable(false);
        editButton.setVisible(true);
        paymentMethodLabel.setVisible(true);
    }

    @FXML private void editProfileButtonAction(){
        editButton.setDisable(true);
        editButton.setVisible(false);
        paymentMethodLabel.setVisible(false);
        editPane.setDisable(false);
        editPane.setVisible(true);
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        athlete = (AthleteBean) getLoggedUser();
        emailLabel.setText("Email: " + athlete.getEmail());
        firstNameLabel.setText(athlete.getName());
        lastNameLabel.setText(athlete.getSurname());
        fiscalCodeLabel.setText("FiscalCode: " + athlete.getFiscalCode());
        try {
            setPaymentMethodLabel();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (DBConnectionFailedException e) {
        e.alert();
    }
    }

    private void setPaymentMethodLabel() throws SQLException, DBConnectionFailedException {
        if (athlete.getCardNumber() == null && athlete.getCardExpirationDate() == null) {
            paymentMethodLabel.setText("Card: Not inserted yet");
        } else if(athlete.getCardNumber() == null || athlete.getCardExpirationDate() == null){
            FatalCaseManager.erasePaymentMethod();
        } else{
            String cardNumberTruncated = athlete.getCardNumber().substring(12, 16);
            paymentMethodLabel.setText("Card: " + athlete.getCardType() + "  **** **** **** " + cardNumberTruncated);
        }
    }
}
