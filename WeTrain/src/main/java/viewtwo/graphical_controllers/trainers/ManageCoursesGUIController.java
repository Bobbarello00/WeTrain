package viewtwo.graphical_controllers.trainers;

import controllers.ManageCoursesController;
import engeneering.AlertGenerator;
import engeneering.manage_list.list_cell_factories.CourseListCellFactory;
import exceptions.DBUnreachableException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import beans.CourseBean;
import viewtwo.PageSwitchSimple;
import viewtwo.graphical_controllers.CourseInfoGUIController;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ManageCoursesGUIController implements Initializable {

    public static final String TRAINERS = "trainers";
    @FXML private VBox courseActions;
    @FXML private ListView<CourseBean> courseList;

    private CourseBean selectedCourse;
    private final ManageCoursesController manageCoursesController = new ManageCoursesController();

    @FXML void backButtonAction() throws IOException {
        PageSwitchSimple.switchPage("TrainersHome", TRAINERS);
    }

    @FXML void createNewCourseButtonAction() throws IOException {
        PageSwitchSimple.switchPage("CreateCourse", TRAINERS);
    }

    @FXML void deleteCourseButtonAction() {
        try {
            manageCoursesController.deleteCourse(selectedCourse);
            updateList();
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSimple.logOff();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateList() throws DBUnreachableException, SQLException {
        ObservableList<CourseBean> courseObservableList = FXCollections.observableList(manageCoursesController.getCourseList());
        courseList.setItems(FXCollections.observableList(courseObservableList));
    }

    @FXML void modifyCourseButtonAction() throws IOException {
        ModifyCourseGUIController modifyCourseGUIController = (ModifyCourseGUIController) PageSwitchSimple.switchPage("ModifyCourse", TRAINERS);
        if(modifyCourseGUIController != null) {
            modifyCourseGUIController.setCourseToModify(selectedCourse);
        }
    }

    @FXML void sendCommunicationButtonAction() throws IOException {
        WriteCommunicationGUIController writeCommunicationGUIController = (WriteCommunicationGUIController) PageSwitchSimple.switchPage("WriteCommunication", TRAINERS);
        if(writeCommunicationGUIController != null) {
            writeCommunicationGUIController.setCourseBean(selectedCourse);
        }
    }

    @FXML void courseInfoButtonAction() throws IOException {
        CourseInfoGUIController courseInfoGUIController = (CourseInfoGUIController) PageSwitchSimple.switchPage("CourseInfo", "");
        if(courseInfoGUIController != null) {
            courseInfoGUIController.setBackPathAndValues("ManageCourses", TRAINERS, selectedCourse);
        }
    }

    @FXML void startLessonButtonAction() throws IOException {
        StartLessonGUIController startLessonGUIController = (StartLessonGUIController) PageSwitchSimple.switchPage("StartLesson", TRAINERS);
        if(startLessonGUIController != null) {
            startLessonGUIController.setCourseBean(selectedCourse);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            courseList.setCellFactory(nodeListView -> new CourseListCellFactory(true));
            updateList();
            courseList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<>() {
                @Override
                public void changed(ObservableValue<? extends CourseBean> observableValue, CourseBean oldItem, CourseBean newItem) {
                    if(newItem != null){
                        selectedCourse = newItem;
                        courseActions.setDisable(false);
                    } else {
                        selectedCourse = null;
                        courseActions.setDisable(true);
                    }
                }
            });
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSimple.logOff();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
