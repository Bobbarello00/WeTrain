package engineering.manage_list.list_cell_factories;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import beans.CourseBean;

import java.io.File;
import java.io.IOException;

public class CourseListCellFactory extends ListCell<CourseBean> {
    private final boolean viewtwo;
    public CourseListCellFactory(boolean viewtwo){
        this.viewtwo = viewtwo;
    }
    private Parent parentNode = null ;
    @Override public void updateItem(CourseBean courseBean, boolean empty){
        updateCourseListWithParameters(courseBean, empty);
    }
    public void updateCourseListWithParameters(CourseBean courseBean, boolean empty) {
        super.updateItem(courseBean, empty);
        if(courseBean != null){
            try {
                String view = "viewone";
                if(viewtwo) {
                    view = "viewtwo";
                }
                if (parentNode == null)parentNode = new FXMLLoader(new File("src/main/resources/"+view+"/ListItem.fxml").toURI().toURL()).load();
                ((Label)parentNode.lookup("#itemName")).setText(courseBean.getName());
                ((Label)parentNode.lookup("#itemCode")).setText(Integer.toString(courseBean.getId()));
                ((Label)parentNode.lookup("#itemOwner")).setText(courseBean.getOwner());
                ((ImageView)parentNode.lookup("#itemIcon")).setImage(new Image(new File("src/main/resources/"+view+"/images/course.png").toURI().toString()));
                setGraphic(parentNode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            setGraphic(null);
        }
    }

}
