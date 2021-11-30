package com.wetrain.wetrain;

import javafx.event.ActionEvent;
import javafx.scene.Node;

public class DaysOfTheWeekController {


    public static void dayButtonAction(ActionEvent event) {
        String sourceId = ((Node) event.getSource()).getId();
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
}
