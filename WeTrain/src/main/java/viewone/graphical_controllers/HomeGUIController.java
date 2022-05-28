package viewone.graphical_controllers;

import controller.NotificationsController;
import exception.DBUnreachableException;
import exception.UserNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import viewone.PageSwitchSizeChange;
import viewone.WeTrain;
import viewone.bean.NotificationBean;
import engeneering.AlertGenerator;
import viewone.LoggedUserSingleton;
import engeneering.UserInfoCarrier;
import engeneering.manage_list.ManageNotificationList;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public abstract class HomeGUIController {

    @FXML protected Text usernameText1;
    @FXML protected ImageView userImage;
    @FXML protected Button logoutButton;
    @FXML protected ListView<NotificationBean> notificationList;
    protected final NotificationsController notificationsController = new NotificationsController();

    @FXML protected void updateNotificationList() throws SQLException, DBUnreachableException {
        try {
            List<NotificationBean> notificationBeanList = notificationsController.getMyNotification();
            ManageNotificationList.updateList(notificationList, Objects.requireNonNull(notificationBeanList));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML protected abstract void editButtonAction(ActionEvent event) throws IOException;

    protected void setUserInfoTab() {
        UserInfoCarrier user = getUserInfo();
        if(user == null){
            return;
        }
        usernameText1.setText(user.getUsername());
        char gender = user.getGender();
        String type = user.getUserType();
        if(Objects.equals(type, "Trainer")){
            if(gender == 'm') {
                setImage("TrainerM");
            }else{
                setImage("TrainerF");
            }
        }else{
            if(gender == 'm') {
                setImage("AthleteM");
            }else{
                setImage("AthleteF");
            }
        }

    }

    protected UserInfoCarrier getUserInfo(){
        try {
            return LoggedUserSingleton.getUserInfo();
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSizeChange.logOff();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void setImage(String str){
        try {
            userImage.setImage(new Image(Objects.requireNonNull(WeTrain.class.getResource("images/"+str+".png")).toURI().toString()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @FXML protected void logoutButtonAction() throws IOException {
        PageSwitchSizeChange.pageSwitch(logoutButton, "WeTrainGUI", "launcher", true);
    }

    @FXML protected void closeAction(MouseEvent event){
        ((Stage) ((ImageView)event.getSource()).getScene().getWindow()).close();
    }
}
