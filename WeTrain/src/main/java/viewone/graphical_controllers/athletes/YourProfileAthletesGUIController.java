package viewone.graphical_controllers.athletes;

import controller.ProfileManagementController;
import database.dao_classes.AthleteDAO;
import exception.DBUnreachableException;
import exception.invalid_data_exception.EmptyFieldsException;
import exception.invalid_data_exception.ExpiredCardException;
import exception.invalid_data_exception.InvalidCardInfoException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import viewone.WeTrain;
import viewone.bean.AthleteBean;
import viewone.bean.CardInfoBean;
import viewone.engeneering.AlertFactory;
import viewone.engeneering.LoggedUserSingleton;
import viewone.graphical_controllers.ProfileGUIController;

import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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

   @Override protected void editAction() throws InvalidCardInfoException, EmptyFieldsException, DBUnreachableException, ExpiredCardException, SQLException {
       CardInfoBean cardInfoBean = new CardInfoBean(
               newCardNumber.getText(),
               newExpirationDate.getText());
       profileManagementController.updateAthleteCardInfo(cardInfoBean);
       athlete = (AthleteBean) LoggedUserSingleton.getInstance();
       setVisible(editPane, false);
       setPaymentMethodLabel();
       setVisible(editButton, true);
       paymentMethodLabel.setVisible(true);
   }

    private void setPaymentMethodLabel() throws SQLException, DBUnreachableException {
        if (athlete.getCardNumber() == null && athlete.getCardExpirationDate() == null) {
            paymentMethodLabel.setText("Card: Not inserted yet!");
            cardLogo.setVisible(false);
        } else {
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
            List<String> errorStrings = e.getErrorStrings();
            AlertFactory.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
        }
    }
}
