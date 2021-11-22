package com.wetrain.wetrain;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class ManageRequestsTrainersController implements Initializable {
    @FXML
    private ImageView logo;
    @FXML
    private Button logoutButt;
    @FXML
    private BorderPane mainPane;
    @FXML
    private Button manageLessonsButt;
    @FXML
    private ListView requestsList;
    @FXML
    private Button createCourseButt;
    @FXML
    private Button createWorkoutButt;
    @FXML
    private Button editButt;
    @FXML
    private Button manageRequestsButt;
    @FXML
    void logoutButtonAction() throws IOException {PageSwitchSizeChange.pageSwitch(logoutButt, "WeTrainGUI");}
    @FXML
    void manageLessonsButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("ManageLessonsTrainers");
        mainPane.setCenter(view);
    }
    @FXML
    void createCourseButtonAction() {System.out.println("Create New Course");}
    @FXML
    void createWorkoutButtonAction() {System.out.println("Create New Workout");}
    @FXML
    void manageRequestsButtonAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("TrainersHome");
        mainPane.setCenter(view);
    }
    @FXML
    void editButtonAction() {System.out.println("Edit");}
    @FXML
    void logoAction() throws IOException {
        PageSwitchSimple loader = new PageSwitchSimple();
        Pane view = loader.getPage("TrainersHome");
        mainPane.setCenter(view);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int m=10;
        ArrayList<Node> requests = new ArrayList<Node>();
        for(int i=0;i<m-1;i++){
            try {
                requests.add(FXMLLoader.load(Objects.requireNonNull(WeTrain.class.getResource("ListItem.fxml"))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            requests.add(FXMLLoader.load(Objects.requireNonNull(WeTrain.class.getResource("ListItemNew.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObservableList<Node> requestsObservableList = FXCollections.observableList(requests);
        requestsList.setItems(requestsObservableList);
    }
}
