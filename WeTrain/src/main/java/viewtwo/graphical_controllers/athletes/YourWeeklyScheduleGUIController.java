package viewtwo.graphical_controllers.athletes;

import controller.RequestWorkoutPlanController;
import controller.SubscribeToCourseController;
import engeneering.AlertGenerator;
import exception.DBUnreachableException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import viewone.PageSwitchSizeChange;
import viewone.bean.*;
import viewtwo.PageSwitchSimple;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class YourWeeklyScheduleGUIController implements Initializable {

    @FXML private RadioButton fridayRadioButton;
    @FXML private RadioButton mondayRadioButton;
    @FXML private RadioButton saturdayRadioButton;
    @FXML private TextArea scheduleTextArea;
    @FXML private RadioButton sundayRadioButton;
    @FXML private RadioButton thursdayRadioButton;
    @FXML private RadioButton tuesdayRadioButton;
    @FXML private RadioButton wednesdayRadioButton;

    private List<CourseBean> courseBeanList;
    private WorkoutPlanBean workoutPlanBean;
    private List<RadioButton> radioButtonList;

    private final SubscribeToCourseController subscribeToCourseController = new SubscribeToCourseController();
    private final RequestWorkoutPlanController requestWorkoutPlanController = new RequestWorkoutPlanController();

    @FXML void backButtonAction() throws IOException {
        PageSwitchSimple.switchPage("TrainingPage", "athletes");
    }

    @FXML void dayButtonAction(ActionEvent event) {
        if(courseBeanList == null && workoutPlanBean == null) {
            try {
                courseBeanList = subscribeToCourseController.getLoggedAthleteCourseList();
                workoutPlanBean = requestWorkoutPlanController.getWorkoutPlan();
            } catch (DBUnreachableException e) {
                List<String> errorStrings = e.getErrorStrings();
                AlertGenerator.newWarningAlert(
                        errorStrings.get(0),
                        errorStrings.get(1),
                        errorStrings.get(2));
                PageSwitchSizeChange.logOff();
                return;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        StringBuilder infoText = getStringBuilder(event);
        scheduleTextArea.setText(infoText.toString());
    }

    private StringBuilder getStringBuilder(ActionEvent event) {
        int day = getDay(event);
        StringBuilder builder = new StringBuilder();
        builder.append("You have this course lesson:\n");
        for(CourseBean course: courseBeanList){
            for(LessonBean lesson: course.getLessonBeanList()){
                if(Objects.equals(lesson.getLessonDay(), DayOfWeek.of(day).name())) {
                    builder.append("-").append(course.getName()).append(" ").append(lesson.getLessonStartTime()).append("/").append(lesson.getLessonEndTime()).append("\n");
                }
            }
        }
        for(WorkoutDayBean workoutDayBean: workoutPlanBean.getWorkoutDayList()){
            if(Objects.equals(workoutDayBean.getDay(), DayOfWeek.of(day).name())){
                builder.append("You have this exercise in your workout plan:\n");
                for(ExerciseBean exercise: workoutDayBean.getExerciseBeanList()){
                    builder.append("-").append(exercise.getName()).append("\n");
                }
            }
        }
        return builder;
    }

    private int getDay(ActionEvent event) {
        for(int i = 0; i < 7; i++) {
            if(radioButtonList.get(i) == event.getSource()) {
                return i + 1;
            }
        }
        return 0;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup group = new ToggleGroup();
        radioButtonList = Arrays.asList(
                mondayRadioButton,
                tuesdayRadioButton,
                wednesdayRadioButton,
                thursdayRadioButton,
                fridayRadioButton,
                saturdayRadioButton,
                sundayRadioButton
        );
        for(RadioButton radioButton: radioButtonList) {
            radioButton.setToggleGroup(group);
        }
    }
}
