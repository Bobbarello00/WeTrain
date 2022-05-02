package viewone.graphical_controllers.trainers;

import controller.CourseManagementTrainerController;
import exception.DBConnectionFailedException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import viewone.MainPane;
import viewone.PageSwitchSimple;
import viewone.bean.CourseBean;
import viewone.bean.LessonBean;
import viewone.engeneering.ManageCourseList;
import viewone.list_cell_factories.CourseListCellFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
    private final CourseManagementTrainerController courseManagementTrainerController = new CourseManagementTrainerController();

    public ManageCoursesGUIController() {}

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            courseList.setCellFactory(nodeListView -> new CourseListCellFactory());
            courseList.getSelectionModel().selectedItemProperty().
                    addListener(new ChangeListener<>() {
                        @Override
                        public void changed(ObservableValue<? extends CourseBean> observableValue, CourseBean oldItem, CourseBean newItem) {
                            selectedCourse = newItem;
                            if(newItem != null){
                                courseNameLabel.setText(selectedCourse.getName());
                                trainerNameLabel.setText(selectedCourse.getOwner());
                                StringBuilder timeSchedule = new StringBuilder();
                                for(LessonBean lessonBean: selectedCourse.getLessonBeanList()){
                                    timeSchedule.append(lessonBean.getLessonDay());
                                    timeSchedule.append(" ");
                                }
                                timeScheduleLabel.setText(String.valueOf(timeSchedule));
                                fitnessLevelLabel.setText(selectedCourse.getFitnessLevel());
                                equipmentTextArea.setText(selectedCourse.getEquipment());
                                generalInfoTextArea.setText(selectedCourse.getDescription());
                                setVisible(true);
                            }
                        }
                    });
            ManageCourseList.updateList(courseList, courseManagementTrainerController.getCourseList());
        } catch (DBConnectionFailedException e) {
            e.alertAndLogOff();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        setUserInfoTab();
    }

    private void setVisible(boolean bool) {
        emptyInfoBox.setVisible(!bool);
        requestInfoBox.setDisable(!bool);
        requestInfoBox.setVisible(bool);
    }

    @FXML public void deleteCourseButtonAction() {
        try {
            courseManagementTrainerController.deleteCourse(selectedCourse);
            setVisible(false);
            ManageCourseList.updateList(courseList, courseManagementTrainerController.getCourseList());
        } catch (DBConnectionFailedException e) {
            e.alertAndLogOff();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML public void modifyCourseButtonAction() throws IOException {
        NewCourseGUIController newCourseGUIController = (NewCourseGUIController) PageSwitchSimple.switchPage(MainPane.getInstance(), "NewCourse", "trainers");
        if(newCourseGUIController != null) {
            newCourseGUIController.setValue(selectedCourse);
        }
    }
}

