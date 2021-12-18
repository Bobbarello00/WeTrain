package com.wetrain.wetrain;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class DaysOfTheWeekController {

    private Button previousButton;

    public void dayButtonAction(ActionEvent event) {
        String sourceId = ((Node) event.getSource()).getId();
        colorShift((Button) event.getSource());
        switch(sourceId){
            case "mondayButton" -> System.out.println("monday");
            case "tuesdayButton" -> System.out.println("tuesday");
            case "wednesdayButton" -> System.out.println("wednesday");
            case "thursdayButton" -> System.out.println("thursday");
            case "fridayButton" -> System.out.println("friday");
            case "saturdayButton" -> System.out.println("saturday");
            case "sundayButton" -> System.out.println("sunday");
        }
    }

    private void colorShift(Button button){
        if(previousButton!=null){
            previousButton.setStyle(null);
        }
        button.setStyle("-fx-background-color: white;" +
                "-fx-border-color:  rgb(24, 147, 21);" +
                "-fx-border-radius: 50;" +
                "-fx-text-fill: rgb(24, 147, 21)");
        previousButton = button;
    }
}
