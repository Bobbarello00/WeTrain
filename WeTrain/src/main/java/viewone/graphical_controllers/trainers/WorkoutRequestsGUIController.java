package viewone.graphical_controllers.trainers;

import controller.WorkoutPlanRequestController;
import exception.DBConnectionFailedException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import viewone.MainPane;
import viewone.PageSwitchSimple;
import viewone.PageSwitchSizeChange;
import viewone.bean.RequestBean;
import viewone.list_cell_factories.RequestListCellFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class WorkoutRequestsGUIController extends HomeGUIControllerTrainers implements Initializable {
    private static final String HOME = "trainers";
    private boolean once = true;
    private RequestBean requestSelected;

    @FXML private ListView<RequestBean> requestsList;
    @FXML private VBox requestInfoBox;
    @FXML private VBox emptyInfoBox;
    @FXML private Label requestInfoLabel;

    private final WorkoutPlanRequestController workoutPlanRequestController = new WorkoutPlanRequestController();

    @FXML void newWorkoutButtonAction() throws IOException {
        NewWorkoutPlanGUIController controller = (NewWorkoutPlanGUIController) PageSwitchSimple.switchPage(MainPane.getInstance(),"NewWorkoutPlan",HOME);
        Objects.requireNonNull(controller).setRequest(requestSelected);
    }

    @FXML void clarificationEmailButtonAction(ActionEvent event) throws IOException {
        PageSwitchSizeChange.pageSwitch((Button)event.getSource(),"EmailForm","",false);
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            requestsList.setCellFactory(nodeListView -> new RequestListCellFactory());
            ObservableList<RequestBean> requestBeanObservableList = FXCollections.observableList(workoutPlanRequestController.getTrainerRequests());
            requestsList.setItems(FXCollections.observableList(requestBeanObservableList));
            requestsList.getSelectionModel().selectedItemProperty().
                    addListener(new ChangeListener<>() {
                        @Override public void changed(ObservableValue<? extends RequestBean> observableValue, RequestBean oldItem, RequestBean newItem) {
                            requestInfoLabel.setText(newItem.getInfo());
                            requestSelected = newItem;
                            activateInfoTab();
                        }
                    });
            setUserInfoTab();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DBConnectionFailedException e) {
            e.alertAndLogOff();
        }
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
