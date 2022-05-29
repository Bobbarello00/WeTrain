package viewtwo.graphical_controllers.athletes;

import controller.SubscribeToCourseController;
import engeneering.AlertGenerator;
import engeneering.manage_list.list_cell_factories.CourseListCellFactory;
import exception.DBUnreachableException;
import exception.PaymentFailedException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import viewone.bean.CourseBean;
import viewone.bean.CourseSearchBean;
import viewtwo.PageSwitchSimple;
import viewtwo.graphical_controllers.CourseInfoGUIController;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    private List<String> selectedDays = new ArrayList<>();
    private static final SubscribeToCourseController subscribeToCourseController = new SubscribeToCourseController();

    @FXML void backButtonAction() throws IOException {
        PageSwitchSimple.switchPage("Courses", "athletes");
    }

    @FXML void courseInfoButtonAction() throws IOException {
        CourseInfoGUIController controller = (CourseInfoGUIController) PageSwitchSimple.switchPage("CourseInfo", "");
        if(controller!=null){
            controller.setBackPathAndValues("FindCourse","athletes",selectedCourse);
        }
    }

    @FXML void dayButtonAction(ActionEvent event) {
        String day = ((Button)event.getSource()).getId().substring(0,2);
        if(selectedDays.contains(day)){
            selectedDays.remove(day);
        }else{
            selectedDays.add(day);
        }
    }

    @FXML void searchCourseByFilters(ActionEvent event) {
        String fitnessLevel = null;
        switch (selectedFitnessLevel){
            case 0 -> fitnessLevel = "Base";
            case 1 -> fitnessLevel = "Intermediate";
            case 2 -> fitnessLevel = "Advanced";
        }
        String name = courseNameText.getText();
        Boolean[] days = new Boolean[7];
        Arrays.fill(days, Boolean.FALSE);
        List<String> stringList = Arrays.asList("mo","tu","we","th","fr","sa","su");
        for(int i=0; i<6; i++){
            days[i] = selectedDays.contains(stringList.get(i));
        }
        try {
            List<CourseBean> courseBeanList = subscribeToCourseController.searchCourse(new CourseSearchBean(
                    name,
                    fitnessLevel,
                    days
            ));
            ObservableList<CourseBean> courseObservableList = FXCollections.observableList(courseBeanList);
            courseList.setItems(FXCollections.observableList(courseObservableList));
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSimple.logOff();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean checkIfAlreadySubscribed() throws DBUnreachableException, SQLException {
        List<CourseBean> courseBeanList = subscribeToCourseController.getLoggedAthleteCourseList();
        for(CourseBean course: courseBeanList){
            if(course.getId() == selectedCourse.getId()){
                return true;
            }
        }
        return false;
    }

    @FXML void subscribe() {
        try {
            if(!checkIfAlreadySubscribed()){
                if(AlertGenerator.newConfirmationAlert(
                        "PURCHASE CONFIRMATION",
                        "Course subscription fee is 5$",
                        "if you click ok a payment will be sent from your selected payment method")) {
                    subscribeToCourseController.subscribeToCourse(selectedCourse);
                    PageSwitchSimple.switchPage("AthletesHome", "athletes");
                }else {
                    AlertGenerator.newWarningAlert("OOPS, SOMETHING WENT WRONG!",
                            "You are already subscribed to this course.",
                            null);
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSimple.logOff();
        } catch (PaymentFailedException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
        }
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
        courseList.setCellFactory(nodeListView -> new CourseListCellFactory(true));
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