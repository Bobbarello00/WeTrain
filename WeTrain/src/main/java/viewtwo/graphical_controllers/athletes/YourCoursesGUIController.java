package viewtwo.graphical_controllers.athletes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import viewtwo.PageSwitchSimple;

import java.io.IOException;

public class YourCoursesGUIController {

    @FXML private VBox courseActions;
    @FXML private ListView<?> courseList;

    @FXML void backButtonAction() throws IOException {
        PageSwitchSimple.switchPage("Courses", "athletes");
    }

    @FXML void courseInfoButtonAction() throws IOException {
        PageSwitchSimple.switchPage("CourseInfo", "athletes");
    }

    @FXML void joinLessonButtonAction(ActionEvent event) {

    }

    @FXML void unsubscribeButtonAction(ActionEvent event) {

    }

}
