package viewone.engeneering;

import controller.CourseManagementAthleteController;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import viewone.PageSwitchSizeChange;
import viewone.bean.CourseEssentialBean;
import viewone.bean.IdBean;
import viewone.graphical_controllers.athletes.AthletesHomeGUIController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ManageList {

    private ManageList() {}

    public static void updateList(ListView<CourseEssentialBean> courseList, List<CourseEssentialBean> courseEssentialBeanList) {
        ObservableList<CourseEssentialBean> courseObservableList = FXCollections.observableList(courseEssentialBeanList);
        courseList.setItems(FXCollections.observableList(courseObservableList));
    }

    public static void setCourseListener(ListView<CourseEssentialBean> list, CourseManagementAthleteController courseManagementAthleteController, Button button){
        list.getSelectionModel().selectedItemProperty().
                addListener(new ChangeListener<>() {
                    @Override
                    public void changed(ObservableValue<? extends CourseEssentialBean> observableValue, CourseEssentialBean oldItem, CourseEssentialBean newItem) {
                        eventList(list, newItem, courseManagementAthleteController, button);
                    }
                });
    }

    private static void eventList(ListView<CourseEssentialBean> listView, CourseEssentialBean newItem, CourseManagementAthleteController courseManagementAthleteController, Button button) {
        try {
            if(newItem != null) {
                AthletesHomeGUIController.setSelectedCourse(courseManagementAthleteController.getCourse(new IdBean(newItem.getId())));
                PageSwitchSizeChange.pageSwitch(button, "CourseInfo", "athletes", false);
                Platform.runLater(() -> listView.getSelectionModel().clearSelection());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
