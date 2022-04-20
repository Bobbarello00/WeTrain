package viewone.graphical_controllers.athletes;

import controller.SubscriptionToTrainerController;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import viewone.PageSwitchSizeChange;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import viewone.bean.FcBean;
import viewone.bean.IdBean;
import viewone.bean.TrainerSearchBean;
import viewone.bean.UserBean;
import viewone.list_cell_factories.PersonListCellFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class YourPersonalTrainerGUIController extends HomeGUIControllerAthletes implements Initializable {

    @FXML private Label trainerName;
    @FXML private VBox trainerBox;
    @FXML private VBox addTrainerBox;
    @FXML private VBox searchTrainerBox;
    @FXML private Button logoutButton;
    @FXML private TextField trainerNameSearch;
    @FXML private Button searchButton;
    @FXML private ListView<UserBean> trainersList;
    @FXML private Label infoName;
    @FXML private Label infoBirth;
    @FXML private Label infoSurname;
    @FXML private Label infoUsername;
    @FXML private VBox infoTrainerBox;
    @FXML private VBox emptyInfoTrainerBox;

    private UserBean selectedTrainer = new UserBean();

    private final SubscriptionToTrainerController subscriptionToTrainerController = SubscriptionToTrainerController.getInstance();

    private void showVBox(VBox vbox){
        vbox.setDisable(false);
        vbox.setVisible(true);
    }
    private void hideVBox(VBox vbox){
        vbox.setDisable(true);
        vbox.setVisible(false);
    }

    @FXML void workoutRequestButtonAction(ActionEvent event) throws IOException {
        PageSwitchSizeChange.pageSwitch((Button) event.getSource(), "RequestForm", "", false);
    }

    @FXML void unsubscribeButtonAction() {
        setSelectedTrainer(null);
        hideVBox(trainerBox);
        showVBox(addTrainerBox);
    }

    @FXML void addTrainerAction() {
        hideVBox(addTrainerBox);
        showVBox(searchTrainerBox);
    }

    @FXML void searchButtonAction() throws SQLException {
        List<UserBean> userBeanList = subscriptionToTrainerController.searchTrainers(new TrainerSearchBean(trainerNameSearch.getText()));
        ObservableList<UserBean> trainersObservableList = FXCollections.observableList(userBeanList);
        trainersList.setItems(FXCollections.observableList(trainersObservableList));
    }

    @FXML void subscribeButtonAction() {
        //TODO EFFETTUARE ISCRIZIONE A TRAINER
        trainerName.setText(selectedTrainer.getName());
        hideVBox(infoTrainerBox);
        showVBox(emptyInfoTrainerBox);
        showVBox(trainerBox);
    }

    @FXML void writeEmailButtonAction(ActionEvent event) throws IOException {
        PageSwitchSizeChange.pageSwitch((Button) event.getSource(), "EmailForm", "", false);
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        trainersList.setCellFactory(nodeListView -> new PersonListCellFactory());
        ObservableList<UserBean> trainersObservableList = null;
        try {
            trainersObservableList = FXCollections.observableList(subscriptionToTrainerController.getTrainersList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        trainersList.setItems(FXCollections.observableList(trainersObservableList));
        trainersList.getSelectionModel().selectedItemProperty().
                addListener(new ChangeListener<>() {
                    @Override public void changed(ObservableValue<? extends UserBean> observableValue, UserBean oldItem, UserBean newItem) {
                        listEvent(trainersList, newItem, subscriptionToTrainerController);
                    }
                });
        setUsername();
    }

    private void listEvent(ListView<UserBean> listView, UserBean newItem, SubscriptionToTrainerController subscriptionToTrainerController) {
        try {
            if(newItem != null) {
                setSelectedTrainer(subscriptionToTrainerController.getTrainerUser(new FcBean(newItem.getFiscalCode())));
                updateInfoTrainerBox();
                Platform.runLater(() -> listView.getSelectionModel().clearSelection());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateInfoTrainerBox() {
        hideVBox(emptyInfoTrainerBox);
        showVBox(infoTrainerBox);
        infoUsername.setText(selectedTrainer.getUsername());
        infoName.setText(selectedTrainer.getName());
        infoSurname.setText(selectedTrainer.getSurname());
        infoBirth.setText(String.valueOf(selectedTrainer.getBirth()));
    }

    public void setSelectedTrainer(UserBean selectedTrainer) {
        this.selectedTrainer = selectedTrainer;
    }
}
