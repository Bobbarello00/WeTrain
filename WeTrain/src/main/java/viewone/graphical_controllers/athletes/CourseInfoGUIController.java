package viewone.graphical_controllers.athletes;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import viewone.bean.CourseBean;
import viewone.bean.LessonBean;
import viewone.graphical_controllers.FitnessLevelFilter;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class CourseInfoGUIController implements Initializable {
    public Boolean[] toggled = new Boolean[7];
    private final FitnessLevelFilter fitnessLevelFilter= new FitnessLevelFilter();
    private CourseBean courseBean;
    @FXML private ListView<Node> exercisesSelectedList;
    @FXML private Button mondayButton;
    @FXML private Button tuesdayButton;
    @FXML private Button wednesdayButton;
    @FXML private Button thursdayButton;
    @FXML private Button fridayButton;
    @FXML private Button saturdayButton;
    @FXML private Button sundayButton;
    @FXML private Button createButton;
    @FXML private TextField courseNameText;
    @FXML private Label infoLabel;
    @FXML private Label equipmentLabel;
    @FXML private Button baseFitnessLevelButton;
    @FXML private Button intermediateFitnessLevelButton;
    @FXML private Button advancedFitnessLevelButton;
    @FXML private TextField trainerNameText;
    @FXML private Label mondayTimeText;
    @FXML private Label tuesdayTimeText;
    @FXML private Label wednesdayTimeText;
    @FXML private Label thursdayTimeText;
    @FXML private Label fridayTimeText;
    @FXML private Label saturdayTimeText;
    @FXML private Label sundayTimeText;

    private void setScheduleLesson(List<LessonBean> lessonBeanList) {
        Arrays.fill(toggled, Boolean.FALSE);
        //TODO da testare
        for(LessonBean lessonBean: lessonBeanList){
            switch (lessonBean.getLessonDay()){
                case ("monday") -> mondayTimeText.setText("from " + lessonBean.getLessonStartTime().toString() + " to " + lessonBean.getLessonEndTime().toString());
                case ("tuesday") -> tuesdayTimeText.setText("from " + lessonBean.getLessonStartTime().toString() + " to " + lessonBean.getLessonEndTime().toString());
                case ("wednesday") -> wednesdayTimeText.setText("from " + lessonBean.getLessonStartTime().toString() + " to " + lessonBean.getLessonEndTime().toString());
                case ("thursday") -> thursdayTimeText.setText("from " + lessonBean.getLessonStartTime().toString() + " to " + lessonBean.getLessonEndTime().toString());
                case ("friday") -> fridayTimeText.setText("from " + lessonBean.getLessonStartTime().toString() + " to " + lessonBean.getLessonEndTime().toString());
                case ("saturday") -> saturdayTimeText.setText("from " + lessonBean.getLessonStartTime().toString() + " to " + lessonBean.getLessonEndTime().toString());
                case ("sunday") -> sundayTimeText.setText("from " + lessonBean.getLessonStartTime().toString() + " to " + lessonBean.getLessonEndTime().toString());
            }
        }
    }

    public void setFitnessLevel(Button button) {
        button.setStyle("-fx-text-fill: white;" +
                " -fx-background-color: rgb(24,147,21);" +
                "-fx-background-radius: 11");
    }

    public void setValue(CourseBean bean) {
        courseBean = bean;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(courseBean == null) {
            //TODO throw Exception;
            System.out.println("Error in CorseInfoGUIController: courseBean is null.");
        }
        courseNameText.setText(courseBean.getName());
        trainerNameText.setText(courseBean.getOwner());
        infoLabel.setText(courseBean.getDescription());
        equipmentLabel.setText(courseBean.getEquipment());
        switch (courseBean.getFitnessLevel()){
            case ("base") -> setFitnessLevel(baseFitnessLevelButton);
            case ("intermediate") -> setFitnessLevel(intermediateFitnessLevelButton);
            case ("advanced") -> setFitnessLevel(advancedFitnessLevelButton);
        }
    }
}
