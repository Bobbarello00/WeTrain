package viewtwo.graphical_controllers.trainers;

import engeneering.AlertGenerator;
import exceptions.DBUnreachableException;
import exceptions.invalid_data_exception.InvalidDataException;
import javafx.fxml.FXML;
import viewone.beans.CourseBean;
import viewtwo.PageSwitchSimple;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CreateCourseGUIController extends CreateOrModifyCourseGUIController{

    @FXML void createCourseButtonAction() {
        try{
            CourseBean courseBean = getCourseBean();
            manageCoursesController.createCourse(courseBean);
            PageSwitchSimple.switchPage("ManageCourses", "trainers");
        } catch (InvalidDataException e){
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
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
}
