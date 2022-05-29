package viewtwo.graphical_controllers.athletes;

import engeneering.manage_list.list_cell_factories.CourseListCellFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import viewone.bean.CourseBean;
import viewtwo.PageSwitchSimple;
import viewtwo.graphical_controllers.CourseInfoGUIController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class YourCoursesGUIController implements Initializable {

    public static final String ATHLETES = "athletes";
    @FXML private VBox courseActions;
    private CourseBean selectedCourse;
    @FXML private ListView<CourseBean> courseList;

    @FXML void backButtonAction() throws IOException {
        PageSwitchSimple.switchPage("Courses", ATHLETES);
    }

    @FXML void courseInfoButtonAction() throws IOException {
        CourseInfoGUIController controller = (CourseInfoGUIController) PageSwitchSimple.switchPage("CourseInfo", ATHLETES);
        if(controller!=null) {
            controller.setBackPathAndValues("YourCourses", ATHLETES, selectedCourse);
        }
    }

    @FXML void joinLessonButtonAction(ActionEvent event) {

    }

    @FXML void unsubscribeButtonAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        courseList.setCellFactory(nodeListView -> new CourseListCellFactory(true));
        courseList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CourseBean>() {
              @Override
              public void changed(ObservableValue<? extends CourseBean> observableValue, CourseBean oldCourse, CourseBean newCourse) {
                  if(newCourse!=null) {
                      selectedCourse = newCourse;
                      courseActions.setDisable(false);
                  }else{
                      courseActions.setDisable(true);
                  }
              }
          }
        );
    }
}
