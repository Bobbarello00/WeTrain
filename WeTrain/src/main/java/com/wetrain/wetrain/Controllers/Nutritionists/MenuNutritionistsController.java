package com.wetrain.wetrain.Controllers.Nutritionists;

import com.wetrain.wetrain.ButtonBehavior;
import com.wetrain.wetrain.MainPane;
import com.wetrain.wetrain.PageSwitchSimple;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MenuNutritionistsController {
    public static ButtonBehavior buttonBehavior = new ButtonBehavior();
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
        PageSwitchSimple.switchPage(MainPane.getInstance(),"NutritionistsHome", "Nutritionists");
        if(buttonBehavior.selectedButton != null) {
            buttonBehavior.selectedButton.setStyle(null);
            buttonBehavior.selectedButton = null;
        }
    }
    @FXML
    void manageAppointmentsButtonAction() throws IOException {
        buttonBehavior.setBehavior(manageAppointmentsButton,"ManageAppointmentsNutritionists","Nutritionists");
    }
    @FXML
    protected void yourCollaboratorButtonAction() throws IOException {
        buttonBehavior.setBehavior(yourCollaboratorButton,"YourCollaboratorNutritionists","Nutritionists");
    }
    @FXML
    void manageDietsButtonAction() throws IOException {
        buttonBehavior.setBehavior(manageDietsButton,"ManageDietsNutritionists","Nutritionists");
    }
    @FXML
    void createDietButtonAction() throws IOException {
        buttonBehavior.setBehavior(createDietButton,"NewDiet","Nutritionists");
    }
}

