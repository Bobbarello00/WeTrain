package viewtwo.graphical_controllers.athletes;

import javafx.fxml.FXML;
import viewtwo.PageSwitchSimple;

import java.io.IOException;

public class CoursesGUIController {

    @FXML void backButtonAction() throws IOException {
        PageSwitchSimple.switchPage("AthletesHome", "athletes");
    }

    @FXML void findCourseButtonAction() throws IOException {
        PageSwitchSimple.switchPage("FindCourse", "athletes");
    }

    @FXML void yourCoursesButtonAction() throws IOException {
        PageSwitchSimple.switchPage("YourCourses", "athletes");
    }
}
