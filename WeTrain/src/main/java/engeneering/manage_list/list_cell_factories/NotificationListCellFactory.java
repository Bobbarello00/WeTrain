package engeneering.manage_list.list_cell_factories;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import beans.NotificationBean;

import java.io.File;
import java.io.IOException;

public class NotificationListCellFactory extends ListCell<NotificationBean> {
    private final boolean viewtwo;
    public NotificationListCellFactory(boolean viewtwo){
        this.viewtwo = viewtwo;
    }
    private Parent parentNode = null ;
    @Override public void updateItem(NotificationBean notificationBean, boolean empty){
        updateNotificationListWithParameters(notificationBean, empty);
    }

    private void updateNotificationListWithParameters(NotificationBean notificationBean, boolean empty) {
        super.updateItem(notificationBean, empty);
        if(notificationBean != null){
            try {
                String view = "viewone";
                if(viewtwo) {
                    view = "viewtwo";
                }
                if (parentNode == null)parentNode = new FXMLLoader(new File("src/main/resources/"+view+"/ListItem.fxml").toURI().toURL()).load();
                ((Label)parentNode.lookup("#itemName")).setText(notificationBean.getSender().getUsername());
                ((Label)parentNode.lookup("#itemCode")).setText(Integer.toString(notificationBean.getId()));
                ((Label)parentNode.lookup("#itemOwner")).setText(notificationBean.getDateTime().toString());
                ((ImageView)parentNode.lookup("#itemIcon")).setImage(new Image(new File("src/main/resources/"+view+"/images/" + notificationBean.getType() + ".png").toURI().toString()));
                setGraphic(parentNode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            setGraphic(null);
        }
    }
}

