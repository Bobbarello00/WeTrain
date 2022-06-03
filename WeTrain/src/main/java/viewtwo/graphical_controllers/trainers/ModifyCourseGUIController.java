package viewtwo.graphical_controllers.trainers;

import engineering.AlertGenerator;
import exceptions.DBUnreachableException;
import exceptions.invalid_data_exception.InvalidDataException;
import javafx.fxml.FXML;
import beans.CourseBean;
import viewtwo.PageSwitchSimple;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ModifyCourseGUIController extends CreateOrModifyCourseGUIController{

    private CourseBean courseToModify;

    public void setCourseToModify(CourseBean courseBean) {
        courseToModify = courseBean;
        setValue(courseToModify);
    }

    @FXML
    void createCourseButtonAction() {
        try{
            CourseBean courseBean = getCourseBean();
            courseBean.setId(courseToModify.getId());
            manageCoursesController.modifyCourse(courseBean, courseToModify.getId());
            PageSwitchSimple.switchPage("ManageCourses", "trainers");
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSimple.logOff();
        } catch (InvalidDataException e){
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
