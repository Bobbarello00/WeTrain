package engeneering.manage_list;

import controller.SatisfyWorkoutRequestsController;
import exception.DBUnreachableException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import viewone.bean.RequestBean;
import viewone.list_cell_factories.RequestListCellFactory;

import java.sql.SQLException;

public class ManageRequestList {

    private ManageRequestList() {}

    public static void setRequestList(ListView<RequestBean> requestList, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController) throws SQLException, DBUnreachableException {
        requestList.setCellFactory(nodeListView -> new RequestListCellFactory());
        updateList(requestList, satisfyWorkoutRequestsController);
    }

    public static void updateList(ListView<RequestBean> requestList, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController) throws SQLException, DBUnreachableException {
        ObservableList<RequestBean> requestBeanObservableList = FXCollections.observableList(satisfyWorkoutRequestsController.getTrainerRequests());
        requestList.setItems(FXCollections.observableList(requestBeanObservableList));
    }
}
