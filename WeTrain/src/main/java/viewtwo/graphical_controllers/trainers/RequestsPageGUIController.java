package viewtwo.graphical_controllers.trainers;

import controller.SatisfyWorkoutRequestsController;
import engeneering.AlertGenerator;
import engeneering.manage_list.list_cell_factories.RequestListCellFactory;
import exception.DBUnreachableException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import viewone.bean.RequestBean;
import viewtwo.PageSwitchSimple;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class RequestsPageGUIController implements Initializable {

    @FXML private VBox requestActions;
    @FXML private ListView<RequestBean> requestList;

    private RequestBean selectedRequest;
    private final SatisfyWorkoutRequestsController satisfyWorkoutRequestsController = new SatisfyWorkoutRequestsController();

    public RequestsPageGUIController() throws DBUnreachableException, SQLException {}

    @FXML void backButtonAction() throws IOException {
        PageSwitchSimple.switchPage("TrainersHome", "trainers");
    }

    @FXML void rejectRequestAction() {
        try {
            satisfyWorkoutRequestsController.rejectRequest(selectedRequest);
            ObservableList<RequestBean> requestBeanObservableList = FXCollections.observableList(satisfyWorkoutRequestsController.getTrainerRequests());
            requestList.setItems(FXCollections.observableList(requestBeanObservableList));
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSimple.logOff();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML void satisfyRequestAction() throws IOException {
        CreateWorkoutPlanGUIController controller = (CreateWorkoutPlanGUIController)PageSwitchSimple.switchPage("CreateWorkoutPlan", "trainers");
        if(controller != null) {
            controller.setValue(selectedRequest, satisfyWorkoutRequestsController, 0);
        }
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            requestList.setCellFactory(nodeListView -> new RequestListCellFactory());
            ObservableList<RequestBean> requestBeanObservableList = FXCollections.observableList(satisfyWorkoutRequestsController.getTrainerRequests());
            requestList.setItems(FXCollections.observableList(requestBeanObservableList));
            requestList.getSelectionModel().selectedItemProperty().
                    addListener(new ChangeListener<>() {
                        @Override public void changed(ObservableValue<? extends RequestBean> observableValue, RequestBean oldItem, RequestBean newItem) {
                            if(newItem != null){
                                selectedRequest = newItem;
                                requestActions.setDisable(false);
                            }
                        }
                    });
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSimple.logOff();
        }
    }
}