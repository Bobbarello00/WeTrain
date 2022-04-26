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
        UserBean user = getLoggedUser();
        if(user == null){
            return;
        }
        usernameText1.setText(user.getUsername());
        char gender = user.getGender();
        String type = user.getType();
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

    private void setImage(String str){
        try {
            userImage.setImage(new Image(Objects.requireNonNull(WeTrain.class.getResource("images/"+str+".png")).toURI().toString()));
        } catch (URISyntaxException e) {
            //TODO
            throw new RuntimeException(e);
        }
    }

    protected UserBean getLoggedUser() {
        try{
            return Objects.requireNonNull(LoggedUserSingleton.getInstance());
        } catch (ExpiredCardException | InvalidCardInfoException e){
            e.alert();
            e.printStackTrace();
        } catch (DBConnectionFailedException | CJException e) {
            new DBConnectionFailedException().alert();
            logoutButton.fire();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @FXML protected void logoutButtonAction(ActionEvent event) throws IOException {
        PageSwitchSizeChange.pageSwitch((Button) event.getSource(), "WeTrainGUI", "launcher", true);
    }

    @FXML protected void closeAction(MouseEvent event){
        ((Stage) ((ImageView)event.getSource()).getScene().getWindow()).close();
    }
}
