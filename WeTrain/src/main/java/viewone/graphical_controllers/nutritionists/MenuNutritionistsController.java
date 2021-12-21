package viewone.graphical_controllers.nutritionists;

import viewone.ButtonBehavior;
import viewone.MainPane;
import viewone.PageSwitchSimple;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MenuNutritionistsController {
    private static final ButtonBehavior buttonBehavior = new ButtonBehavior();
    private static final String HOME = "nutritionists";
    @FXML
    private Button createDietButton;
    @FXML
    private Button manageAppointmentsButton;
    @FXML
    private Button manageDietsButton;
    @FXML
    private Button yourCollaboratorButton;
    @FXML
    void logoAction() throws IOException {
        PageSwitchSimple.switchPage(MainPane.getInstance(),"NutritionistsHome", HOME);
        buttonBehavior.resetSelectedButton();
    }
    @FXML
    void manageAppointmentsButtonAction() throws IOException {
        buttonBehavior.setBehavior(manageAppointmentsButton,"ManageAppointmentsNutritionists",HOME);
    }
    @FXML
    protected void yourCollaboratorButtonAction() throws IOException {
        buttonBehavior.setBehavior(yourCollaboratorButton,"YourCollaboratorNutritionists",HOME);
    }
    @FXML
    void manageDietsButtonAction() throws IOException {
        buttonBehavior.setBehavior(manageDietsButton,"ManageDietsNutritionists",HOME);
    }
    @FXML
    void createDietButtonAction() throws IOException {
        buttonBehavior.setBehavior(createDietButton,"NewDiet",HOME);
    }
}

