package controller;

import database.dao_classes.AthleteDAO;
import database.dao_classes.RequestDAO;
import database.dao_classes.WorkoutPlanDAO;
import exception.DBUnreachableException;
import exception.ElementNotFoundException;
import model.*;
import viewone.bean.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SatisfyWorkoutRequestsController {

    private final NotificationsController notificationsController = new NotificationsController();
    private final LoginController loginController = new LoginController();
    private final WorkoutPlan workoutPlan = new WorkoutPlan();

    private Trainer trainer;

    public void rejectRequest(RequestBean requestBean) throws DBUnreachableException, SQLException {
        new RequestDAO().deleteRequest(requestBean.getId());
        notificationsController.sendRejectRequestNotification(requestBean.getAthleteFc());
    }

    private WorkoutDay getWorkoutDay(String day) {
        for(WorkoutDay workoutDay: workoutPlan.getWorkoutDayList()){
            if(Objects.equals(workoutDay.getDay(), day)) {
                return workoutDay;
            }
        }
        return null;
    }

    public void addExerciseToPlan(ExerciseForWorkoutPlanBean bean) throws SQLException, DBUnreachableException {
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

    public void removeExerciseFromDay(ExerciseForWorkoutPlanBean bean) throws ElementNotFoundException {
        WorkoutDay workoutDay = getWorkoutDay(bean.getDay());
        if(workoutDay == null){
            throw new ElementNotFoundException();
        }
        workoutDay.removeExercise(bean.getName(), bean.getInfo());
    }

    public void removeExerciseFromPlan(ExerciseBean bean) throws ElementNotFoundException {
        for(WorkoutDay workoutDay: workoutPlan.getWorkoutDayList()){
            removeExerciseFromDay(new ExerciseForWorkoutPlanBean(
                    bean,
                    workoutDay.getDay()));
        }
    }

    public void sendWorkoutPlan(RequestBean requestBean) throws DBUnreachableException, SQLException {
        Athlete receiver = new AthleteDAO().loadAthlete(requestBean.getAthleteFc());
        WorkoutPlan workoutPlan = receiver.getWorkoutPlan();
        if(workoutPlan != null){
            new AthleteDAO().removeWorkoutPlan(workoutPlan.getId());
        }
        new WorkoutPlanDAO().saveWorkoutPlan(this.workoutPlan, requestBean.getAthleteFc());
        new RequestDAO().deleteRequest(requestBean.getId());
        notificationsController.sendWorkoutPlanReadyNotification(receiver);
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

    public List<RequestBean> getTrainerRequests() throws SQLException, DBUnreachableException {
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
