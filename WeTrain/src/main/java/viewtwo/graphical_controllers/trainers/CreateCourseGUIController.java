package viewtwo.graphical_controllers.trainers;

import controller.ManageCoursesController;
import engeneering.AlertGenerator;
import engeneering.LoggedUserSingleton;
import exception.DBUnreachableException;
import exception.invalid_data_exception.EmptyFieldsException;
import exception.invalid_data_exception.InvalidDataException;
import exception.invalid_data_exception.TimeNotInsertedException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import viewone.bean.CourseBean;
import viewone.bean.LessonBean;
import viewtwo.PageSwitchSimple;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;

public class CreateCourseGUIController implements Initializable {

    private final ObservableList<String> fitnessLevelList = FXCollections.observableArrayList(Arrays.asList("Base", "Intermediate", "Advanced"));
    private final ObservableList<String> hoursList = FXCollections.observableArrayList("00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23");
    private final ObservableList<String> minutesList = FXCollections.observableArrayList("00","05","10","15","20","25","30","35","40","45","50","55");


    @FXML private TextField courseNameText;
    @FXML private ChoiceBox<String> endHourFr;
    @FXML private ChoiceBox<String> endHourMo;
    @FXML private ChoiceBox<String> endHourSa;
    @FXML private ChoiceBox<String> endHourSu;
    @FXML private ChoiceBox<String> endHourTh;
    @FXML private ChoiceBox<String> endHourTu;
    @FXML private ChoiceBox<String> endHourWe;
    @FXML private ChoiceBox<String> endMinuteFr;
    @FXML private ChoiceBox<String> endMinuteMo;
    @FXML private ChoiceBox<String> endMinuteSa;
    @FXML private ChoiceBox<String> endMinuteSu;
    @FXML private ChoiceBox<String> endMinuteTh;
    @FXML private ChoiceBox<String> endMinuteTu;
    @FXML private ChoiceBox<String> endMinuteWe;
    @FXML private TextArea equipmentTextArea;
    @FXML private ChoiceBox<String> fitnessLevelSpinner;
    @FXML private RadioButton fridayRadioButton;
    @FXML private TextArea infoTextArea;
    @FXML private RadioButton mondayRadioButton;
    @FXML private RadioButton saturdayRadioButton;
    @FXML private ChoiceBox<String> startHourFr;
    @FXML private ChoiceBox<String> startHourMo;
    @FXML private ChoiceBox<String> startHourSa;
    @FXML private ChoiceBox<String> startHourSu;
    @FXML private ChoiceBox<String> startHourTh;
    @FXML private ChoiceBox<String> startHourTu;
    @FXML private ChoiceBox<String> startHourWe;
    @FXML private ChoiceBox<String> startMinuteFr;
    @FXML private ChoiceBox<String> startMinuteMo;
    @FXML private ChoiceBox<String> startMinuteSa;
    @FXML private HBox timeSchedulerFr;
    @FXML private HBox timeSchedulerMo;
    @FXML private ChoiceBox<String> startMinuteSu;
    @FXML private ChoiceBox<String> startMinuteTh;
    @FXML private ChoiceBox<String> startMinuteTu;
    @FXML private ChoiceBox<String> startMinuteWe;
    @FXML private RadioButton sundayRadioButton;
    @FXML private RadioButton thursdayRadioButton;
    @FXML private HBox timeSchedulerSa;
    @FXML private HBox timeSchedulerSu;
    @FXML private HBox timeSchedulerTh;
    @FXML private RadioButton tuesdayRadioButton;
    @FXML private RadioButton wednesdayRadioButton;
    @FXML private HBox timeSchedulerTu;
    @FXML private HBox timeSchedulerWe;

    private List<RadioButton> radioButtonList;
    private List<ChoiceBox<String>> startMinuteBoxList;
    private List<ChoiceBox<String>> startHourBoxList;
    private List<ChoiceBox<String>> endMinuteBoxList;
    private List<ChoiceBox<String>> endHourBoxList;
    private List<HBox> hBoxList;
    private final Boolean[] selected = new Boolean[7];
    private final ManageCoursesController manageCoursesController = new ManageCoursesController();

    @FXML void createCourseButtonAction() {
        String fitnessLevel = fitnessLevelSpinner.getValue();
        try{
            if(Objects.equals(courseNameText.getText(), "")) {
                throw new EmptyFieldsException();
            }
            CourseBean courseBean = new CourseBean(
                    courseNameText.getText(),
                    infoTextArea.getText(),
                    fitnessLevel,
                    LoggedUserSingleton.getFc(),
                    equipmentTextArea.getText());
            courseBean.setLessonBeanList(getLessonDay());
            manageCoursesController.createCourse(courseBean);
            PageSwitchSimple.switchPage("ManageCourses", "trainers");
        } catch (InvalidDataException e){
            List<String> errorStrings = e.getErrorStrings();
            AlertGenerator.newWarningAlert(
                    errorStrings.get(0),
                    errorStrings.get(1),
                    errorStrings.get(2));
        } catch (SQLException | IOException e) {
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

    private LocalTime getTime(ChoiceBox<String> minuteBox, ChoiceBox<String> hourBox) throws TimeNotInsertedException {
        String hourString = hourBox.getSelectionModel().getSelectedItem();
        String minuteString = minuteBox.getSelectionModel().getSelectedItem();
        int minute;
        if(!Objects.equals(minuteString, "min")) {
            minute = Integer.parseInt(minuteString);
        } else {
            throw new TimeNotInsertedException();
        }
        int hour;
        if(!Objects.equals(hourString, "h")) {
            hour = Integer.parseInt(hourString);
        } else {
            throw new TimeNotInsertedException();
        }
        return LocalTime.of(hour, minute);
    }

    private List<LessonBean> getLessonDay() throws TimeNotInsertedException {
        List<LessonBean> lessonBeanList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            if (Boolean.TRUE.equals(selected[i])) {
                lessonBeanList.add(new LessonBean(
                        DayOfWeek.of(i+1).name(),
                        getTime(startMinuteBoxList.get(i), startHourBoxList.get(i)),
                        getTime(endMinuteBoxList.get(i), endHourBoxList.get(i)))
                );
            }
        }
        return lessonBeanList;
    }

    @FXML void cancelButtonAction() throws IOException {
        PageSwitchSimple.switchPage("ManageCourses", "trainers");
    }

    @FXML void dayButtonAction(ActionEvent event) {
        boolean bool = ((RadioButton) event.getSource()).isSelected();
        for(int i = 0; i < 7; i++) {
            if(event.getSource() == radioButtonList.get(i)) {
                hBoxList.get(i).setVisible(bool);
                hBoxList.get(i).setDisable(!bool);
                selected[i] = bool;
            }
        }
    }

    private void setChoiceBox(ChoiceBox<String> minute, ChoiceBox<String> hour) {
        minute.setItems(minutesList);
        minute.setValue("min");
        hour.setItems(hoursList);
        hour.setValue("h");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Arrays.fill(selected, Boolean.FALSE);
        radioButtonList = Arrays.asList(
                mondayRadioButton,
                tuesdayRadioButton,
                wednesdayRadioButton,
                thursdayRadioButton,
                fridayRadioButton,
                saturdayRadioButton,
                sundayRadioButton
        );
        hBoxList = Arrays.asList(
                timeSchedulerMo,
                timeSchedulerTu,
                timeSchedulerWe,
                timeSchedulerTh,
                timeSchedulerFr,
                timeSchedulerSa,
                timeSchedulerSu
        );
        startMinuteBoxList = Arrays.asList(
                startMinuteMo,
                startMinuteTu,
                startMinuteWe,
                startMinuteTh,
                startMinuteFr,
                startMinuteSa,
                startMinuteSu
        );
        startHourBoxList = Arrays.asList(
                startHourMo,
                startHourTu,
                startHourWe,
                startHourTh,
                startHourFr,
                startHourSa,
                startHourSu
        );
        endMinuteBoxList = Arrays.asList(
                endMinuteMo,
                endMinuteTu,
                endMinuteWe,
                endMinuteTh,
                endMinuteFr,
                endMinuteSa,
                endMinuteSu
        );
        endHourBoxList = Arrays.asList(
                endHourMo,
                endHourTu,
                endHourWe,
                endHourTh,
                endHourFr,
                endHourSa,
                endHourSu
        );
        fitnessLevelSpinner.setItems(fitnessLevelList);
        for(int i = 0; i < 7; i++) {
            setChoiceBox(startMinuteBoxList.get(i), startHourBoxList.get(i));
            setChoiceBox(endMinuteBoxList.get(i), endHourBoxList.get(i));
        }
    }
}
