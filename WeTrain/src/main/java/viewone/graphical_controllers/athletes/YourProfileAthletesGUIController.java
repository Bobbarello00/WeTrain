package viewone.graphical_controllers.athletes;

import controller.ProfileManagementController;
import database.dao_classes.AthleteDAO;
import exception.DBUnreachableException;
import exception.invalidDataException.InvalidDataException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import viewone.WeTrain;
import viewone.bean.AthleteBean;
import viewone.bean.CardInfoBean;
import viewone.engeneering.FatalCaseManager;
import viewone.engeneering.LoggedUserSingleton;
import viewone.graphical_controllers.ProfileGUIController;

import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class YourProfileAthletesGUIController extends ProfileGUIController implements Initializable {

    @FXML private TextField newCardNumber;
    @FXML private TextField newExpirationDate;
    @FXML private Text numberOfCoursesText;
    @FXML private ImageView cardLogo;

    private AthleteBean athlete;

    private final ProfileManagementController profileManagementController = new ProfileManagementController();

    @FXML private void editCardButtonAction(){
        cardLogo.setVisible(false);
        editPaymentMethodButtonAction();
    }

    @FXML private void editConfirmation() {
        try{
            CardInfoBean cardInfoBean = new CardInfoBean(
                    newCardNumber.getText(),
                    newExpirationDate.getText());
            profileManagementController.updateAthleteCardInfo(cardInfoBean);
            athlete = (AthleteBean) LoggedUserSingleton.getInstance();
            setVisible(editPane, false);
            setPaymentMethodLabel();
            setVisible(editButton, true);
            paymentMethodLabel.setVisible(true);
        } catch (SQLException e){
           throw new RuntimeException();
        } catch (InvalidDataException e) {
            e.alert();
        } catch (DBUnreachableException e) {
            ((Stage) editButton.getScene().getWindow()).close();
            e.alertAndLogOff();
        }
    }

    private void setPaymentMethodLabel() throws SQLException, DBUnreachableException {
        if (athlete.getCardNumber() == null && athlete.getCardExpirationDate() == null) {
            paymentMethodLabel.setText("Card: Not inserted yet!");
            cardLogo.setVisible(false);
        } else if(athlete.getCardNumber() == null || athlete.getCardExpirationDate() == null){
            FatalCaseManager.erasePaymentMethod();
        } else{
            String truncatedCardNumber = athlete.getCardNumber().substring(12, 16);
            try {
                if(Objects.equals(athlete.getCardType(), "VISA")){
                    cardLogo.setImage(new Image(Objects.requireNonNull(WeTrain.class.getResource("images/Visa.png")).toURI().toString()));
                }else{
                    cardLogo.setImage(new Image(Objects.requireNonNull(WeTrain.class.getResource("images/MasterCard.png")).toURI().toString()));
                }
            }catch (URISyntaxException e) {
                e.printStackTrace();
            }
            cardLogo.setVisible(true);
            paymentMethodLabel.setText("Card: \t\t" + "  **** **** **** " + truncatedCardNumber);
        }
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            athlete = (AthleteBean) getLoggedUser();
            if(athlete != null) {
                emailLabel.setText("Email: " + athlete.getEmail());
                firstNameLabel.setText(athlete.getName());
                lastNameLabel.setText(athlete.getSurname());
                fiscalCodeLabel.setText("FiscalCode: " + athlete.getFiscalCode());
                numberOfCoursesText.setText(String.valueOf(new AthleteDAO().getNumberOfCourses(athlete.getFiscalCode())));
                if (athlete.getGender() == 'm') {
                    usrImage.setImage(new Image(Objects.requireNonNull(WeTrain.class.getResource("images/AthleteM.png")).toURI().toString()));
                } else {
                    usrImage.setImage(new Image(Objects.requireNonNull(WeTrain.class.getResource("images/AthleteF.png")).toURI().toString()));
                }
                setPaymentMethodLabel();
            }
        } catch (SQLException | URISyntaxException e) {
            e.printStackTrace();
        } catch (DBUnreachableException e) {
            e.alert();
        }
    }
}
