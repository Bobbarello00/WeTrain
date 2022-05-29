package viewtwo.graphical_controllers.athletes;

import controller.SubscribeToCourseController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import viewone.bean.CourseBean;
import viewtwo.PageSwitchSimple;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FindCourseGUIController implements Initializable {

    @FXML private VBox courseActions;
    @FXML private ListView<CourseBean> courseList;
    @FXML private TextField courseNameText;
    @FXML private ChoiceBox<String> fitnessLevelChoiceBox;
    @FXML private RadioButton fridayRadioButton;
    @FXML private RadioButton mondayRadioButton;
    @FXML private RadioButton saturdayRadioButton;
    @FXML private RadioButton sundayRadioButton;
    @FXML private RadioButton thursdayRadioButton;
    @FXML private RadioButton tuesdayRadioButton;
    @FXML private RadioButton wednesdayRadioButton;

    private CourseBean selectedCourse;
    private int selectedFitnessLevel = 0;
    private StringBuilder selectedDays = new StringBuilder();
    private static final SubscribeToCourseController subscribeToCourseController = new SubscribeToCourseController();

    @FXML void backButtonAction() throws IOException {
        PageSwitchSimple.switchPage("Courses", "athletes");
    }

    @FXML void courseInfoButtonAction() throws IOException {
        PageSwitchSimple.switchPage("CourseInfo", "athletes");
    }

    @FXML void dayButtonAction(ActionEvent event) {
        selectedDays.append(((Button)event.getSource()).getId(), 0, 2);
        selectedDays.append(" ");
    }

    @FXML void searchCourseByFilters(ActionEvent event) {
        //TODO
        //courseList.setItems();
    }

    @FXML void subscribeButtonAction(ActionEvent event) {
        //TODO
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fitnessLevelChoiceBox.setValue("Select fitness level");
        fitnessLevelChoiceBox.setItems(FXCollections.observableArrayList("Basic","Intermediate","Advanced"));
        fitnessLevelChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldString, String newString) {
                switch (newString) {
                    case "Basic" -> selectedFitnessLevel = 0;
                    case "Intermediate" -> selectedFitnessLevel = 1;
                    case "Advanced" -> selectedFitnessLevel = 2;
                }
            }
        });
        courseList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CourseBean>() {
              @Override
              public void changed(ObservableValue<? extends CourseBean> observableValue, CourseBean oldCourse, CourseBean newCourse) {
                  if(newCourse!=null) {
                      courseActions.setDisable(false);
                      selectedCourse = newCourse;
                  }else{
                      courseActions.setDisable(true);
                  }
              }
          }
        );
    }
}