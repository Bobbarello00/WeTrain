package viewone.graphical_controllers.athletes;

import controller.SubscriptionToTrainerController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import viewone.MainPane;
import viewone.bean.TrainerBean;
import viewone.bean.UserBean;
import viewone.list_cell_factories.PersonListCellFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FindTrainerGUIController implements Initializable {

    @FXML private TextField trainerNameSearch;
    @FXML private Button searchButton;
    @FXML private ListView<UserBean> trainersList;

    private final SubscriptionToTrainerController subscriptionToTrainerController = SubscriptionToTrainerController.getInstance();

    @FXML void closeAction() {
        ((Stage) searchButton.getScene().getWindow()).close();
        MainPane.getInstance().setDisable(false);
    }

    @FXML void searchButtonAction(ActionEvent event) {
        //TODO implementare ricerca Trainers per nome
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        trainersList.setCellFactory(nodeListView -> new PersonListCellFactory());
        ObservableList<UserBean> trainersObservableList = null;
        try {
            trainersObservableList = FXCollections.observableList(subscriptionToTrainerController.getTrainersList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        trainersList.setItems(FXCollections.observableList(trainersObservableList));
    }
}
