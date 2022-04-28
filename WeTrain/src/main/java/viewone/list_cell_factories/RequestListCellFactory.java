package viewone.list_cell_factories;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import viewone.WeTrain;
import viewone.bean.RequestBean;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

public class RequestListCellFactory extends ListCell<RequestBean> {

    private Parent parentNode = null ;

    @Override public void updateItem(RequestBean requestBean, boolean empty) {
        updateRequestListWithParameters(requestBean, empty);
    }

    private void updateRequestListWithParameters(RequestBean requestBean, boolean empty) {
        super.updateItem(requestBean, empty);
        if(requestBean != null){
            try {
                if (parentNode == null)parentNode = new FXMLLoader(WeTrain.class.getResource("ListItem.fxml")).load();
                ((Label)parentNode.lookup("#itemName")).setText(requestBean.getAthleteUsername());
                ((Label)parentNode.lookup("#itemCode")).setText(Integer.toString(requestBean.getId()));
                ((Label)parentNode.lookup("#itemOwner")).setText("");
                ((ImageView)parentNode.lookup("#itemIcon")).setImage(new Image(Objects.requireNonNull(WeTrain.class.getResource("images/" + "request" + ".png")).toURI().toString()));
                setGraphic(parentNode);
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }else{
            setGraphic(null);
        }
    }
}
