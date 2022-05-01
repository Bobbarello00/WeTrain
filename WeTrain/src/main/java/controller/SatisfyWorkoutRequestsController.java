package controller;

import database.dao_classes.AthleteDAO;
import database.dao_classes.RequestDAO;
import database.dao_classes.WorkoutPlanDAO;
import exception.DBConnectionFailedException;
import model.*;
import viewone.bean.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SatisfyWorkoutRequestsController {

    private final LoginController loginController = new LoginController();
    private final WorkoutPlan workoutPlan = new WorkoutPlan();

    private Trainer trainer;

    private WorkoutDay getWorkoutDay(String day) {
        for(WorkoutDay workoutDay: workoutPlan.getWorkoutDayList()){
            if(Objects.equals(workoutDay.getDay(), day)) {
                return workoutDay;
            }
        }
        return null;
    }

    public void addExerciseToPlan(ExerciseForWorkoutPlanBean bean) throws SQLException, DBConnectionFailedException {
        WorkoutDay workoutDay = getWorkoutDay(bean.getDay());
        if(workoutDay == null) {
            workoutDay = new WorkoutDay(bean.getDay());
            workoutPlan.addWorkoutDay(workoutDay);
        }
        if(trainer == null){
            trainer = (Trainer) loginController.getLoggedUser();
        }
        workoutDay.addExercise(new Exercise(
                bean.getId(),
                bean.getName(),
                bean.getInfo(),
                trainer
        ));
        System.out.println("Exercise added!");
    }

    public boolean checkAlreadyAdded(ExerciseForWorkoutPlanBean exerciseForWorkoutPlanBean) {
        for(WorkoutDay workoutDay: workoutPlan.getWorkoutDayList()){
            if(Objects.equals(workoutDay.getDay(), exerciseForWorkoutPlanBean.getDay())) {
                for (Exercise exercise : workoutDay.getExerciseList()) {
                    if (Objects.equals(exercise.getName(), exerciseForWorkoutPlanBean.getName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void removeExerciseFromDay(ExerciseForWorkoutPlanBean bean) {
        WorkoutDay workoutDay = getWorkoutDay(bean.getDay());
        if(workoutDay == null){
            System.out.println("Il WorkoutDay non esiste");
            return;
        }
        workoutDay.removeExercise(bean.getName(), bean.getInfo());
        System.out.println("Exercise removed!");
    }

    public void removeExerciseFromPlan(ExerciseBean bean) {
        for(WorkoutDay workoutDay: workoutPlan.getWorkoutDayList()){
            removeExerciseFromDay(new ExerciseForWorkoutPlanBean(
                    bean,
                    workoutDay.getDay()));
        }
    }

    public void sendWorkoutRequest(RequestBean requestBean) throws DBConnectionFailedException, SQLException {
        WorkoutPlan workoutPlan1 = new AthleteDAO().loadAthlete(requestBean.getAthleteFc()).getWorkoutPlan();
        if(workoutPlan1 != null){
            new AthleteDAO().removeWorkoutPlan(workoutPlan1.getId());
        }
        new WorkoutPlanDAO().saveWorkoutPlan(workoutPlan, requestBean.getAthleteFc());
        new RequestDAO().deleteRequest(requestBean.getId());
    }

    public WorkoutDayBean getWorkoutDayBean(DayBean dayBean) {
        for(WorkoutDay workoutDay: workoutPlan.getWorkoutDayList()){
            if(Objects.equals(workoutDay.getDay(), dayBean.getDay())){
                WorkoutDayBean workoutDayBean = new WorkoutDayBean(workoutDay.getDay());
                for(Exercise exercise: workoutDay.getExerciseList()){
                    workoutDayBean.addExerciseBean(new ExerciseBean(
                            exercise.getName(),
                            exercise.getInfo()
                    ));
                }
                return workoutDayBean;
            }
        }
        return new WorkoutDayBean(dayBean.getDay());
    }

    public List<RequestBean> getTrainerRequests() throws SQLException, DBConnectionFailedException {
        List<Request> requestList = new RequestDAO().loadTrainerRequests((Trainer) loginController.getLoggedUser());
        List<RequestBean> requestBeanList = new ArrayList<>();
        for(Request request: requestList) {
            requestBeanList.add(new RequestBean(
                    request.getId(),
                    request.getRequestDate(),
                    request.getInfo(),
                    request.getAthlete().getFiscalCode(),
                    request.getAthlete().getUsername(),
                    request.getTrainer().getFiscalCode()
            ));
        }
        return requestBeanList;
    }
}
