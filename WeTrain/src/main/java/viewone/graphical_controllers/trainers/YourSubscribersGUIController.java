package viewone.graphical_controllers.trainers;

import controller.SubscribersManagementController;
import database.dao_classes.TrainerDAO;
import exception.DBConnectionFailedException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import viewone.PageSwitchSizeChange;
import viewone.bean.UserBean;
import viewone.engeneering.UserInfoCarrier;
import viewone.list_cell_factories.PersonListCellFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class YourSubscribersGUIController extends HomeGUIControllerTrainers implements Initializable {
    @FXML private Label subscribersCountLabel;
    @FXML private ListView<UserBean> subscribersList;
    @FXML private Label infoBirth;
    @FXML private Label infoEmail;
    @FXML private Label infoFiscalCode;
    @FXML private Label infoName;
    @FXML private VBox infoSubscriberBox;
    @FXML private Label infoSurname;
    @FXML private Label infoUsername;
    private boolean clicked = false;

    private final SubscribersManagementController subscribersManagementController = new SubscribersManagementController();

    @FXML void writeEmailButtonAction(ActionEvent event) throws IOException {
        PageSwitchSizeChange.pageSwitch((Button) event.getSource(),"EmailForm","",false);
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            UserInfoCarrier trainerInfo = getUserInfo();
            subscribersCountLabel.setText(String.valueOf( new TrainerDAO().getNumberOfSubscribers(trainerInfo.getFiscalCode())));
            subscribersList.setCellFactory(nodeListView -> new PersonListCellFactory());
            subscribersList.getSelectionModel().selectedItemProperty().
                    addListener(new ChangeListener<>() {
                        @Override
                        public void changed(ObservableValue<? extends UserBean> observableValue, UserBean oldItem, UserBean newItem) {
                            setInfoBox(newItem);
                        }
                    });
            ObservableList<UserBean> requestBeanObservableList = FXCollections.observableList(subscribersManagementController.getSubscriberList(trainerInfo.getFiscalCode()));
            subscribersList.setItems(FXCollections.observableList(requestBeanObservableList));
            setUserInfoTab();
        } catch (DBConnectionFailedException e) {
            e.alertAndLogOff();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void setInfoBox(UserBean selectedSubscriber) {
        if(!clicked) {
            infoSubscriberBox.setDisable(false);
            infoSubscriberBox.setVisible(true);
            clicked = true;
        }
        infoName.setText("Name: " + selectedSubscriber.getName());
        infoSurname.setText("Surname: " + selectedSubscriber.getSurname());
        infoUsername.setText("Username: " + selectedSubscriber.getUsername());
        infoBirth.setText("Birth: " + String.valueOf(selectedSubscriber.getBirth()));
        infoEmail.setText("Email: " + selectedSubscriber.getEmail());
        infoFiscalCode.setText("FiscalCode: " + selectedSubscriber.getFiscalCode());
    }
}
