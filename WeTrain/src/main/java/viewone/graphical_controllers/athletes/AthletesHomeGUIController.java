package viewone.graphical_controllers.athletes;

import controller.SubscribeToCourseController;
import exception.DBUnreachableException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import viewone.PageSwitchSizeChange;
import viewone.bean.CourseBean;
import engeneering.AlertGenerator;
import engeneering.manage_list.ManageCourseList;
import engeneering.manage_list.ManageNotificationList;
import engeneering.manage_list.list_cell_factories.CourseListCellFactory;
import engeneering.manage_list.list_cell_factories.NotificationListCellFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class AthletesHomeGUIController extends HomeGUIControllerAthletes implements Initializable {
    @FXML private ListView<CourseBean> courseList;
    @FXML private ListView<CourseBean> popularList;

    private final SubscribeToCourseController subscribeToCourseController = new SubscribeToCourseController();

    public void updateLists() {
        try {
            List<CourseBean> courseBeanList = subscribeToCourseController.getPopularCourseList();
            ManageCourseList.updateList(popularList, Objects.requireNonNull(courseBeanList));
            updateNotificationList();
            courseBeanList = subscribeToCourseController.getLoggedAthleteCourseList();
            ManageCourseList.updateList(courseList, Objects.requireNonNull(courseBeanList));
        } catch (DBUnreachableException e){
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

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        courseList.setCellFactory(nodeListView -> new CourseListCellFactory(false));
        popularList.setCellFactory(nodeListView -> new CourseListCellFactory(false));
        notificationList.setCellFactory(nodeListView -> new NotificationListCellFactory(false));

        updateLists();

        ManageCourseList.setListener(courseList, subscribeToCourseController);
        ManageCourseList.setListener(popularList, subscribeToCourseController);
        ManageNotificationList.setCourseListener(notificationList);


        setUserInfoTab();
    }
}
