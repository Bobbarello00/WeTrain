package com.wetrain.wetrain.Controllers.Nutritionist;

import com.wetrain.wetrain.Controllers.PageSwitchSimple;
import com.wetrain.wetrain.PageSwitchSizeChange;
import com.wetrain.wetrain.WeTrain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class ManageDietsNutritionistsController implements Initializable{
    @FXML
    private Button createDietButt;
    @FXML
    private Button editButt;
    @FXML
    private Button logoutButt;
    @FXML
    private ListView dietsList;
    @FXML
    private BorderPane mainPane;
    @FXML
    private Button manageAppointmentsButt;
    @FXML
    private Button manageDietsButt;
    @FXML
    private Button manageRequestsButt;
    @FXML
    void createDietButtonAction() {

    }
    @FXML
    void editButtonAction() {

    }
    @FXML
    void logoAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("NutritionistsHome", "Nutritionists");
        mainPane.setCenter(view);
    }
    @FXML
    void logoutButtonAction() throws IOException {
        PageSwitchSizeChange.pageSwitch(logoutButt, "Launcher/WeTrainGUI");}
    @FXML
    void manageAppointmentsButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("ManageAppointmentsNutritionists", "Nutritionists");
        mainPane.setCenter(view);
    }
    @FXML
    void manageDietsButtonAction()throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("NutritionistsHome", "Nutritionists");
        mainPane.setCenter(view);
    }
    @FXML
    void manageRequestsButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("ManageRequestsNutritionists", "Nutritionists");
        mainPane.setCenter(view);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int n = 10;
        ArrayList<Node> diets = new ArrayList<Node>();
        for (int i = 0; i < n-1; i++) {
            try {
                diets.add(FXMLLoader.load(Objects.requireNonNull(WeTrain.class.getResource("ListItem.fxml"))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            diets.add(FXMLLoader.load(Objects.requireNonNull(WeTrain.class.getResource("ListItemNew.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObservableList<Node> dietsObservableList = FXCollections.observableList(diets);
        dietsList.setItems(dietsObservableList);
    }
}
