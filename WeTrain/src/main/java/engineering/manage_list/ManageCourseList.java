package engineering.manage_list;

import controllers.SubscribeToCourseController;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import viewone.PageSwitchSizeChange;
import beans.CourseBean;
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

    public static void setListener(ListView<CourseBean> list, SubscribeToCourseController subscribeToCourseController){
        list.getSelectionModel().selectedItemProperty().
                addListener(new ChangeListener<>() {
                    @Override
                    public void changed(ObservableValue<? extends CourseBean> observableValue, CourseBean oldItem, CourseBean newItem) {
                        listEvent(list, newItem, subscribeToCourseController);
                    }
                });
    }

    private static void listEvent(ListView<CourseBean> listView, CourseBean newItem, SubscribeToCourseController subscribeToCourseController) {
        try {
            if(newItem != null) {
                CourseOverviewGUIController courseOverviewGUIController =
                        (CourseOverviewGUIController) PageSwitchSizeChange.pageSwitch((Stage) null,
                                "CourseOverview",
                                "",
                                false);
                courseOverviewGUIController.setValues(newItem, subscribeToCourseController);
                Platform.runLater(() -> listView.getSelectionModel().clearSelection());
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
