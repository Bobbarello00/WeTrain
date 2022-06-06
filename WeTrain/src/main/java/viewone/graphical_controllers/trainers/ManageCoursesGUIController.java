package viewone.graphical_controllers.trainers;

import controllers.ManageCoursesController;
import exceptions.DBUnreachableException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import engineering.MainPane;
import viewone.PageSwitchSimple;
import viewone.PageSwitchSizeChange;
import beans.CourseBean;
import beans.LessonBean;
import engineering.AlertGenerator;
import engineering.manage_list.ManageCourseList;
import engineering.manage_list.list_cell_factories.CourseListCellFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ManageCoursesGUIController extends HomeGUIControllerTrainers implements Initializable {
    @FXML private ListView<CourseBean> courseList;
    @FXML private VBox requestInfoBox;
    @FXML private VBox emptyInfoBox;
    @FXML private TextArea generalInfoTextArea;
    @FXML private TextArea equipmentTextArea;
    @FXML private Label fitnessLevelLabel;
    @FXML private Label timeScheduleLabel;
    @FXML private Label trainerNameLabel;
    @FXML private Label courseNameLabel;


    private CourseBean selectedCourse;
    private final ManageCoursesController manageCoursesController = new ManageCoursesController();

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            courseList.setCellFactory(nodeListView -> new CourseListCellFactory(false));
            courseList.getSelectionModel().selectedItemProperty().
                    addListener(new ChangeListener<>() {
                        @Override
                        public void changed(ObservableValue<? extends CourseBean> observableValue, CourseBean oldItem, CourseBean newItem) {
                            selectedCourse = newItem;
                            if(newItem != null){
                                courseNameLabel.setText(selectedCourse.getName());
                                trainerNameLabel.setText(selectedCourse.getOwner());
                                String timeSchedule = "";
                                for(LessonBean lessonBean: selectedCourse.getLessonBeanList()){
                                    timeSchedule += lessonBean.getLessonDay();
                                    timeSchedule += " ";
                                }
                                timeScheduleLabel.setText(timeSchedule);
                                fitnessLevelLabel.setText(selectedCourse.getFitnessLevel());
                                equipmentTextArea.setText(selectedCourse.getEquipment());
                                generalInfoTextArea.setText(selectedCourse.getDescription());
                                setVisible(true);
                            }
                        }
                    });
            ManageCourseList.updateList(courseList, manageCoursesController.getCourseList());
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSizeChange.logOff();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setUserInfoTab();
    }

    private void setVisible(boolean bool) {
        emptyInfoBox.setVisible(!bool);
        requestInfoBox.setDisable(!bool);
        requestInfoBox.setVisible(bool);
    }

    @FXML public void deleteCourseButtonAction() {
        if(AlertGenerator.newConfirmationAlert(
                "ATTENTION!",
                "CONFIRMATION",
                "If you click 'ok' the selected course will be deleted!\n " +
                        "This operation can't be undone.")) {
            try {
                manageCoursesController.deleteCourse(selectedCourse);
                setVisible(false);
                ManageCourseList.updateList(courseList, manageCoursesController.getCourseList());
            } catch (DBUnreachableException e) {
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
    }

    @FXML public void modifyCourseButtonAction() throws IOException {
        NewCourseGUIController newCourseGUIController = (NewCourseGUIController) PageSwitchSimple.switchPage(MainPane.getInstance(), "NewCourse", "trainers");
        MenuTrainersGUIController.resetSelectedButton();
        if(newCourseGUIController != null) {
            newCourseGUIController.setValue(selectedCourse);
        }
    }
}

