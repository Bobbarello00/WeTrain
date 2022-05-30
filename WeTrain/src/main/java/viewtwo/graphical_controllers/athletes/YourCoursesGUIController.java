package viewtwo.graphical_controllers.athletes;

import controllers.JoinLessonController;
import controllers.SubscribeToCourseController;
import engeneering.AlertGenerator;
import engeneering.manage_list.list_cell_factories.CourseListCellFactory;
import exceptions.BrowsingNotSupportedException;
import exceptions.DBUnreachableException;
import exceptions.NoScheduledLessonException;
import exceptions.UrlNotInsertedYetException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import viewone.beans.CourseBean;
import viewtwo.PageSwitchSimple;
import viewtwo.graphical_controllers.CourseInfoGUIController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class YourCoursesGUIController implements Initializable {

    public static final String ATHLETES = "athletes";
    @FXML private VBox courseActions;
    @FXML private ListView<CourseBean> courseList;

    private CourseBean selectedCourse;
    private static final SubscribeToCourseController subscribeToCourseController = new SubscribeToCourseController();

    @FXML void backButtonAction() throws IOException {
        PageSwitchSimple.switchPage("Courses", ATHLETES);
    }

    @FXML void courseInfoButtonAction() throws IOException {
        CourseInfoGUIController controller = (CourseInfoGUIController) PageSwitchSimple.switchPage("CourseInfo", "");
        if(controller!=null) {
            controller.setBackPathAndValues("YourCourses", ATHLETES, selectedCourse);
        }
    }

    @FXML void joinLessonButtonAction() {
        try {
            new JoinLessonController().joinLesson(selectedCourse);
        } catch (UrlNotInsertedYetException | BrowsingNotSupportedException | DBUnreachableException |
                 NoScheduledLessonException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
        } catch (URISyntaxException e) {
            AlertGenerator.newWarningAlert("EXCEPTION!",
                    "Url not working",
                    "The url inserted by the trainer is incorrect or not working anymore.");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @FXML void unsubscribeButtonAction() {
        try {
            subscribeToCourseController.unsubscribeFromCourse(selectedCourse);
            PageSwitchSimple.switchPage("AthletesHome", "athletes");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSimple.logOff();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        courseList.setCellFactory(nodeListView -> new CourseListCellFactory(true));
        courseList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<>() {
                                                                              @Override
                                                                              public void changed(ObservableValue<? extends CourseBean> observableValue, CourseBean oldCourse, CourseBean newCourse) {
                                                                                  if (newCourse != null) {
                                                                                      selectedCourse = newCourse;
                                                                                      courseActions.setDisable(false);
                                                                                  } else {
                                                                                      courseActions.setDisable(true);
                                                                                  }
                                                                              }
                                                                          }
        );
        try {
            courseList.setItems(FXCollections.observableList(subscribeToCourseController.getLoggedAthleteCourseList()));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSimple.logOff();
        }
    }
}
