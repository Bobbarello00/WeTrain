package viewone.graphical_controllers.athletes;

import controller.CourseManagementAthleteController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Trainer;
import viewone.MainPane;
import viewone.bean.CourseEssentialBean;
import viewone.bean.UserBean;
import viewone.list_cell_factories.PersonListCellFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FindTrainerGUIController implements Initializable {

    @FXML private TextField trainerNameSearch;
    @FXML private Button searchButton;
    @FXML private ListView<UserBean> trainersList;

    @FXML void closeAction() {
        ((Stage) searchButton.getScene().getWindow()).close();
        MainPane.getInstance().setDisable(false);
    }

    @FXML void searchButtonAction(ActionEvent event) {

    }

    private void updateList(){
        //TODO Implementare controller e relativo metodo per restituzione lista dei trainer
        List<UserBean> userBeanList = new ArrayList<>();
        ObservableList<UserBean> trainersObservableList = FXCollections.observableList(userBeanList);
        trainersList.setItems(FXCollections.observableList(trainersObservableList));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        trainersList.setCellFactory(nodeListView -> new PersonListCellFactory());
    }
}
