package viewtwo.graphical_controllers.athletes;

import controller.RequestWorkoutPlanController;
import engeneering.AlertGenerator;
import exception.DBUnreachableException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import viewone.bean.ExerciseBean;
import viewone.bean.WorkoutDayBean;
import viewone.bean.WorkoutPlanBean;
import engeneering.manage_list.list_cell_factories.ExerciseListCellFactory;
import viewtwo.PageSwitchSimple;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class YourWorkoutPlanGUIController implements Initializable {

    @FXML private ListView<ExerciseBean> exerciseListView;
    @FXML private ChoiceBox<String> dayChoiceBox;
    @FXML private TextArea infoTextArea;
    @FXML private Text text;

    private WorkoutPlanBean workoutPlanBean;
    private final RequestWorkoutPlanController requestWorkoutPlanController = new RequestWorkoutPlanController();

    @FXML void backButtonAction() throws IOException {
        PageSwitchSimple.switchPage("TrainingPage", "athletes");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            workoutPlanBean = requestWorkoutPlanController.getWorkoutPlan();
            if(workoutPlanBean == null) {
                text.setVisible(true);
                infoTextArea.setVisible(false);
                dayChoiceBox.setVisible(false);
                exerciseListView.setVisible(false);
                return;
            }
            ObservableList<String> daysList = FXCollections.observableArrayList();
            for(int i = 1; i <= 7; i++) {
                daysList.add(DayOfWeek.of(i).name());
            }
            dayChoiceBox.setItems(daysList);
            dayChoiceBox.setValue("Select day");

            exerciseListView.setCellFactory(nodeListView -> new ExerciseListCellFactory(true));
            exerciseListView.getSelectionModel().selectedItemProperty().
                    addListener((observableValue, oldItem, newItem) -> {
                        if (newItem != null) {
                            infoTextArea.setText(String.format("""
                                    Name: %s
    
                                    Description:
                                    \t\t\t %s
    
                                    """, newItem.getName(), newItem.getInfo()));
                        }
                    });

            dayChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String oldString, String newString) {
                    for(WorkoutDayBean workoutDayBean: workoutPlanBean.getWorkoutDayList()) {
                        if(Objects.equals(workoutDayBean.getDay(), newString)) {
                            ObservableList<ExerciseBean> exerciseObservableList = FXCollections.observableList(workoutDayBean.getExerciseBeanList());
                            exerciseListView.setItems(FXCollections.observableList(exerciseObservableList));
                            return;
                        }
                    }
                    exerciseListView.setItems(FXCollections.observableList(new ArrayList<>()));
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DBUnreachableException e) {
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
            PageSwitchSimple.logOff();
        }
    }
}