package viewone.engeneering.manageList;

import controller.SatisfyWorkoutRequestsController;
import exception.DBConnectionFailedException;
import exception.invalidDataException.EmptyFieldsException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import viewone.bean.RequestBean;
import viewone.list_cell_factories.RequestListCellFactory;

import java.sql.SQLException;

public class ManageRequestList {

    public static void setRequestList(ListView<RequestBean> requestList, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController) throws SQLException, DBConnectionFailedException {
        requestList.setCellFactory(nodeListView -> new RequestListCellFactory());
        updateList(requestList, satisfyWorkoutRequestsController);
    }

    public static void updateList(ListView<RequestBean> requestList, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController) throws SQLException, DBConnectionFailedException {
        ObservableList<RequestBean> requestBeanObservableList = FXCollections.observableList(satisfyWorkoutRequestsController.getTrainerRequests());
        requestList.setItems(FXCollections.observableList(requestBeanObservableList));
    }
}
