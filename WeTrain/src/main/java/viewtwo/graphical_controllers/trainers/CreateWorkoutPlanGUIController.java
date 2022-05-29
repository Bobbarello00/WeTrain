package viewtwo.graphical_controllers.trainers;

import controller.SatisfyWorkoutRequestsController;
import engeneering.AlertGenerator;
import engeneering.manage_list.list_cell_factories.ExerciseListCellFactory;
import exception.DBUnreachableException;
import exception.invalid_data_exception.NoDayIsSelectedException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import viewone.bean.*;
import viewtwo.PageSwitchSimple;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class CreateWorkoutPlanGUIController implements Initializable {

    @FXML private ListView<ExerciseBean> exerciseList;
    @FXML private RadioButton fridayRadioButton;
    @FXML private RadioButton mondayRadioButton;
    @FXML private RadioButton saturdayRadioButton;
    @FXML private TextField searchExerciseText;
    @FXML private ListView<ExerciseBean> selectedExerciseList;
    @FXML private RadioButton sundayRadioButton;
    @FXML private RadioButton thursdayRadioButton;
    @FXML private RadioButton tuesdayRadioButton;
    @FXML private RadioButton wednesdayRadioButton;

    private List<RadioButton> radioButtonList;
    private final List<String> dayList = new ArrayList<>();
    private RequestBean selectedRequest;
    private SatisfyWorkoutRequestsController satisfyWorkoutRequestsController;

    public CreateWorkoutPlanGUIController() {}

    public void setValue(RequestBean requestBean, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController, int day) {
        try {
            this.selectedRequest = requestBean;
            this.satisfyWorkoutRequestsController = satisfyWorkoutRequestsController;
            List<ExerciseBean> exerciseBeanList = satisfyWorkoutRequestsController.getTrainerExercises();
            exerciseList.setItems(FXCollections.observableList(exerciseBeanList));
            setListener(exerciseList);
            setListener(selectedExerciseList);
            radioButtonList.get(day).fire();
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSimple.logOff();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setListener(ListView<ExerciseBean> exerciseList) {
        exerciseList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ExerciseBean>() {
            @Override
            public void changed(ObservableValue<? extends ExerciseBean> observableValue, ExerciseBean oldItem, ExerciseBean newItem) {
                try {
                    if (newItem != null) {
                        ExerciseOverviewGUIController controller = (ExerciseOverviewGUIController) PageSwitchSimple.switchPage("ExerciseOverview", "trainers");
                        if(controller != null) {
                            controller.setValue(selectedRequest, satisfyWorkoutRequestsController, new ExerciseForWorkoutPlanBean(newItem, getDay()), getIntDay());
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NoDayIsSelectedException e) {
                    List<String> errorStrings = e.getErrorStrings();
                    AlertGenerator.newWarningAlert(
                            errorStrings.get(0),
                            errorStrings.get(1),
                            errorStrings.get(2));
                }
            }
        });
    }

    private int getIntDay() {
        for(int i = 0; i < 7; i++) {
            if(radioButtonList.get(i).isSelected()) {
                return i;
            }
        }
        return 0;
    }


    @FXML void backButtonAction() throws IOException {
        PageSwitchSimple.switchPage("RequestsPage", "trainers");
    }

    @FXML void createNewExerciseAction() throws IOException {
        CreateExerciseGUIController controller = (CreateExerciseGUIController) PageSwitchSimple.switchPage("CreateExercise", "trainers");
        if(controller != null) {
            controller.setValue(selectedRequest, satisfyWorkoutRequestsController, getIntDay());
        }
    }

    @FXML void dayButtonAction(ActionEvent event) {
        try {
            if (((RadioButton) event.getSource()).isSelected()) {
                updateSelectedExerciseList();
            } else {
                emptySelectedExerciseList();
            }
        } catch (NoDayIsSelectedException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
        }
    }

    public void emptySelectedExerciseList() {
        selectedExerciseList.setItems(FXCollections.observableList(new ArrayList<>()));
    }

    public void updateSelectedExerciseList() throws NoDayIsSelectedException {
        WorkoutDayBean workoutDayBean = satisfyWorkoutRequestsController.getWorkoutDayBean(new DayBean(getDay()));
        ObservableList<ExerciseBean> exerciseBeanObservableList = FXCollections.observableList(workoutDayBean.getExerciseBeanList());
        selectedExerciseList.setItems(exerciseBeanObservableList);
    }

    private String getDay() throws NoDayIsSelectedException {
        for(int i = 0; i < 7; i++) {
            if(radioButtonList.get(i).isSelected()) {
                return dayList.get(i);
            }
        }
        throw new NoDayIsSelectedException();
    }

    @FXML void searchButtonAction() {
        try {
            List<ExerciseBean> exerciseBeanList = satisfyWorkoutRequestsController.searchExercise(new SearchBean(searchExerciseText.getText()));
            ObservableList<ExerciseBean> exerciseBeanObservableList = FXCollections.observableList(exerciseBeanList);
            exerciseList.setItems(exerciseBeanObservableList);
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSimple.logOff();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML void sendWorkoutPlanButtonAction() {
        try {
            satisfyWorkoutRequestsController.sendWorkoutPlan(selectedRequest);
            PageSwitchSimple.switchPage("RequestsPage", "trainers");
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSimple.logOff();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exerciseList.setCellFactory(nodeListView -> new ExerciseListCellFactory());
        selectedExerciseList.setCellFactory(nodeListView -> new ExerciseListCellFactory());
        radioButtonList = Arrays.asList(
                mondayRadioButton,
                tuesdayRadioButton,
                wednesdayRadioButton,
                thursdayRadioButton,
                fridayRadioButton,
                saturdayRadioButton,
                sundayRadioButton
        );
        for(int i = 1; i <= 7; i++) {
            dayList.add(DayOfWeek.of(i).name());
        }
        ToggleGroup group = new ToggleGroup();
        mondayRadioButton.setToggleGroup(group);
        tuesdayRadioButton.setToggleGroup(group);
        wednesdayRadioButton.setToggleGroup(group);
        thursdayRadioButton.setToggleGroup(group);
        fridayRadioButton.setToggleGroup(group);
        saturdayRadioButton.setToggleGroup(group);
        sundayRadioButton.setToggleGroup(group);
    }
}
