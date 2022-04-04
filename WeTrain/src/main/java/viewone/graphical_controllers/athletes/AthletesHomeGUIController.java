package viewone.graphical_controllers.athletes;

import controller.CourseManagementAthleteController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import model.LoggedUserSingleton;
import viewone.bean.CourseEssentialBean;
import viewone.listCellFactories.CourseListCellFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AthletesHomeGUIController extends HomeGUIControllerAthletes implements Initializable {
    @FXML
    private ListView<CourseEssentialBean> courseList;
    @FXML
    private ListView<Node> popularList;
    @FXML
    private ListView<Node> feedList;
    @FXML
    private Text usernameText1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*ListPopulate.populateList(10,courseList);
        populateCourseList();
        ListPopulate.populateList(0,popularList);
        ListPopulate.populateList(30,feedList);
        //courseList.getSelectionModel().getSelectedItems();
        */
        //TODO fare query per corsi più popolari
        courseList.setCellFactory(nodeListView -> new CourseListCellFactory());
        try {
            ObservableList<CourseEssentialBean> courseObservableList = FXCollections.observableList(CourseManagementAthleteController.getCourseList());
            courseList.setItems(FXCollections.observableList(courseObservableList));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*TODO
        courseList.getSelectionModel().selectedItemProperty().???????????????;
        addListener(new ChangeListener<Node>() {
            @Override
            public void changed(ObservableValue<? extends Node> observableValue, Node oldNode, Node newNode) {
                Label label = (Label)(newNode.lookup("#itemCode"));
                label.setText("Ciao");
            }
        });
        */
        usernameText1.setText(LoggedUserSingleton.getInstance().getUsername());
    }
}
