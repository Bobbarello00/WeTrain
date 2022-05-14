package viewone.graphical_controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.util.Objects;

public class FitnessLevelFilterGUIController {
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

    public String getSelectedFitnessLevelString(){
        if(Objects.equals(getSelectedFitnessLevel().getId(), "baseFitnessLevelButton")){
            return  "Base";
        } else if(Objects.equals(getSelectedFitnessLevel().getId(), "intermediateFitnessLevelButton")){
            return  "Intermediate";
        } else {
            return  "Advanced";
        }
    }
}
