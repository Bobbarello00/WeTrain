package viewone.graphical_controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class FitnessLevelFilter {
    private Button selectedFitnessLevel;

    public void fitnessLevelSelection(ActionEvent event){
        if(selectedFitnessLevel!=null) {
            selectedFitnessLevel.setStyle("-fx-text-fill: rgb(24,147,21);" +
                    " -fx-background-color: white;" +
                    "-fx-background-radius: 10");
        }
        selectedFitnessLevel=(Button)event.getSource();
        selectedFitnessLevel.setStyle("-fx-text-fill: white;" +
                " -fx-background-color: rgb(24,147,21);" +
                "-fx-background-radius: 11");
    }

    public Button getSelectedFitnessLevel(){
        return selectedFitnessLevel;
    }
}
