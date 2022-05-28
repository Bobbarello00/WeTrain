package viewone.graphical_controllers.athletes;

import controller.SubscribeToTrainerController;
import exception.DBUnreachableException;
import exception.PaymentFailedException;
import exception.invalid_data_exception.InvalidIbanException;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import viewone.PageSwitchSizeChange;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import viewone.WeTrain;
import viewone.bean.*;
import engeneering.AlertGenerator;
import viewone.graphical_controllers.EmailFormGUIController;
import engeneering.manage_list.list_cell_factories.PersonListCellFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class YourPersonalTrainerGUIController extends HomeGUIControllerAthletes implements Initializable {

    @FXML private Label trainerName;
    @FXML private ImageView trainerImage;
    @FXML private VBox trainerBox;
    @FXML private VBox addTrainerBox;
    @FXML private VBox searchTrainerBox;
    @FXML private TextField trainerNameSearch;
    @FXML private Button subscribeButton;
    @FXML private ListView<UserBean> trainersList;
    @FXML private Label infoName;
    @FXML private Label infoSurname;
    @FXML private Label infoUsername;
    @FXML private Label infoBirth;
    @FXML private Label infoEmail;
    @FXML private Label infoFiscalCode;
    @FXML private VBox infoTrainerBox;
    @FXML private VBox emptyInfoTrainerBox;

    private UserBean selectedTrainer;

    private final SubscribeToTrainerController subscribeToTrainerController = new SubscribeToTrainerController();

    private void showVBox(VBox vbox){
        vbox.setDisable(false);
        vbox.setVisible(true);
    }
    private void hideVBox(VBox vbox){
        vbox.setDisable(true);
        vbox.setVisible(false);
    }

    @FXML void workoutRequestButtonAction(ActionEvent event) throws IOException {
        RequestFormGUIController controller = (RequestFormGUIController)
                PageSwitchSizeChange.pageSwitch((Button) event.getSource(), "RequestForm", "athletes", false);
        controller.setTrainer(selectedTrainer);
    }

    @FXML void unsubscribeButtonAction() {
        setSelectedTrainer(null);
        try {
            subscribeToTrainerController.unsubscribeFromTrainer();
            subscribeButton.setDisable(false);
            subscribeButton.setVisible(true);
            hideVBox(trainerBox);
            hideVBox(infoTrainerBox);
            showVBox(emptyInfoTrainerBox);
            showVBox(addTrainerBox);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSizeChange.logOff();
        }
    }

    @FXML void addTrainerAction() {
        ObservableList<UserBean> trainersObservableList;
        try {
            trainersObservableList = FXCollections.observableList(subscribeToTrainerController.getTrainersList());
            trainersList.setItems(FXCollections.observableList(trainersObservableList));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSizeChange.logOff();
            return;
        }
        hideVBox(addTrainerBox);
        showVBox(searchTrainerBox);
    }

    @FXML void searchButtonAction() throws SQLException {
        List<UserBean> userBeanList;
        try {
            userBeanList = subscribeToTrainerController.searchTrainers(new SearchBean(trainerNameSearch.getText()));
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSizeChange.logOff();
            return;
        }
        ObservableList<UserBean> trainersObservableList = FXCollections.observableList(userBeanList);
        trainersList.setItems(FXCollections.observableList(trainersObservableList));
    }

    @FXML void subscribeButtonAction() {
        try {
            if(AlertGenerator.newConfirmationAlert("PURCHASE CONFIRMATION",
                    "Trainer subscription fee is 5$",
                    "if you click ok a payment will be sent from your selected payment method")) {
                subscribeToTrainerController.subscribeToTrainer(selectedTrainer.getFiscalCode());
                updateTrainerBox();
                subscribeButton.setDisable(true);
                subscribeButton.setVisible(false);
                hideVBox(searchTrainerBox);
                showVBox(trainerBox);
            }
        } catch (SQLException | URISyntaxException e) {
            e.printStackTrace();
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSizeChange.logOff();
        } catch (PaymentFailedException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
        }

    }

    private void updateTrainerBox() throws URISyntaxException {
        if(selectedTrainer.getGender() == 'm') {
            trainerImage.setImage(new Image(Objects.requireNonNull(WeTrain.class.getResource("images/TrainerM.png")).toURI().toString()));
        }else{
            trainerImage.setImage(new Image(Objects.requireNonNull(WeTrain.class.getResource("images/TrainerF.png")).toURI().toString()));
        }
        trainerName.setText(selectedTrainer.getName() + " " + selectedTrainer.getSurname());
    }

    @FXML void writeEmailButtonAction(ActionEvent event) throws IOException {
        EmailFormGUIController emailFormGUIController = (EmailFormGUIController) PageSwitchSizeChange.pageSwitch((Button) event.getSource(), "EmailForm", "", false);
        emailFormGUIController.setReceiver(selectedTrainer);
    }


    private void listEvent(ListView<UserBean> listView, UserBean newItem, SubscribeToTrainerController subscribeToTrainerController) throws IOException {
        try {
            if(newItem != null) {
                setSelectedTrainer(subscribeToTrainerController.getTrainerUser(new FcBean(newItem.getFiscalCode())));
                updateInfoTrainerBox();
                Platform.runLater(() -> listView.getSelectionModel().clearSelection());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSizeChange.logOff();
        }
    }

    private void updateInfoTrainerBox() {
        infoName.setText("Name: " + selectedTrainer.getName());
        infoSurname.setText("Surname: " + selectedTrainer.getSurname());
        infoUsername.setText("Username: " + selectedTrainer.getUsername());
        infoBirth.setText("Birth: " + selectedTrainer.getBirth());
        infoEmail.setText("Email: " + selectedTrainer.getEmail());
        infoFiscalCode.setText("FiscalCode: " + selectedTrainer.getFiscalCode());
        hideVBox(emptyInfoTrainerBox);
        showVBox(infoTrainerBox);
    }

    private void setTrainer() throws IOException {
        try {
            TrainerBean trainerBean = subscribeToTrainerController.getTrainer();
            if(trainerBean != null){
                setSelectedTrainer(trainerBean);
                updateInfoTrainerBox();
                updateTrainerBox();
                subscribeButton.setDisable(true);
                subscribeButton.setVisible(false);
                hideVBox(addTrainerBox);
                showVBox(trainerBox);
            }else {
                setSelectedTrainer(null);
            }
        } catch (SQLException | URISyntaxException e) {
            e.printStackTrace();
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSizeChange.logOff();
        } catch (InvalidIbanException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
        }
    }

    public void setSelectedTrainer(UserBean selectedTrainer) {
        this.selectedTrainer = selectedTrainer;
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        trainersList.setCellFactory(nodeListView -> new PersonListCellFactory());
        trainersList.getSelectionModel().selectedItemProperty().
                addListener(new ChangeListener<>() {
                    @Override public void changed(ObservableValue<? extends UserBean> observableValue, UserBean oldItem, UserBean newItem) {
                        try {
                            listEvent(trainersList, newItem, subscribeToTrainerController);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
        setUserInfoTab();
        try {
            setTrainer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
