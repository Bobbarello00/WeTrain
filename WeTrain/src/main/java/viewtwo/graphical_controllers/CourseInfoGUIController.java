package viewtwo.graphical_controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import viewone.bean.CourseBean;
import viewone.bean.LessonBean;
import viewtwo.PageSwitchSimple;

import java.io.IOException;
import java.util.List;

public class CourseInfoGUIController {

    @FXML private Label startHourWe;
    @FXML private Label courseNameLabel;
    @FXML private Label trainerNameLabel;
    @FXML private Label endHourFr;
    @FXML private Label endHourMo;
    @FXML private Label endHourSa;
    @FXML private Label endHourSu;
    @FXML private Label endHourTh;
    @FXML private Label endHourTu;
    @FXML private Label endHourWe;
    @FXML private Label endMinuteFr;
    @FXML private Label endMinuteMo;
    @FXML private Label endMinuteSa;
    @FXML private Label endMinuteSu;
    @FXML private Label endMinuteTh;
    @FXML private Label endMinuteTu;
    @FXML private Label endMinuteWe;
    @FXML private TextArea equipmentTextArea;
    @FXML private Label fitnessLevelLabel;
    @FXML private RadioButton fridayRadioButton;
    @FXML private TextArea infoTextArea;
    @FXML private RadioButton mondayRadioButton;
    @FXML private RadioButton saturdayRadioButton;
    @FXML private Label startHourFr;
    @FXML private Label startHourMo;
    @FXML private Label startHourSa;
    @FXML private Label startHourSu;
    @FXML private Label startHourTh;
    @FXML private Label startHourTu;
    @FXML private Label startMinuteFr;
    @FXML private Label startMinuteMo;
    @FXML private Label startMinuteSa;
    @FXML private Label startMinuteSu;
    @FXML private Label startMinuteTh;
    @FXML private Label startMinuteTu;
    @FXML private Label startMinuteWe;
    @FXML private RadioButton sundayRadioButton;
    @FXML private RadioButton thursdayRadioButton;
    @FXML private HBox timeSchedulerFr;
    @FXML private HBox timeSchedulerMo;
    @FXML private HBox timeSchedulerSa;
    @FXML private HBox timeSchedulerSu;
    @FXML private HBox timeSchedulerTh;
    @FXML private HBox timeSchedulerTu;
    @FXML private HBox timeSchedulerWe;
    @FXML private RadioButton tuesdayRadioButton;
    @FXML private RadioButton wednesdayRadioButton;

    private String filename;
    private String path;

    public void setBackPathAndValues(String filename, String pathString, CourseBean courseBean) {
        this.filename = filename;
        path = pathString;
        courseNameLabel.setText(courseBean.getName());
        trainerNameLabel.setText(courseBean.getOwner());
        infoTextArea.setText(courseBean.getDescription());
        equipmentTextArea.setText(courseBean.getEquipment());
        fitnessLevelLabel.setText(courseBean.getFitnessLevel());
        if(courseBean.getLessonBeanList() != null) {
            setScheduleLesson(courseBean.getLessonBeanList());
        }
    }

    private void setScheduleLesson(List<LessonBean> lessonBeanList) {
        for(LessonBean lessonBean: lessonBeanList){
            switch (lessonBean.getLessonDay().toLowerCase()){
                case ("monday") -> setDaySchedule(timeSchedulerMo, startHourMo, startMinuteMo,
                        endHourMo, endMinuteMo, mondayRadioButton, lessonBean);
                case ("tuesday") -> setDaySchedule(timeSchedulerTu, startHourTu, startMinuteTu,
                        endHourTu, endMinuteTu, tuesdayRadioButton, lessonBean);
                case ("wednesday") -> setDaySchedule(timeSchedulerWe, startHourWe, startMinuteWe,
                        endHourWe, endMinuteWe, wednesdayRadioButton, lessonBean);
                case ("thursday") -> setDaySchedule(timeSchedulerTh, startHourTh, startMinuteTh,
                        endHourTh, endMinuteTh, thursdayRadioButton, lessonBean);
                case ("friday") -> setDaySchedule(timeSchedulerFr, startHourFr, startMinuteFr,
                        endHourFr, endMinuteFr, fridayRadioButton, lessonBean);
                case ("saturday") -> setDaySchedule(timeSchedulerSa, startHourSa, startMinuteSa,
                        endHourSa, endMinuteSa, saturdayRadioButton, lessonBean);
                case ("sunday") -> setDaySchedule(timeSchedulerSu, startHourSu, startMinuteSu,
                        endHourSu, endMinuteSu, sundayRadioButton, lessonBean);
            }
        }
    }

    private void setDaySchedule(HBox timeScheduler, Label startHour, Label startMinute, Label endHour, Label endMinute,RadioButton radioButton, LessonBean lessonBean) {
        startHour.setText(String.valueOf(lessonBean.getLessonStartTime().getHour()));
        startMinute.setText(String.valueOf(lessonBean.getLessonStartTime().getMinute()));
        endHour.setText(String.valueOf(lessonBean.getLessonEndTime().getHour()));
        endMinute.setText(String.valueOf(lessonBean.getLessonEndTime().getMinute()));
        timeScheduler.setVisible(true);
        radioButton.selectedProperty().setValue(true);
    }

    @FXML void backButtonAction() throws IOException {
        PageSwitchSimple.switchPage(filename, path);
    }

}