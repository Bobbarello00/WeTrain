package engeneering.manage_list.list_cell_factories;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import viewone.bean.UserBean;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class PersonListCellFactory extends ListCell<UserBean> {
    private final boolean viewtwo;
    public PersonListCellFactory(boolean viewtwo){
        this.viewtwo = viewtwo;
    }

    private Parent parentNode = null ;

    @Override public void updateItem(UserBean userBean, boolean empty){
        updatePersonListWithParameters(userBean, empty);
    }

    public void updatePersonListWithParameters(UserBean userBean, boolean empty) {
        super.updateItem(userBean, empty);
        if(userBean != null){
            try {
                String view = "viewone";
                if(viewtwo) {
                    view = "viewtwo";
                }
                if (parentNode == null)parentNode = new FXMLLoader(new File("src/main/resources/"+view+"/ListItem.fxml").toURI().toURL()).load();
                ((Label)parentNode.lookup("#itemName")).setText(userBean.getName() + " " + userBean.getSurname());
                ((Label)parentNode.lookup("#itemOwner")).setText(userBean.getUsername());
                ((Label)parentNode.lookup("#itemCode")).setText(userBean.getFiscalCode());
                setPicture(userBean, view);
                setGraphic(parentNode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            setGraphic(null);
        }
    }

    private void setPicture(UserBean userBean, String view) {
        if(Objects.equals(userBean.getType(), "Trainer")){
            if (userBean.getGender() == 'm'){
                setImage("TrainerM", view);
            } else {
                setImage("TrainerF", view);
            }
        }else {
            if (userBean.getGender() == 'm'){
                setImage("AthleteM", view);
            } else {
                setImage("AthleteF", view);
            }
        }
    }

    private void setImage(String str, String view){
        ((ImageView) parentNode.lookup("#itemIcon")).setImage(new Image(new File("src/main/resources/"+view+"/images/" + str + ".png").toURI().toString()));
    }
}
