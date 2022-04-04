package viewone.listCellFactories;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import viewone.WeTrain;
import viewone.bean.CourseEssentialBean;

import java.io.IOException;

public class CourseListCellFactory extends ListCell<CourseEssentialBean> {
    private Parent parentNode = null ;

    @Override
    public void updateItem(CourseEssentialBean courseBean, boolean empty){
        super.updateItem(courseBean, empty);
        if(courseBean != null){
                try {
                    if (parentNode == null)parentNode = new FXMLLoader(WeTrain.class.getResource("ListItem.fxml")).load();
                    ((Label)parentNode.lookup("#itemName")).setText(courseBean.getName());
                    ((Label)parentNode.lookup("#itemCode")).setText(Integer.toString(courseBean.getId()));
                    ((Label)parentNode.lookup("#itemOwner")).setText("\uD83D\uDC68 "+courseBean.getOwner());
                    setGraphic(parentNode);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }else{
            setGraphic(null);
        }
    }

}
