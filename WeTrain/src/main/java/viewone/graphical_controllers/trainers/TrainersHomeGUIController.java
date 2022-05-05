package viewone.graphical_controllers.trainers;

import controller.CourseManagementTrainerController;
import controller.SatisfyWorkoutRequestsController;
import exception.DBConnectionFailedException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import viewone.MainPane;
import viewone.PageSwitchSimple;
import viewone.bean.CourseBean;
import viewone.bean.RequestBean;
import viewone.engeneering.manageList.ManageCourseList;
import viewone.engeneering.manageList.ManageRequestList;
import viewone.list_cell_factories.CourseListCellFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TrainersHomeGUIController extends HomeGUIControllerTrainers implements Initializable {
    @FXML private ListView<CourseBean> courseList;
    @FXML private ListView<RequestBean> requestList;

    private final SatisfyWorkoutRequestsController satisfyWorkoutRequestsController = new SatisfyWorkoutRequestsController();
    private final CourseManagementTrainerController courseManagementTrainerController = new CourseManagementTrainerController();

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ManageRequestList.setRequestList(requestList, satisfyWorkoutRequestsController);
            requestList.getSelectionModel().selectedItemProperty().
                    addListener(new ChangeListener<>() {
                        @Override
                        public void changed(ObservableValue<? extends RequestBean> observableValue, RequestBean oldItem, RequestBean newItem) {
                            try {
                                WorkoutRequestsGUIController workoutRequestsGUIController = (WorkoutRequestsGUIController) PageSwitchSimple.switchPage(MainPane.getInstance(), "WorkoutRequests", "trainers");
                                if(workoutRequestsGUIController != null) workoutRequestsGUIController.setSelectedRequest(newItem);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
            courseList.setCellFactory(nodeListView -> new CourseListCellFactory());
            ManageCourseList.setCourseListener(courseList);
            ManageCourseList.updateList(courseList, courseManagementTrainerController.getCourseList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (DBConnectionFailedException e) {
            e.alertAndLogOff();
        }
        setUserInfoTab();
    }

}
