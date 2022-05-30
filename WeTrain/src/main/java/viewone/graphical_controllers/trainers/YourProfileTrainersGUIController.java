package viewone.graphical_controllers.trainers;

import controllers.ProfileManagementController;
import database.dao_classes.TrainerDAO;
import exceptions.DBUnreachableException;
import exceptions.invalid_data_exception.InvalidIbanException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import viewone.WeTrain;
import viewone.beans.IbanBean;
import viewone.beans.TrainerBean;
import engeneering.AlertGenerator;
import engeneering.LoggedUserSingleton;
import viewone.graphical_controllers.ProfileGUIController;

import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class YourProfileTrainersGUIController extends ProfileGUIController implements Initializable {
    @FXML private TextField newIban;
    @FXML private Text subscribersText;

    TrainerBean trainer;

    private final ProfileManagementController profileManagementController = new ProfileManagementController();

    @Override protected void editAction() throws InvalidIbanException, DBUnreachableException, SQLException {
        IbanBean ibanBean = IbanBean.ctorWithSyntaxCheck(newIban.getText());
        profileManagementController.updateTrainerIban(ibanBean);
        trainer = (TrainerBean) LoggedUserSingleton.getInstance();
        setVisible(editPane, false);
        setIbanLabel();
        setVisible(editButton, true);
        paymentMethodLabel.setVisible(true);
    }

    private void setIbanLabel() {
        if(trainer.getIban() == null){
            paymentMethodLabel.setText("Iban: Not Inserted yet!");
        }else {
            paymentMethodLabel.setText("Iban: " + trainer.getIban());
        }
    }

    @FXML protected void editIbanButtonAction(){
        editButton.setDisable(true);
        editButton.setVisible(false);
        paymentMethodLabel.setVisible(false);
        editPane.setDisable(false);
        editPane.setVisible(true);
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        trainer = (TrainerBean) getLoggedUser();
        emailLabel.setText("Email: " + trainer.getEmail());
        firstNameLabel.setText(trainer.getName());
        lastNameLabel.setText(trainer.getSurname());
        fiscalCodeLabel.setText("FiscalCode: " + trainer.getFiscalCode());
        try{
            subscribersText.setText(String.valueOf(new TrainerDAO().getNumberOfSubscribers(trainer.getFiscalCode())));
            if(trainer.getGender() == 'm') {
                usrImage.setImage(new Image(Objects.requireNonNull(WeTrain.class.getResource("images/TrainerM.png")).toURI().toString()));
            }else{
                usrImage.setImage(new Image(Objects.requireNonNull(WeTrain.class.getResource("images/TrainerF.png")).toURI().toString()));
            }
            setIbanLabel();
        } catch (URISyntaxException | SQLException e) {
            e.printStackTrace();
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
        }

    }
}
