package viewone.graphical_controllers.athletes;

import com.mysql.cj.exceptions.CJException;
import controller.CourseManagementAthleteController;
import exception.DBConnectionFailedException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import viewone.bean.CourseBean;
import viewone.engeneering.ManageCourseList;
import viewone.list_cell_factories.CourseListCellFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class AthletesHomeGUIController extends HomeGUIControllerAthletes implements Initializable {
    @FXML private ListView<CourseBean> courseList;
    @FXML private ListView<CourseBean> popularList;
    @FXML private ListView<Node> feedList;
    @FXML private Button logoutButton;

    private final CourseManagementAthleteController courseManagementAthleteController = new CourseManagementAthleteController();

    public void updateList() {
        List<CourseBean> courseBeanList = null;
        List<CourseBean> popularBeanList = null;
        try {
            courseBeanList = courseManagementAthleteController.getCourseList();
            popularBeanList = courseManagementAthleteController.getPopularCourseList();
        } catch (DBConnectionFailedException | CJException e) {
            new DBConnectionFailedException().alertAndLogOff();
            logoutButton.fire();
            return;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ManageCourseList.updateList(courseList, Objects.requireNonNull(courseBeanList));
        ManageCourseList.updateList(popularList, Objects.requireNonNull(popularBeanList));
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        courseList.setCellFactory(nodeListView -> new CourseListCellFactory());
        popularList.setCellFactory(nodeListView -> new CourseListCellFactory());

        updateList();

        ManageCourseList.setCourseListener(courseList);
        ManageCourseList.setCourseListener(popularList);

        setUserInfoTab();
    }
}
