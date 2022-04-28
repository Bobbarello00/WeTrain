package viewone.graphical_controllers.trainers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import viewone.ListPopulate;
import viewone.MainPane;
import viewone.PageSwitchSimple;
import viewone.PageSwitchSizeChange;
import viewone.bean.ExerciseBean;
import viewone.bean.RequestBean;
import viewone.list_cell_factories.ExerciseListCellFactory;
import viewone.list_cell_factories.RequestListCellFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WorkoutRequestsGUIController extends HomeGUIControllerTrainers implements Initializable {
    private static final String HOME = "trainers";
    private boolean once = true;

    @FXML private ListView<RequestBean> requestsList;
    @FXML private VBox requestInfoBox;
    @FXML private VBox emptyInfoBox;
    @FXML private Label requestInfoLabel;

    @FXML void newWorkoutButtonAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"NewWorkoutPlan",HOME);
    }

    @FXML void clarificationEmailButtonAction(ActionEvent event) throws IOException {
        PageSwitchSizeChange.pageSwitch((Button)event.getSource(),"EmailForm","",false);
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        requestsList.setCellFactory(nodeListView -> new RequestListCellFactory());
        ObservableList<RequestBean> requestBeanObservableList = null;
        //TODO fare controller e prendere richieste
        requestBeanObservableList = FXCollections.observableList(null);
        requestsList.setItems(FXCollections.observableList(requestBeanObservableList));
        requestsList.getSelectionModel().selectedItemProperty().
                addListener(new ChangeListener<>() {
                    @Override public void changed(ObservableValue<? extends RequestBean> observableValue, RequestBean oldItem, RequestBean newItem) {
                        requestInfoLabel.setText(newItem.getInfo());
                        activateInfoTab();
                    }
                });
        setUserInfoTab();
    }

    private void activateInfoTab() {
        if(once) {
            requestInfoBox.setDisable(false);
            requestInfoBox.setVisible(true);
            emptyInfoBox.setDisable(true);
            emptyInfoBox.setVisible(false);
            once = false;
        }
    }
}
