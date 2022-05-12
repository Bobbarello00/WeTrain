package viewone.graphical_controllers;

import controller.CourseManagementAthleteController;
import controller.JoinLessonController;
import exception.BrowsingNotSupportedException;
import exception.DBUnreachableException;
import exception.ImATrainerException;
import exception.UrlNotInsertedYetException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import viewone.MainPane;
import viewone.PageSwitchSimple;
import viewone.PageSwitchSizeChange;
import viewone.bean.CourseBean;
import viewone.bean.IdBean;
import viewone.bean.LessonBean;
import viewone.engeneering.AlertFactory;
import viewone.graphical_controllers.trainers.CommunicationFormGUIController;
import viewone.graphical_controllers.trainers.NewCourseGUIController;
import viewone.graphical_controllers.trainers.StartLessonGUIController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class CourseOverviewGUIController {

    @FXML private Button mondayButton;
    @FXML private Button tuesdayButton;
    @FXML private Button wednesdayButton;
    @FXML private Button thursdayButton;
    @FXML private Button fridayButton;
    @FXML private Button saturdayButton;
    @FXML private Button sundayButton;
    @FXML private Label courseNameText;
    @FXML private Label infoLabel;
    @FXML private Label equipmentLabel;
    @FXML private Button baseFitnessLevelButton;
    @FXML private Button intermediateFitnessLevelButton;
    @FXML private Button advancedFitnessLevelButton;
    @FXML private Label trainerNameText;
    @FXML private Label mondayTimeText;
    @FXML private Label tuesdayTimeText;
    @FXML private Label wednesdayTimeText;
    @FXML private Label thursdayTimeText;
    @FXML private Label fridayTimeText;
    @FXML private Label saturdayTimeText;
    @FXML private Label sundayTimeText;
    @FXML private Button subscribeButton;
    @FXML private Button modifyButton;
    @FXML private Button sendCommunicationButton;
    @FXML private Pane startLessonPane;
    @FXML private Text lessonText;
    private CourseBean courseBean;
    private boolean subscribed = false;
    private boolean isTrainer = false;

    private final CourseManagementAthleteController courseManagementAthleteController = new CourseManagementAthleteController();
    private final JoinLessonController joinLessonController = new JoinLessonController();

    private void setButtonColor(Button button) {
        button.setStyle("-fx-background-color: white;" +
                " -fx-text-fill: rgb(24,147,21);" +
                " -fx-border-color: rgb(24,147,21);" +
                " -fx-border-radius: 50");
    }

    private void setDay(Button button, Label label, LessonBean lessonBean) {
        label.setText("from " + lessonBean.getLessonStartTime().toString().substring(0, 5) + " to " + lessonBean.getLessonEndTime().toString().substring(0, 5));
        setButtonColor(button);
    }

    private void setScheduleLesson(List<LessonBean> lessonBeanList) {
        for(LessonBean lessonBean: lessonBeanList){
            switch (lessonBean.getLessonDay().toLowerCase()){
                case ("monday") -> setDay(mondayButton, mondayTimeText, lessonBean);
                case ("tuesday") -> setDay(tuesdayButton, tuesdayTimeText, lessonBean);
                case ("wednesday") -> setDay(wednesdayButton, wednesdayTimeText, lessonBean);
                case ("thursday") -> setDay(thursdayButton, thursdayTimeText, lessonBean);
                case ("friday") -> setDay(fridayButton, fridayTimeText, lessonBean);
                case ("saturday") -> setDay(saturdayButton, saturdayTimeText, lessonBean);
                case ("sunday") -> setDay(sundayButton, sundayTimeText, lessonBean);
            }
        }
    }

    public void setFitnessLevel(Button button) {
        button.setStyle("-fx-text-fill: white;" +
                " -fx-background-color: rgb(24,147,21);" +
                "-fx-background-radius: 11");
    }

    @FXML protected void closeAction(MouseEvent event){
        close(((Stage) ((ImageView) event.getSource()).getScene().getWindow()));
    }

    private void close(Stage stage) {
        stage.close();
        MainPane.getInstance().setDisable(false);
    }

    public void setValues(CourseBean courseBean) throws SQLException, IOException {
        try {
            if(courseManagementAthleteController.checkSubscription(courseBean)){
                startLessonPane.setDisable(false);
                startLessonPane.setVisible(true);
                subscribeButton.setStyle("-fx-background-color:  rgb(200, 0, 0)");
                subscribeButton.setText("Unsubscribe");
                subscribed = true;
            }
            lessonText.setText("Join Lesson");
        } catch (DBUnreachableException e) {
            e.alertAndLogOff();
            ((Stage) subscribeButton.getScene().getWindow()).close();
        } catch (ImATrainerException e) {
            isTrainer = true;
            startLessonPane.setDisable(false);
            startLessonPane.setVisible(true);
            setVisibile(subscribeButton, false);
            setVisibile(modifyButton, true);
            setVisibile(sendCommunicationButton, true);
        }
        this.courseBean = courseBean;
        courseNameText.setText(courseBean.getName());
        trainerNameText.setText(courseBean.getOwner());
        infoLabel.setText(courseBean.getDescription());
        equipmentLabel.setText(courseBean.getEquipment());
        switch (courseBean.getFitnessLevel()){
            case ("Base") -> setFitnessLevel(baseFitnessLevelButton);
            case ("Intermediate") -> setFitnessLevel(intermediateFitnessLevelButton);
            case ("Advanced") -> setFitnessLevel(advancedFitnessLevelButton);
        }
        if(courseBean.getLessonBeanList() != null) {
            setScheduleLesson(courseBean.getLessonBeanList());
        }
    }

    private void setVisibile(Button button, boolean bool) {
        button.setVisible(bool);
        button.setDisable(!bool);
    }

    @FXML public void sendCommunicationButtonAction(ActionEvent event) {
        try {
            ((Stage) subscribeButton.getScene().getWindow()).close();
            CommunicationFormGUIController communicationFormGUIController =
                    (CommunicationFormGUIController) PageSwitchSizeChange.
                            pageSwitch(((Button) event.getSource()), "CommunicationForm", "trainers", false);
            communicationFormGUIController.setCourseBean(courseBean);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML private void meetAction(MouseEvent event) throws IOException {
        if(isTrainer) {
            closeAction(event);
            StartLessonGUIController startLessonGUIController = (StartLessonGUIController) PageSwitchSizeChange.pageSwitch((Stage) MainPane.getInstance().getScene().getWindow(),
                    "StartLesson",
                    "trainers",
                    false);
            startLessonGUIController.setCourse(courseBean);
        }else{
            try {
                joinLessonController.joinLesson(new IdBean(courseBean.getId()));
            } catch (UrlNotInsertedYetException e) {
                e.alert();
            } catch (URISyntaxException e) {
                AlertFactory.newWarningAlert("EXCEPTION!",
                        "Url not working",
                        "The url inserted by the trainer is incorrect or not working anymore.");
            } catch (BrowsingNotSupportedException e) {
                e.alert();
            }
        }
    }

    @FXML public void modifyButtonAction(ActionEvent event) {
        try {
            ((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
            MainPane.getInstance().setDisable(false);
            NewCourseGUIController newCourseGUIController =
                    (NewCourseGUIController) PageSwitchSimple.switchPage(MainPane.getInstance(), "NewCourse", "trainers");
            if(newCourseGUIController != null) {
                newCourseGUIController.setValue(courseBean);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML public void subscribeButtonAction(ActionEvent event) {
        try {
            if(courseBean != null) {
                if (!subscribed) {
                    courseManagementAthleteController.subscribeToACourse(courseBean);
                } else {
                    courseManagementAthleteController.unsubscribeFromACourse(courseBean);
                }
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            AlertFactory.newWarningAlert("OOPS, SOMETHING WENT WRONG!",
                    "You are already subscribed to this course.",
                    null);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DBUnreachableException e) {
            e.alert();
        }
        ((Stage) ((Button) event.getSource()).getScene().getWindow()).close();
        MainPane.getInstance().setDisable(false);
    }
}
