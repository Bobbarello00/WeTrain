package viewone.list_cell_factories;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import viewone.WeTrain;
import viewone.bean.UserBean;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

public class PersonListCellFactory extends ListCell<UserBean> {
    private Parent parentNode = null ;

    @Override public void updateItem(UserBean userBean, boolean empty){
        updatePersonListWithParameter(userBean, empty, "person");
    }

    public void updatePersonListWithParameter(UserBean userBean, boolean empty, String str) {
        super.updateItem(userBean, empty);
        if(userBean != null){
            try {
                if (parentNode == null)parentNode = new FXMLLoader(WeTrain.class.getResource("ListItem.fxml")).load();
                ((Label)parentNode.lookup("#itemName")).setText(userBean.getName() + " " + userBean.getSurname());
                ((Label)parentNode.lookup("#itemOwner")).setText(userBean.getUsername());
                ((Label)parentNode.lookup("#itemCode")).setText(userBean.getFiscalCode());
                if(Objects.equals(userBean.getType(), "Trainer")){
                    if (userBean.getGender() == 'm'){
                        setImage("TrainerM");
                        }else{
                        setImage("TrainerF");
                    }
                }else {
                    if (userBean.getGender() == 'm'){
                        setImage("AthleteM");
                    }else{
                        setImage("AthleteF");
                    }
                }
                setGraphic(parentNode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            setGraphic(null);
        }
    }

    private void setImage(String str){
        try {
            ((ImageView) parentNode.lookup("#itemIcon")).setImage(new Image(Objects.requireNonNull(WeTrain.class.getResource("images/"+str+".png")).toURI().toString()));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
