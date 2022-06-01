package viewone.graphical_controllers.trainers;

import controllers.SubscribersManagementController;
import engeneering.AlertGenerator;
import engeneering.manage_list.list_cell_factories.PersonListCellFactory;
import exceptions.DBUnreachableException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import viewone.PageSwitchSizeChange;
import beans.UserBean;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            subscribersCountLabel.setText(String.valueOf(subscribersManagementController.getSubscribersNumber().getSubscribersNumber()));
            subscribersList.setCellFactory(nodeListView -> new PersonListCellFactory(false));
            subscribersList.getSelectionModel().selectedItemProperty().
                    addListener(new ChangeListener<>() {
                        @Override
                        public void changed(ObservableValue<? extends UserBean> observableValue, UserBean oldItem, UserBean newItem) {
                            setInfoBox(newItem);
                        }
                    });
            ObservableList<UserBean> requestBeanObservableList = FXCollections.observableList(subscribersManagementController.getSubscriberList());
            subscribersList.setItems(requestBeanObservableList);
            setUserInfoTab();
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSizeChange.logOff();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setInfoBox(UserBean selectedSubscriber) {
        if(!clicked) {
            infoSubscriberBox.setDisable(false);
            infoSubscriberBox.setVisible(true);
            clicked = true;
        }
        infoName.setText(" Name: " + selectedSubscriber.getName());
        infoSurname.setText(" Surname: " + selectedSubscriber.getSurname());
        infoUsername.setText(" Username: " + selectedSubscriber.getUsername());
        infoBirth.setText(" Birth: " + selectedSubscriber.getBirth());
        infoEmail.setText(" Email: " + selectedSubscriber.getEmail());
        infoFiscalCode.setText(" FiscalCode: " + selectedSubscriber.getFiscalCode());
    }
}
