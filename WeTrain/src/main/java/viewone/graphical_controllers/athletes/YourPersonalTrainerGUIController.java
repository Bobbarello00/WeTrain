package viewone.graphical_controllers.athletes;

import controller.SubscriptionToTrainerController;
import exception.DBUnreachableException;
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
import viewone.graphical_controllers.EmailFormGUIController;
import viewone.list_cell_factories.PersonListCellFactory;

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
    @FXML private Button logoutButton;
    @FXML private TextField trainerNameSearch;
    @FXML private Button searchButton;
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

    private final SubscriptionToTrainerController subscriptionToTrainerController = new SubscriptionToTrainerController();

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
            subscriptionToTrainerController.unsubscribeFromTrainer();
            subscribeButton.setDisable(false);
            subscribeButton.setVisible(true);
            hideVBox(trainerBox);
            hideVBox(infoTrainerBox);
            showVBox(emptyInfoTrainerBox);
            showVBox(addTrainerBox);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DBUnreachableException e) {
            e.alertAndLogOff();
        }
    }

    @FXML void addTrainerAction() {
        ObservableList<UserBean> trainersObservableList;
        try {
            trainersObservableList = FXCollections.observableList(subscriptionToTrainerController.getTrainersList());
            trainersList.setItems(FXCollections.observableList(trainersObservableList));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DBUnreachableException e) {
            e.alertAndLogOff();
            return;
        }
        hideVBox(addTrainerBox);
        showVBox(searchTrainerBox);
    }

    @FXML void searchButtonAction() throws SQLException {
        List<UserBean> userBeanList;
        try {
            userBeanList = subscriptionToTrainerController.searchTrainers(new TrainerSearchBean(trainerNameSearch.getText()));
        } catch (DBUnreachableException e) {
            e.alertAndLogOff();
            return;
        }
        ObservableList<UserBean> trainersObservableList = FXCollections.observableList(userBeanList);
        trainersList.setItems(FXCollections.observableList(trainersObservableList));
    }

    @FXML void subscribeButtonAction() {
        try {
            subscriptionToTrainerController.subscribeToTrainer(selectedTrainer.getFiscalCode());
            updateTrainerBox();
            subscribeButton.setDisable(true);
            subscribeButton.setVisible(false);
            hideVBox(searchTrainerBox);
            showVBox(trainerBox);
        } catch (SQLException | URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (DBUnreachableException e) {
            e.alertAndLogOff();
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


    private void listEvent(ListView<UserBean> listView, UserBean newItem, SubscriptionToTrainerController subscriptionToTrainerController) throws IOException {
        try {
            if(newItem != null) {
                setSelectedTrainer(subscriptionToTrainerController.getTrainerUser(new FcBean(newItem.getFiscalCode())));
                updateInfoTrainerBox();
                Platform.runLater(() -> listView.getSelectionModel().clearSelection());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DBUnreachableException e) {
            e.alertAndLogOff();
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
            TrainerBean trainerBean = subscriptionToTrainerController.getTrainer();
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
            throw new RuntimeException(e);
        } catch (DBUnreachableException e) {
            e.alertAndLogOff();
        } catch (InvalidIbanException e) {
            e.alert();
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
                            listEvent(trainersList, newItem, subscriptionToTrainerController);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
        setUserInfoTab();
        try {
            setTrainer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
