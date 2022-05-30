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

    @FXML private Label courseNameLabel;
    @FXML private Label endTimeFr;
    @FXML private Label endTimeMo;
    @FXML private Label endTimeSa;
    @FXML private Label endTimeSu;
    @FXML private Label endTimeTh;
    @FXML private Label endTimeTu;
    @FXML private Label endTimeWe;
    @FXML private TextArea equipmentTextArea;
    @FXML private Label fitnessLevelLabel;
    @FXML private RadioButton fridayRadioButton;
    @FXML private TextArea infoTextArea;
    @FXML private RadioButton mondayRadioButton;
    @FXML private RadioButton saturdayRadioButton;
    @FXML private Label startTimeFr;
    @FXML private Label startTimeMo;
    @FXML private Label startTimeSa;
    @FXML private Label startTimeSu;
    @FXML private Label startTimeTh;
    @FXML private Label startTimeTu;
    @FXML private Label startTimeWe;
    @FXML private RadioButton sundayRadioButton;
    @FXML private RadioButton thursdayRadioButton;
    @FXML private HBox timeSchedulerFr;
    @FXML private HBox timeSchedulerMo;
    @FXML private HBox timeSchedulerSa;
    @FXML private HBox timeSchedulerSu;
    @FXML private HBox timeSchedulerTh;
    @FXML private HBox timeSchedulerTu;
    @FXML private HBox timeSchedulerWe;
    @FXML private Label trainerNameLabel;
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
                case ("monday") -> setDaySchedule(timeSchedulerMo, startTimeMo,
                        endTimeMo, mondayRadioButton, lessonBean);
                case ("tuesday") -> setDaySchedule(timeSchedulerTu, startTimeTu,
                        endTimeTu, tuesdayRadioButton, lessonBean);
                case ("wednesday") -> setDaySchedule(timeSchedulerWe, startTimeWe,
                       endTimeWe, wednesdayRadioButton, lessonBean);
                case ("thursday") -> setDaySchedule(timeSchedulerTh, startTimeTh,
                        endTimeTh, thursdayRadioButton, lessonBean);
                case ("friday") -> setDaySchedule(timeSchedulerFr, startTimeFr,
                        endTimeFr, fridayRadioButton, lessonBean);
                case ("saturday") -> setDaySchedule(timeSchedulerSa, startTimeSa,
                        endTimeSa, saturdayRadioButton, lessonBean);
                case ("sunday") -> setDaySchedule(timeSchedulerSu, startTimeSu,
                        endTimeSu, sundayRadioButton, lessonBean);
            }
        }
    }

    private void setDaySchedule(HBox timeScheduler, Label startLabel, Label endLabel, RadioButton radioButton, LessonBean lessonBean) {
        startLabel.setText(lessonBean.getLessonStartTime().toString());
        endLabel.setText(lessonBean.getLessonEndTime().toString());
        timeScheduler.setVisible(true);
        radioButton.selectedProperty().setValue(true);
    }

    @FXML void backButtonAction() throws IOException {
        PageSwitchSimple.switchPage(filename, path);
    }

}