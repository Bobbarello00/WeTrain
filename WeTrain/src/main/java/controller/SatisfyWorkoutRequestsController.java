package controller;

import database.dao_classes.WorkoutPlanDAO;
import exception.DBConnectionFailedException;
import model.Exercise;
import model.Trainer;
import model.WorkoutDay;
import model.WorkoutPlan;
import viewone.bean.*;

import java.sql.SQLException;
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
        System.out.println(workoutPlan.getWorkoutDayList().size());
        if(trainer == null){
            trainer = (Trainer) loginController.getLoggedUser();
        }
        workoutDay.addExercise(new Exercise(
                bean.getName(),
                bean.getInfo(),
                trainer
        ));
        System.out.println("Exercise added!");
    }

    public boolean checkAlreadyAdded(ExerciseForWorkoutPlanBean exerciseForWorkoutPlanBean) {
        for(WorkoutDay workoutDay: workoutPlan.getWorkoutDayList()){
            for(Exercise exercise: workoutDay.getExerciseList()){
                if(Objects.equals(exercise.getName(), exerciseForWorkoutPlanBean.getName())){
                    return true;
                }
            }
        }
        return false;
    }

    public void removeExerciseFromPlan(ExerciseForWorkoutPlanBean bean) {
        WorkoutDay workoutDay = getWorkoutDay(bean.getDay());
        if(workoutDay == null){
            System.out.println("Il WorkoutDay non esiste");
            return;
        }
        workoutDay.removeExercise(bean.getName(), bean.getInfo());
        System.out.println("Exercise removed!");
    }

    public void sendWorkoutRequest(RequestBean requestBean) throws DBConnectionFailedException, SQLException {
        new WorkoutPlanDAO().saveWorkoutPlan(workoutPlan, requestBean.getAthleteFc());
    }

    public WorkoutDayBean getWorkoutDayBean(DayBean dayBean) {
        for(WorkoutDay workoutDay: workoutPlan.getWorkoutDayList()){
            System.out.println(workoutDay.getDay());
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
}
