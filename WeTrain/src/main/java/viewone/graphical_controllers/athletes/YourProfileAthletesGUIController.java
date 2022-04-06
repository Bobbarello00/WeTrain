package viewone.graphical_controllers.athletes;

import controller.ProfileManagementController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.Athlete;
import model.LoggedUserSingleton;
import viewone.bean.CardInfoBean;
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
    @FXML private DatePicker newExpirationDate;

    @FXML private void editConfirmation(){
        if(!Objects.equals(newCardNumber.getText(), "")
                & !Objects.equals(newExpirationDate.getEditor().getText(), "")) {
            CardInfoBean cardInfoBean = new CardInfoBean();
            if(!cardInfoBean.setCardNumber(newCardNumber.getText())
                    || !cardInfoBean.setExpirationDate(newExpirationDate.getEditor().getText())){
                System.out.printf("""
                        errore nel set dei nuovi valori per la carta:
                         numero: '%s'\s
                         data: '%s'%n""", newCardNumber.getText(), newExpirationDate.getEditor().getText());
                return;
            }
            try{
                ProfileManagementController.updateAthleteCardInfo(cardInfoBean);
            }catch (SQLException e){
                e.printStackTrace();
                //TODO GESTIONE EXCEPTION
            }
            editPane.setDisable(true);
            editPane.setVisible(false);
            setPaymentMethodLabel();
            editButton.setDisable(false);
            editButton.setVisible(true);
            paymentMethodLabel.setVisible(true);
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
        emailLabel.setText("Email: " + LoggedUserSingleton.getInstance().getEmail());
        firstNameLabel.setText(LoggedUserSingleton.getInstance().getName());
        lastNameLabel.setText(LoggedUserSingleton.getInstance().getSurname());
        fiscalCodeLabel.setText("FiscalCode: " + LoggedUserSingleton.getInstance().getFiscalCode());
        setPaymentMethodLabel();
    }

    private void setPaymentMethodLabel(){
        if(((Athlete)LoggedUserSingleton.getInstance()).getCardNumber() == null){
            paymentMethodLabel.setText("Card: " + "Not inserted yet");
        } else {
            paymentMethodLabel.setText("Card: " + ((Athlete) LoggedUserSingleton.getInstance()).getCardNumber());
        }
    }
}
