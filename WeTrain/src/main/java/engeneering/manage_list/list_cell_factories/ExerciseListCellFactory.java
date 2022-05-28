package engeneering.manage_list.list_cell_factories;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import viewone.bean.ExerciseBean;

import java.io.File;
import java.io.IOException;

public class ExerciseListCellFactory extends ListCell<ExerciseBean> {
    private Parent parentNode = null ;
    @Override public void updateItem(ExerciseBean exerciseBean, boolean empty){
        updateExerciseListWithParameters(exerciseBean, empty);
    }

    private void updateExerciseListWithParameters(ExerciseBean exerciseBean, boolean empty) {
        super.updateItem(exerciseBean, empty);
        if(exerciseBean != null){
            try {
                if (parentNode == null) parentNode = new FXMLLoader(new File("src/main/resources/ListItem.fxml").toURI().toURL()).load();
                ((Label)parentNode.lookup("#itemName")).setText(exerciseBean.getName());
                ((Label)parentNode.lookup("#itemCode")).setText(Integer.toString(exerciseBean.getId()));
                ((Label)parentNode.lookup("#itemOwner")).setText("");
                ((ImageView)parentNode.lookup("#itemIcon")).setImage(new Image(new File("src/main/resources/viewone/images/exercise.png").toURI().toString()));
                setGraphic(parentNode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            setGraphic(null);
        }
    }

}

