package com.wetrain.wetrain.graphical_controllers;

import com.wetrain.wetrain.WeTrain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class ListPopulate {

    private ListPopulate() {}

    public static void populateList(int n, ListView<Node> nameList, boolean newItem) {
        ArrayList<Node> name = new ArrayList<>();
        for (int i = 0; i < n-1; i++) {
            try {
                name.add(FXMLLoader.load(Objects.requireNonNull(WeTrain.class.getResource("ListItem.fxml"))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(newItem){
            try {
                name.add(FXMLLoader.load(Objects.requireNonNull(WeTrain.class.getResource("ListItemNew.fxml"))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                name.add(FXMLLoader.load(Objects.requireNonNull(WeTrain.class.getResource("ListItem.fxml"))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ObservableList<Node> nameObservableList = FXCollections.observableList(name);
        nameList.setItems(nameObservableList);
    }
}
