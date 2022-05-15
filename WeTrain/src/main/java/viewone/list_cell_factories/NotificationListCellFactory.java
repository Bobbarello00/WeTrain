package viewone.list_cell_factories;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import viewone.WeTrain;
import viewone.bean.NotificationBean;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

public class NotificationListCellFactory extends ListCell<NotificationBean> {
    private Parent parentNode = null ;
    @Override public void updateItem(NotificationBean notificationBean, boolean empty){
        updateNotificationListWithParameters(notificationBean, empty);
    }

    private void updateNotificationListWithParameters(NotificationBean notificationBean, boolean empty) {
        super.updateItem(notificationBean, empty);
        if(notificationBean != null){
            try {
                if (parentNode == null)parentNode = new FXMLLoader(WeTrain.class.getResource("ListItem.fxml")).load();
                ((Label)parentNode.lookup("#itemName")).setText(notificationBean.getSender().getUsername());
                ((Label)parentNode.lookup("#itemCode")).setText(Integer.toString(notificationBean.getId()));
                ((Label)parentNode.lookup("#itemOwner")).setText(notificationBean.getDateTime().toString());
                ((ImageView)parentNode.lookup("#itemIcon")).setImage(new Image(Objects.requireNonNull(WeTrain.class.getResource("images/" + notificationBean.getType() + ".png")).toURI().toString()));
                setGraphic(parentNode);
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }else{
            setGraphic(null);
        }
    }
}

