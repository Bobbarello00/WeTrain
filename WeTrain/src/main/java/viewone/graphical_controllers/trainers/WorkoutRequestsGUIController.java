package viewone.graphical_controllers.trainers;

import controller.SatisfyWorkoutRequestsController;
import exception.DBUnreachableException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import viewone.engeneering.AlertGenerator;
import viewone.engeneering.manage_list.ManageRequestList;
import viewone.graphical_controllers.EmailFormGUIController;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class WorkoutRequestsGUIController extends HomeGUIControllerTrainers implements Initializable {
    private static final String HOME = "trainers";
    private boolean once = true;
    private RequestBean selectedRequest;

    @FXML private ListView<RequestBean> requestList;
    @FXML private VBox requestInfoBox;
    @FXML private VBox emptyInfoBox;
    @FXML private Label requestInfoLabel;

    private final SatisfyWorkoutRequestsController satisfyWorkoutRequestsController = new SatisfyWorkoutRequestsController();

    public WorkoutRequestsGUIController() throws DBUnreachableException, SQLException {}

    @FXML void rejectButtonAction() {
        try {
            satisfyWorkoutRequestsController.rejectRequest(selectedRequest);
            ManageRequestList.updateList(requestList, satisfyWorkoutRequestsController);
            setTabVisibile(false);
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSizeChange.logOff();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML void newWorkoutButtonAction() throws IOException {
        NewWorkoutPlanGUIController controller = (NewWorkoutPlanGUIController) PageSwitchSimple.switchPage(MainPane.getInstance(),"NewWorkoutPlan",HOME);
        Objects.requireNonNull(controller).setRequest(selectedRequest);
    }

    @FXML void clarificationEmailButtonAction(ActionEvent event) throws IOException {
        EmailFormGUIController emailFormGUIController = (EmailFormGUIController) PageSwitchSizeChange.pageSwitch((Button) event.getSource(), "EmailForm", "", false);
        emailFormGUIController.setReceiver(selectedRequest.getAthleteBean());
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ManageRequestList.setRequestList(requestList, satisfyWorkoutRequestsController);
            requestList.getSelectionModel().selectedItemProperty().
                    addListener(new ChangeListener<>() {
                        @Override public void changed(ObservableValue<? extends RequestBean> observableValue, RequestBean oldItem, RequestBean newItem) {
                            if(newItem != null){
                                requestInfoLabel.setText(newItem.getInfo());
                                selectedRequest = newItem;
                                activateInfoTab();
                            }
                        }
                    });
            setUserInfoTab();
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

    public void setSelectedRequest(RequestBean requestBean) {
        this.selectedRequest = requestBean;
        requestInfoLabel.setText(selectedRequest.getInfo());
        activateInfoTab();
    }

    private void activateInfoTab() {
        if(once) {
            setTabVisibile(true);
        }
    }

    private void setTabVisibile(boolean bool) {
        requestInfoBox.setDisable(!bool);
        requestInfoBox.setVisible(bool);
        emptyInfoBox.setDisable(bool);
        emptyInfoBox.setVisible(!bool);
        once = !bool;
    }
}
