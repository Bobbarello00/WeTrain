package viewone.graphical_controllers.athletes;

import controller.ProfileManagementController;
import exception.ExpiredCardException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import viewone.LoggedUserSingleton;
import viewone.bean.AthleteBean;
import viewone.bean.CardInfoBean;
import viewone.bean.UserBean;
import viewone.graphical_controllers.ProfileGUIController;

import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class YourProfileAthletesGUIController extends ProfileGUIController implements Initializable {
    @FXML private Label emailLabel;
    @FXML private Label firstNameLabel;
    @FXML private Label fiscalCodeLabel;
    @FXML private Label lastNameLabel;
    @FXML private Label paymentMethodLabel;
    @FXML private Button editButton;
    @FXML private Pane editPane;
    @FXML private TextField newCardNumber;
    @FXML private TextField newExpirationDate;

    @FXML private void editConfirmation(){
        if(!Objects.equals(newCardNumber.getText(), "")
                & !Objects.equals(newExpirationDate.getText(), "")) {
            CardInfoBean cardInfoBean = new CardInfoBean();
            if(!cardInfoBean.setCardNumber(newCardNumber.getText())
                    || !cardInfoBean.setExpirationDate(newExpirationDate.getText())){
                System.out.printf("""
                        errore nel set dei nuovi valori per la carta:
                         numero: '%s'\s
                         data: '%s'%n""", newCardNumber.getText(), newExpirationDate.getText());
                return;
            }
            try{
                ProfileManagementController.updateAthleteCardInfo(cardInfoBean);
                editPane.setDisable(true);
                editPane.setVisible(false);
                setPaymentMethodLabel();
                editButton.setDisable(false);
                editButton.setVisible(true);
                paymentMethodLabel.setVisible(true);
            }catch (SQLException e){
                e.printStackTrace();
                //TODO GESTIONE EXCEPTION
            } catch (ExpiredCardException e) {
                e.alert();
                e.printStackTrace();
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
        try {
            UserBean usr = LoggedUserSingleton.getInstance();
            emailLabel.setText("Email: " + usr.getEmail());
            firstNameLabel.setText(usr.getName());
            lastNameLabel.setText(usr.getSurname());
            fiscalCodeLabel.setText("FiscalCode: " + usr.getFiscalCode());
            setPaymentMethodLabel();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ExpiredCardException e) {
            e.printStackTrace();
        }
    }

    private void setPaymentMethodLabel() {
        try {
            AthleteBean athlete = (AthleteBean) LoggedUserSingleton.getInstance();
            if (athlete.getCardNumber() == null) {
                paymentMethodLabel.setText("Card: " + "Not inserted yet");
            } else {
                String cardNumberTruncated = athlete.getCardNumber().substring(12, 16);
                paymentMethodLabel.setText("Card: " + athlete.getCardType() + "  **** **** **** " + cardNumberTruncated);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ExpiredCardException e) {
            e.printStackTrace();
        }
    }
}
