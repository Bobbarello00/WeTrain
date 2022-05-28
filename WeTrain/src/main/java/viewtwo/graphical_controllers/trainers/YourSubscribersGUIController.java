package viewtwo.graphical_controllers.trainers;

import controller.SubscribersManagementController;
import engeneering.AlertGenerator;
import engeneering.manage_list.list_cell_factories.PersonListCellFactory;
import exception.DBUnreachableException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import viewone.bean.UserBean;
import viewtwo.PageSwitchSimple;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class YourSubscribersGUIController implements Initializable {

    @FXML private Pane masterDataPane;
    @FXML private TextArea masterDataTextArea;
    @FXML private ListView<UserBean> subscriberList;

    private UserBean selectedSubscriber;
    private final SubscribersManagementController subscribersManagementController = new SubscribersManagementController();

    @FXML void backButtonAction() throws IOException {
        PageSwitchSimple.switchPage("TrainersHome", "trainers");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            subscriberList.setCellFactory(nodeListView -> new PersonListCellFactory());
            subscriberList.getSelectionModel().selectedItemProperty().
                    addListener(new ChangeListener<>() {
                        @Override
                        public void changed(ObservableValue<? extends UserBean> observableValue, UserBean oldItem, UserBean newItem) {
                            selectedSubscriber = newItem;
                            masterDataPane.setDisable(false);
                            masterDataPane.setVisible(true);
                            masterDataTextArea.setText(String.format(
                                    """
                                            Name: %s
                                            
                                            Surname: %s
                                            
                                            Username: %s
                                            
                                            Birth: %s
                                            
                                            Email: %s
                                            
                                            FC: %s
                                            """,
                                    selectedSubscriber.getName(),
                                    selectedSubscriber.getSurname(),
                                    selectedSubscriber.getUsername(),
                                    selectedSubscriber.getBirth().toString(),
                                    selectedSubscriber.getEmail(),
                                    selectedSubscriber.getFiscalCode())
                            );
                        }
                    });
            subscriberList.setItems(FXCollections.observableList(subscribersManagementController.getSubscriberList()));
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
