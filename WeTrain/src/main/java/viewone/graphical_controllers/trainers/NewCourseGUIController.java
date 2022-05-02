package viewone.graphical_controllers.trainers;

import controller.CourseManagementTrainerController;
import exception.DBConnectionFailedException;
import exception.InvalidDataException;
import exception.InvalidTimeException;
import exception.TimeNotInsertedException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import viewone.MainPane;
import viewone.PageSwitchSimple;
import viewone.bean.CourseBean;
import viewone.bean.LessonBean;
import viewone.engeneering.UserInfoCarrier;
import viewone.graphical_controllers.FitnessLevelFilterGUIController;
import viewone.graphical_controllers.TimeSchedulerGUIController;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.util.*;

public class NewCourseGUIController extends HomeGUIControllerTrainers implements Initializable {
    public Boolean[] toggled = new Boolean[7];
    private final FitnessLevelFilterGUIController fitnessLevelFilter= new FitnessLevelFilterGUIController();
    private List<TimeSchedulerGUIController> timeSchedulerGUIControllerList = new ArrayList<>();
    private CourseBean courseToModify;

    @FXML private ListView<Node> exercisesSelectedList;
    @FXML private Button mondayButton;
    @FXML private TimeSchedulerGUIController mondayTimeSchedulerController;
    @FXML private Parent mondayTimeScheduler;
    @FXML private Button tuesdayButton;
    @FXML private TimeSchedulerGUIController tuesdayTimeSchedulerController;
    @FXML private Parent tuesdayTimeScheduler;
    @FXML private Button wednesdayButton;
    @FXML private TimeSchedulerGUIController wednesdayTimeSchedulerController;
    @FXML private Parent wednesdayTimeScheduler;
    @FXML private Button thursdayButton;
    @FXML private TimeSchedulerGUIController thursdayTimeSchedulerController;
    @FXML private Parent thursdayTimeScheduler;
    @FXML private Button fridayButton;
    @FXML private TimeSchedulerGUIController fridayTimeSchedulerController;
    @FXML private Parent fridayTimeScheduler;
    @FXML private Button saturdayButton;
    @FXML private TimeSchedulerGUIController saturdayTimeSchedulerController;
    @FXML private Parent saturdayTimeScheduler;
    @FXML private Button sundayButton;
    @FXML private TimeSchedulerGUIController sundayTimeSchedulerController;
    @FXML private Parent sundayTimeScheduler;
    @FXML private TextField courseNameText;
    @FXML private TextArea infoTextArea;
    @FXML private TextArea equipmentTextArea;
    @FXML private Button baseFitnessLevelButton;
    @FXML private Button intermediateFitnessLevelButton;
    @FXML private Button advancedFitnessLevelButton;
    @FXML private Button createButton;
    @FXML private Text pageTitle;

    private List<Button> buttonList = new ArrayList<>();

    private final CourseManagementTrainerController courseManagementTrainerController = new CourseManagementTrainerController();

    @FXML void createButtonAction() throws IOException {
        String fitnessLevel = fitnessLevelFilter.getSelectedFitnessLevelString();
        try{
            UserInfoCarrier user = getUserInfo();
            if(user == null){
                return;
            }
            CourseBean courseBean = new CourseBean(
                    courseNameText.getText(),
                    infoTextArea.getText(),
                    fitnessLevel,
                    user.getFiscalCode(),
                    equipmentTextArea.getText());
            courseBean.setLessonBeanList(getLessonDay());
            if (courseToModify == null){
                courseManagementTrainerController.createCourse(courseBean);
                System.out.println("Created!");
            } else {
                courseManagementTrainerController.modifyCourse(courseBean, courseToModify.getId());
                System.out.println("Modified!");
            }
            PageSwitchSimple.switchPage(MainPane.getInstance(),"TrainersHome", "trainers");
            MenuTrainersGUIController.resetSelectedButton();

        } catch (InvalidDataException e){
            e.alert();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DBConnectionFailedException e) {
            e.alertAndLogOff();
        }
    }

    private List<LessonBean> getLessonDay() throws TimeNotInsertedException {
        List<LessonBean> lessonBeanList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            if (toggled[i]) {
                lessonBeanList.add(new LessonBean(
                        DayOfWeek.of(i+1).name(),
                        timeSchedulerGUIControllerList.get(i).getStartTime(),
                        timeSchedulerGUIControllerList.get(i).getEndTime()));
            }
        }
        return lessonBeanList;
    }

    @FXML void fitnessLevelSelection(ActionEvent event){
        fitnessLevelFilter.fitnessLevelSelection(event);
    }

    @FXML void dayButtonAction(ActionEvent event) {
        String sourceId = ((Node) event.getSource()).getId();
        switch(sourceId){
            case "mondayButton" -> toggledDayButtonAction(mondayTimeSchedulerController,mondayButton,0);
            case "tuesdayButton" -> toggledDayButtonAction(tuesdayTimeSchedulerController,tuesdayButton,1);
            case "wednesdayButton" -> toggledDayButtonAction(wednesdayTimeSchedulerController,wednesdayButton,2);
            case "thursdayButton" -> toggledDayButtonAction(thursdayTimeSchedulerController,thursdayButton,3);
            case "fridayButton" -> toggledDayButtonAction(fridayTimeSchedulerController,fridayButton,4);
            case "saturdayButton" -> toggledDayButtonAction(saturdayTimeSchedulerController,saturdayButton,5);
            case "sundayButton" -> toggledDayButtonAction(sundayTimeSchedulerController,sundayButton,6);
        }
    }

    private void toggledDayButtonAction(TimeSchedulerGUIController controller, Button button, int i){
        toggled[i]=!toggled[i];
        controller.toggleVisibility(toggled[i]);
        if(toggled[i]) {
            button.setStyle("-fx-background-color: white;" +
                    " -fx-text-fill: rgb(24,147,21);" +
                    " -fx-border-color: rgb(24,147,21);" +
                    " -fx-border-radius: 50");
        }else{
            button.setStyle(null);
        }
    }

    public void setValue(CourseBean courseBean) {
        pageTitle.setText("Modify Course");
        createButton.setText("Modify");
        courseToModify = courseBean;
        courseNameText.setText(courseBean.getName());
        if(Objects.equals(courseBean.getFitnessLevel(), "Base")) {
            baseFitnessLevelButton.fire();
        } else if(Objects.equals(courseBean.getFitnessLevel(), "Intermediate")) {
            intermediateFitnessLevelButton.fire();
        } else {
            advancedFitnessLevelButton.fire();
        }

        equipmentTextArea.setText(courseBean.getEquipment());
        infoTextArea.setText(courseBean.getDescription());

        for(LessonBean lessonBean: courseBean.getLessonBeanList()){
            for(int i = 0; i < 7; i++) {
                if(DayOfWeek.of(i + 1).name().equals(lessonBean.getLessonDay())) {
                    buttonList.get(i).fire();
                    timeSchedulerGUIControllerList.get(i).setStartBox(lessonBean.getLessonStartTime());
                    timeSchedulerGUIControllerList.get(i).setEndBox(lessonBean.getLessonEndTime());
                }
            }
        }
    }

    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        baseFitnessLevelButton.fire();
        Arrays.fill(toggled, Boolean.FALSE);
        setUserInfoTab();
        buttonList = Arrays.asList(
                mondayButton,
                tuesdayButton,
                wednesdayButton,
                thursdayButton,
                fridayButton,
                saturdayButton,
                sundayButton
        );
        timeSchedulerGUIControllerList = Arrays.asList(
                mondayTimeSchedulerController,
                tuesdayTimeSchedulerController,
                wednesdayTimeSchedulerController,
                thursdayTimeSchedulerController,
                fridayTimeSchedulerController,
                saturdayTimeSchedulerController,
                sundayTimeSchedulerController
        );
    }
}