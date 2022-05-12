package viewone.graphical_controllers.athletes;

import com.mysql.cj.exceptions.CJException;
import controller.CourseManagementAthleteController;
import exception.DBUnreachableException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import viewone.bean.CourseBean;
import viewone.engeneering.manageList.ManageCourseList;
import viewone.engeneering.manageList.ManageNotificationList;
import viewone.list_cell_factories.CourseListCellFactory;
import viewone.list_cell_factories.NotificationListCellFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class AthletesHomeGUIController extends HomeGUIControllerAthletes implements Initializable {
    @FXML private ListView<CourseBean> courseList;
    @FXML private ListView<CourseBean> popularList;

    @FXML private Button logoutButton;

    private final CourseManagementAthleteController courseManagementAthleteController = new CourseManagementAthleteController();

    public void updateLists() {
        try {
            List<CourseBean> courseBeanList = courseManagementAthleteController.getPopularCourseList();
            ManageCourseList.updateList(popularList, Objects.requireNonNull(courseBeanList));
            updateNotificationList();
            courseBeanList = courseManagementAthleteController.getCourseList();
            ManageCourseList.updateList(courseList, Objects.requireNonNull(courseBeanList));
        } catch (DBUnreachableException | CJException e) {
            new DBUnreachableException().alertAndLogOff();
            logoutButton.fire();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        courseList.setCellFactory(nodeListView -> new CourseListCellFactory());
        popularList.setCellFactory(nodeListView -> new CourseListCellFactory());
        notificationList.setCellFactory(nodeListView -> new NotificationListCellFactory());

        updateLists();

        ManageCourseList.setCourseListener(courseList);
        ManageCourseList.setCourseListener(popularList);
        ManageNotificationList.setCourseListener(notificationList);


        setUserInfoTab();
    }
}
