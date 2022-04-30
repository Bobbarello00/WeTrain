package viewone;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class DaysOfTheWeekButtonController {

    private Button previousButton;
    private final List<String> buttonNameList = new ArrayList<>(Arrays.asList(
            "mondayButton",
            "tuesdayButton",
            "wednesdayButton",
            "thursdayButton",
            "fridayButton",
            "saturdayButton",
            "sundayButton"
    ));

    public String dayButtonAction(ActionEvent event) {
        String sourceId = ((Node) event.getSource()).getId();
        colorShift((Button) event.getSource());
        for(int i = 0; i < 7; i++){
            if(Objects.equals(sourceId, buttonNameList.get(i))) {
                return DayOfWeek.of(i+1).name();
            }
        }
        return null;
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

    public String getDay() {
        for(int i = 0; i < 7; i++){
            if(Objects.equals(previousButton.getId(), buttonNameList.get(i))) {
                return DayOfWeek.of(i+1).name();
            }
        }
        return null;
    }
}
