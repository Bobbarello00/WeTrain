package engeneering.manage_list.list_cell_factories;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import viewone.beans.RequestBean;

import java.io.File;
import java.io.IOException;

public class RequestListCellFactory extends ListCell<RequestBean> {
    private final boolean viewtwo;
    public RequestListCellFactory(boolean viewtwo){
        this.viewtwo = viewtwo;
    }

    private Parent parentNode = null ;

    @Override public void updateItem(RequestBean requestBean, boolean empty) {
        updateRequestListWithParameters(requestBean, empty);
    }

    private void updateRequestListWithParameters(RequestBean requestBean, boolean empty) {
        super.updateItem(requestBean, empty);
        if(requestBean != null){
            try {
                String view = "viewone";
                if(viewtwo) {
                    view = "viewtwo";
                }
                if (parentNode == null)parentNode = new FXMLLoader(new File("src/main/resources/"+view+"/ListItem.fxml").toURI().toURL()).load();
                ((Label)parentNode.lookup("#itemName")).setText(requestBean.getAthleteBean().getUsername());
                ((Label)parentNode.lookup("#itemCode")).setText(Integer.toString(requestBean.getId()));
                ((Label)parentNode.lookup("#itemOwner")).setText("");
                ((ImageView)parentNode.lookup("#itemIcon")).setImage(new Image(new File("src/main/resources/"+view+"/images/request.png").toURI().toString()));
                setGraphic(parentNode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            setGraphic(null);
        }
    }
}
