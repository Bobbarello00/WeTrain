package viewone.engeneering.manageList;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import viewone.PageSwitchSizeChange;
import viewone.bean.CourseBean;
import viewone.graphical_controllers.CourseOverviewGUIController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ManageCourseList {

    private ManageCourseList() {}

    public static void updateList(@NotNull ListView<CourseBean> courseList, @NotNull List<CourseBean> courseBeanList) {
        ObservableList<CourseBean> courseObservableList = FXCollections.observableList(courseBeanList);
        courseList.setItems(FXCollections.observableList(courseObservableList));
    }

    public static void setCourseListener(ListView<CourseBean> list){
        list.getSelectionModel().selectedItemProperty().
                addListener(new ChangeListener<>() {
                    @Override
                    public void changed(ObservableValue<? extends CourseBean> observableValue, CourseBean oldItem, CourseBean newItem) {
                        listEvent(list, newItem);
                    }
                });
    }

    private static void listEvent(ListView<CourseBean> listView, CourseBean newItem) {
        try {
            if(newItem != null) {
                CourseOverviewGUIController courseOverviewGUIController =
                        (CourseOverviewGUIController) PageSwitchSizeChange.pageSwitch((Stage) null,
                                "CourseOverview",
                                "",
                                false);
                courseOverviewGUIController.setValues(newItem);
                Platform.runLater(() -> listView.getSelectionModel().clearSelection());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
