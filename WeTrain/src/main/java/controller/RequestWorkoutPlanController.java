package controller;

import database.dao_classes.RequestDAO;
import exception.DBUnreachableException;
import model.Athlete;
import model.Exercise;
import model.WorkoutDay;
import model.WorkoutPlan;
import viewone.bean.ExerciseBean;
import viewone.bean.RequestBean;
import viewone.bean.WorkoutDayBean;
import viewone.bean.WorkoutPlanBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequestWorkoutPlanController {

    private final LoginController loginController = new LoginController();

    public void sendRequest(RequestBean requestBean) throws DBUnreachableException, SQLException {
        new RequestDAO().saveRequest(
                requestBean.getRequestDate(),
                requestBean.getInfo(),
                requestBean.getAthleteBean().getFiscalCode(),
                requestBean.getTrainerFc()
        );
    }

    public WorkoutPlanBean getWorkoutPlan() throws SQLException, DBUnreachableException {
        WorkoutPlan workoutPlan = ((Athlete) loginController.getLoggedUser()).getWorkoutPlan();
        if(workoutPlan == null) {
            return null;
        }
        WorkoutPlanBean workoutPlanBean = new WorkoutPlanBean(workoutPlan.getId());
        List<WorkoutDayBean> workoutDayBeanList = new ArrayList<>();
        for(WorkoutDay workoutDay: workoutPlan.getWorkoutDayList()){
            WorkoutDayBean workoutDayBean = new WorkoutDayBean(workoutDay.getDay());
            List<ExerciseBean> exerciseBeanList = new ArrayList<>();
            for(Exercise exercise: workoutDay.getExerciseList()){
                ExerciseBean exerciseBean = new ExerciseBean(
                        exercise.getId(),
                        exercise.getName(),
                        exercise.getInfo()
                );
                exerciseBeanList.add(exerciseBean);
            }
            workoutDayBean.setExerciseBeanList(exerciseBeanList);
            workoutDayBeanList.add(workoutDayBean);
        }
        workoutPlanBean.setWorkoutDayList(workoutDayBeanList);
        return workoutPlanBean;
    }
}
