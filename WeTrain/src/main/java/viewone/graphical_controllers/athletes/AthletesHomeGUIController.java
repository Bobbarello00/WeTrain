package viewone.graphical_controllers.athletes;

import controller.CourseManagementAthleteController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import viewone.bean.CourseBean;
import viewone.bean.CourseEssentialBean;
import viewone.engeneering.ManageList;
import viewone.list_cell_factories.CourseListCellFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AthletesHomeGUIController extends HomeGUIControllerAthletes implements Initializable {
    @FXML private ListView<CourseEssentialBean> courseList;
    @FXML private ListView<CourseEssentialBean> popularList;
    @FXML private ListView<Node> feedList;
    @FXML private Button logoutButton;
    private static CourseBean selectedCourse;

    private final CourseManagementAthleteController courseManagementAthleteController = CourseManagementAthleteController.getInstance();

    public static void setSelectedCourse(CourseBean course) {
        selectedCourse = course;
    }

    public static CourseBean getSelectedCourse() {
        return selectedCourse;
    }

    public void updateList() {
        List<CourseEssentialBean> courseBeanList = null;
        List<CourseEssentialBean> popularBeanList = null;
        try {
            courseBeanList = courseManagementAthleteController.getCourseList();
            popularBeanList = courseManagementAthleteController.getPopularCourseList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ManageList.updateList(courseList, courseBeanList);
        ManageList.updateList(popularList, popularBeanList);
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        courseList.setCellFactory(nodeListView -> new CourseListCellFactory());
        popularList.setCellFactory(nodeListView -> new CourseListCellFactory());

        updateList();

        ManageList.setCourseListener(courseList, courseManagementAthleteController, logoutButton);
        ManageList.setCourseListener(popularList, courseManagementAthleteController, logoutButton);

        setUserInfoTab();
    }
}
