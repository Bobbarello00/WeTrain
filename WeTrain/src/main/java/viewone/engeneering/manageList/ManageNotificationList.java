package viewone.engeneering.manageList;

import controller.NotificationsController;
import exception.DBConnectionFailedException;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import org.jetbrains.annotations.NotNull;
import viewone.bean.NotificationBean;
import viewone.engeneering.AlertFactory;
import viewone.graphical_controllers.athletes.AthletesHomeGUIController;

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
                AlertFactory.newInformationAlert(
                        "Notification",
                        newItem.getText());
                notificationsController.deleteNotification(newItem);
                Platform.runLater(() -> listView.getSelectionModel().clearSelection());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DBConnectionFailedException e) {
            e.alertAndLogOff();
        }
    }
}
