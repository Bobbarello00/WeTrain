package viewone.graphical_controllers.athletes;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import viewone.bean.CourseBean;
import viewone.bean.LessonBean;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CourseInfoGUIController implements Initializable {

    @FXML private ListView<Node> exercisesSelectedList;
    @FXML private Button mondayButton;
    @FXML private Button tuesdayButton;
    @FXML private Button wednesdayButton;
    @FXML private Button thursdayButton;
    @FXML private Button fridayButton;
    @FXML private Button saturdayButton;
    @FXML private Button sundayButton;
    @FXML private Button subscribeButton;
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

    private void setButtonColor(Button button) {
        button.setStyle("-fx-background-color: white;" +
                " -fx-text-fill: rgb(24,147,21);" +
                " -fx-border-color: rgb(24,147,21);" +
                " -fx-border-radius: 50");
    }

    private void setDay(Button button, Label label, LessonBean lessonBean) {
        label.setText("from " + lessonBean.getLessonStartTime().toString() + " to " + lessonBean.getLessonEndTime().toString());
        setButtonColor(button);
    }

    private void setScheduleLesson(List<LessonBean> lessonBeanList) {
        //TODO da testare
        for(LessonBean lessonBean: lessonBeanList){
            switch (lessonBean.getLessonDay()){
                //TODO codice duplicato
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
        ((Stage) ((ImageView)event.getSource()).getScene().getWindow()).close();
    }

    private void setValue(CourseBean courseBean) {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CourseBean courseBean = AthletesHomeGUIController.getSelectedCourse();
        if(courseBean != null) {
            setValue(courseBean);
        } else {
            System.out.println("Error in CourseInfoGUIController: courseBean == null");
        }
    }

    public void subscribeButtonAction() {
        //TODO Sottoscrizione al corso
    }
}
