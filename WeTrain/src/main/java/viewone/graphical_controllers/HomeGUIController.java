package viewone.graphical_controllers;

import com.mysql.cj.exceptions.CJException;
import exception.DBConnectionFailedException;
import exception.ExpiredCardException;
import exception.InvalidCardInfoException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import viewone.PageSwitchSizeChange;
import viewone.WeTrain;
import viewone.bean.UserBean;
import viewone.engeneering.LoggedUserSingleton;
import viewone.engeneering.UserInfoCarrier;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Objects;

public abstract class HomeGUIController {
    @FXML protected abstract void editButtonAction(ActionEvent event) throws IOException;
    @FXML protected Text usernameText1;
    @FXML protected ImageView userImage;
    @FXML protected Button logoutButton;

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
        } catch (DBConnectionFailedException e) {
            e.alertAndLogOff();
            return null;
        } catch (ExpiredCardException | InvalidCardInfoException e) {
            e.alert();
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setImage(String str){
        try {
            userImage.setImage(new Image(Objects.requireNonNull(WeTrain.class.getResource("images/"+str+".png")).toURI().toString()));
        } catch (URISyntaxException e) {
            //TODO
            throw new RuntimeException(e);
        }
    }

    @FXML protected void logoutButtonAction() throws IOException {
        PageSwitchSizeChange.pageSwitch(logoutButton, "WeTrainGUI", "launcher", true);
    }

    @FXML protected void closeAction(MouseEvent event){
        ((Stage) ((ImageView)event.getSource()).getScene().getWindow()).close();
    }
}
