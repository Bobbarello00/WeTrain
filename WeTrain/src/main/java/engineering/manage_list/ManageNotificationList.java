package engineering.manage_list;

import controllers.NotificationsController;
import exceptions.DBUnreachableException;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import org.jetbrains.annotations.NotNull;
import viewone.PageSwitchSizeChange;
import beans.NotificationBean;
import engineering.AlertGenerator;

import java.sql.SQLException;
import java.util.List;

public class ManageNotificationList {

    private static final NotificationsController notificationsController = new NotificationsController();

    private ManageNotificationList() {}

    public static void updateList(@NotNull ListView<NotificationBean> notificationBeanListView, @NotNull List<NotificationBean> notificationBeanList) {
        ObservableList<NotificationBean> notificationObservableList = FXCollections.observableList(notificationBeanList);
        notificationBeanListView.setItems(FXCollections.observableList(notificationObservableList));
    }

    public static void setCourseListener(ListView<NotificationBean> list){
        list.getSelectionModel().selectedItemProperty().
                addListener(new ChangeListener<>() {
                    @Override
                    public void changed(ObservableValue<? extends NotificationBean> observableValue, NotificationBean oldItem, NotificationBean newItem) {
                        listEvent(list, newItem);
                    }
                });
    }

    private static void listEvent(ListView<NotificationBean> listView, NotificationBean newItem) {
        try {
            if(newItem != null) {
                AlertGenerator.newInformationAlert(
                        "Notification",
                        newItem.getText());
                notificationsController.deleteNotification(newItem);
                Platform.runLater(() -> listView.getSelectionModel().clearSelection());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSizeChange.logOff();
        }
    }
}
