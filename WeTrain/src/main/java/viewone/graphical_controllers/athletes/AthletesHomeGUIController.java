package viewone.graphical_controllers.athletes;

import controller.CourseManagementAthleteController;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import viewone.PageSwitchSizeChange;
import viewone.bean.CourseBean;
import viewone.bean.CourseEssentialBean;
import viewone.bean.CourseSearchBean;
import viewone.list_cell_factories.CourseListCellFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AthletesHomeGUIController extends HomeGUIControllerAthletes implements Initializable {
    @FXML private ListView<CourseEssentialBean> courseList;
    @FXML private ListView<Node> popularList;
    @FXML private ListView<Node> feedList;
    @FXML private Button logoutButton;
    private static CourseBean selectedCourse;

    private final CourseManagementAthleteController courseManagementAthleteController = CourseManagementAthleteController.getInstance();

    public void eventList(ListView<CourseEssentialBean> listView, CourseEssentialBean newItem) {
        try {
            if(newItem != null) {
                setSelectedCourse(courseManagementAthleteController.getCourse(new CourseSearchBean(newItem.getId())));
                PageSwitchSizeChange.pageSwitch(logoutButton, "CourseInfo", "athletes", false);
                Platform.runLater(() -> listView.getSelectionModel().clearSelection());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setSelectedCourse(CourseBean course) {
        selectedCourse = course;
    }

    public static CourseBean getSelectedCourse() {
        return selectedCourse;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO fare query per corsi più popolari

        courseList.setCellFactory(nodeListView -> new CourseListCellFactory());
        try {
            ObservableList<CourseEssentialBean> courseObservableList = FXCollections.observableList(courseManagementAthleteController.getCourseList());
            courseList.setItems(FXCollections.observableList(courseObservableList));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        courseList.getSelectionModel().selectedItemProperty().
                addListener(new ChangeListener<CourseEssentialBean>() {
                    @Override
                    public void changed(ObservableValue<? extends CourseEssentialBean> observableValue, CourseEssentialBean oldItem, CourseEssentialBean newItem) {
                        eventList(courseList, newItem);
                    }
                });
        setUsername();
    }
}
