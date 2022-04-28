package viewone.list_cell_factories;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import viewone.WeTrain;
import viewone.bean.ExerciseBean;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

public class ExerciseListCellFactory extends ListCell<ExerciseBean> {
    private Parent parentNode = null ;
    @Override public void updateItem(ExerciseBean exerciseBean, boolean empty){
        updateExerciseListWithParameter(exerciseBean, empty);
    }

    private void updateExerciseListWithParameter(ExerciseBean exerciseBean, boolean empty) {
        super.updateItem(exerciseBean,empty);
        if(exerciseBean != null){
            try {
                if (parentNode == null)parentNode = new FXMLLoader(WeTrain.class.getResource("ListItem.fxml")).load();
                ((Label)parentNode.lookup("#itemName")).setText(exerciseBean.getName());
                ((Label)parentNode.lookup("#itemCode")).setText(Integer.toString(exerciseBean.getId()));
                ((Label)parentNode.lookup("#itemOwner")).setText("");
                ((ImageView)parentNode.lookup("#itemIcon")).setImage(new Image(Objects.requireNonNull(WeTrain.class.getResource("images/" + "exercise" + ".png")).toURI().toString()));
                setGraphic(parentNode);
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }else{
            setGraphic(null);
        }
    }

}

