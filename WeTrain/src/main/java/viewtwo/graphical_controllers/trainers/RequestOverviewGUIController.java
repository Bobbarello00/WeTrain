package viewtwo.graphical_controllers.trainers;

import controller.SatisfyWorkoutRequestsController;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import viewone.bean.RequestBean;
import viewtwo.PageSwitchSimple;
import viewtwo.graphical_controllers.EmailFormGUIController;

import java.io.IOException;

public class RequestOverviewGUIController {

    @FXML private TextArea requestDescription;

    private RequestBean selectedRequest;
    private SatisfyWorkoutRequestsController satisfyWorkoutRequestsController;

    public void setValue(RequestBean requestBean, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController) {
        selectedRequest = requestBean;
        this.satisfyWorkoutRequestsController = satisfyWorkoutRequestsController;
        requestDescription.setText(requestBean.getInfo());
    }

    @FXML void askClarificationAction() throws IOException {
        EmailFormGUIController controller = (EmailFormGUIController) PageSwitchSimple.switchPage("CreateWorkoutPlan", "trainers");
        if(controller != null) {
            controller.setBackPathAndReceiver("RequestsPage", "trainers", selectedRequest.getAthleteBean());
        }
    }

    @FXML void backButtonAction() throws IOException {
        PageSwitchSimple.switchPage("RequestsPage", "trainers");
    }

    @FXML void createWorkoutAction() throws IOException {
        CreateWorkoutPlanGUIController controller = (CreateWorkoutPlanGUIController)PageSwitchSimple.switchPage("CreateWorkoutPlan", "trainers");
        if(controller != null) {
            controller.setValue(selectedRequest, satisfyWorkoutRequestsController, 0);
        }
    }
}