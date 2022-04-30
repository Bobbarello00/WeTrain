package controller;

import database.dao_classes.WorkoutPlanDAO;
import exception.DBConnectionFailedException;
import model.Exercise;
import model.Trainer;
import model.WorkoutDay;
import model.WorkoutPlan;
import viewone.bean.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SatisfyWorkoutRequestsController {

    private final LoginController loginController = new LoginController();

    private final WorkoutPlanBean workoutPlanBean = new WorkoutPlanBean();

    private WorkoutDayBean getWorkoutDay(String day) {
        for(WorkoutDayBean workoutDay: workoutPlanBean.getWorkoutDayList()){
            if(Objects.equals(workoutDay.getDay(), day)) {
                return workoutDay;
            }
        }
        return null;
    }

    public void addExerciseToPlan(ExerciseForWorkoutPlanBean bean) {
        //TODO aggiungere inserimento delle info per ogni WorkoutDay
        WorkoutDayBean workoutDayBean = getWorkoutDay(bean.getDay());
        if(workoutDayBean == null) {
            workoutDayBean = new WorkoutDayBean(bean.getDay(), null);
            workoutPlanBean.addWorkoutDayBean(workoutDayBean);
        }
        workoutDayBean.addExerciseBean(bean.getExerciseBean());
        System.out.println("Exercise added!");
    }

    public boolean checkAlreadyAdded(ExerciseForWorkoutPlanBean exerciseForWorkoutPlanBean) {
        for(WorkoutDayBean workoutDayBean: workoutPlanBean.getWorkoutDayList()){
            for(ExerciseBean exerciseBean: workoutDayBean.getExerciseBeanList()){
                if(Objects.equals(exerciseBean.getName(), exerciseForWorkoutPlanBean.getExerciseBean().getName())){
                    return true;
                }
            }
        }
        return false;
    }

    public void removeExerciseFromPlan(ExerciseForWorkoutPlanBean bean) {
        WorkoutDayBean workoutDayBean = getWorkoutDay(bean.getDay());
        if(workoutDayBean == null){
            System.out.println("Il WorkoutDay non esiste");
            return;
        }
        workoutDayBean.removeExerciseBean(bean.getExerciseBean());
        System.out.println("Exercise added!");
    }

    public void sendWorkoutRequest(RequestBean requestBean) throws DBConnectionFailedException, SQLException {
        WorkoutPlan workoutPlan = getWorkoutPlan(workoutPlanBean);
        new WorkoutPlanDAO().saveWorkoutPlan(workoutPlan, requestBean.getAthleteFc());
    }

    private WorkoutPlan getWorkoutPlan(WorkoutPlanBean workoutPlanBean) throws SQLException, DBConnectionFailedException {
        Trainer trainer = (Trainer) loginController.getLoggedUser();
        WorkoutPlan workoutPlan = new WorkoutPlan();
        List<WorkoutDay> workoutDayList = new ArrayList<>();
        for(WorkoutDayBean workoutDayBean: workoutPlanBean.getWorkoutDayList()){
            List<Exercise> exerciseList = new ArrayList<>();
            for(ExerciseBean exerciseBean: workoutDayBean.getExerciseBeanList()){
                exerciseList.add(new Exercise(
                        exerciseBean.getId(),
                        exerciseBean.getName(),
                        exerciseBean.getInfo(),
                        trainer));
            }
            workoutDayList.add(new WorkoutDay(
                    workoutDayBean.getDay(),
                    workoutDayBean.getInfo(),
                    exerciseList));
        }
        workoutPlan.addAllWorkoutDays(workoutDayList);
        return workoutPlan;
    }

}
