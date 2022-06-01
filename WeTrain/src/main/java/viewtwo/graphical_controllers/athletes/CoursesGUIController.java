package viewtwo.graphical_controllers.athletes;

import javafx.fxml.FXML;
import viewtwo.PageSwitchSimple;

import java.io.IOException;

public class CoursesGUIController {

    public static final String ATHLETES = "athletes";

    @FXML void backButtonAction() throws IOException {
        PageSwitchSimple.switchPage("AthletesHome", ATHLETES);
    }

    @FXML void findCourseButtonAction() throws IOException {
        PageSwitchSimple.switchPage("FindCourse", ATHLETES);
    }

    @FXML void yourCoursesButtonAction() throws IOException {
        PageSwitchSimple.switchPage("YourCourses", ATHLETES);
    }
}
