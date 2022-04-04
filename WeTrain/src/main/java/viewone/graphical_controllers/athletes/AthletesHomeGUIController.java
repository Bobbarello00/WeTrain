package viewone.graphical_controllers.athletes;

import controller.CourseManagementAthleteController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import model.LoggedUserSingleton;
import viewone.ListPopulate;
import viewone.WeTrain;
import viewone.bean.CourseEssentialBean;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class AthletesHomeGUIController extends HomeGUIControllerAthletes implements Initializable {
    @FXML
    private ListView<Node> coursesList;
    @FXML
    private ListView<Node> popularsList;
    @FXML
    private ListView<Node> feedList;
    @FXML
    private Text usernameText1;

    public void populateCourseList() {
        try {
            List<CourseEssentialBean> courseBeanList = CourseManagementAthleteController.getCourseList();
            ArrayList<Node> tempList = new ArrayList<>();
            for (CourseEssentialBean courseBean : courseBeanList) {
                try {
                    Node item = FXMLLoader.load(Objects.requireNonNull(WeTrain.class.getResource("ListItem.fxml")));
                    ((Label)item.lookup("#itemName")).setText(courseBean.getName());
                    ((Label)item.lookup("#itemCode")).setText(Integer.toString(courseBean.getId()));
                    tempList.add(item);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            ObservableList<Node> courseObservableList = FXCollections.observableList(tempList);
            coursesList.setItems(courseObservableList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //ListPopulate.populateList(10,coursesList);
        populateCourseList();
        ListPopulate.populateList(0,popularsList); //TODO fare query per corsi più popolari
        ListPopulate.populateList(30,feedList);
        //coursesList.getSelectionModel().getSelectedItems();
        //TODO
        coursesList.getSelectionModel().selectedItemProperty().
                addListener(new ChangeListener<Node>() {
                    @Override
                    public void changed(ObservableValue<? extends Node> observableValue, Node oldNode, Node newNode) {
                        Label label = (Label)(newNode.lookup("#itemCode"));
                        label.setText("Ciao");
                    }
                });
        usernameText1.setText(LoggedUserSingleton.getInstance().getUsername());
    }
}
