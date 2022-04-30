package controller;

import exception.DBConnectionFailedException;
import model.Athlete;
import model.Exercise;
import model.WorkoutDay;
import model.WorkoutPlan;
import viewone.bean.ExerciseBean;
import viewone.bean.ExerciseForWorkoutPlanBean;
import viewone.bean.WorkoutDayBean;
import viewone.bean.WorkoutPlanBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WorkoutPlanController {

    private final LoginController loginController = new LoginController();

    public WorkoutPlanBean getWorkoutPlan() throws SQLException, DBConnectionFailedException {
        WorkoutPlan workoutPlan = ((Athlete) loginController.getLoggedUser()).getWorkoutPlan();
        if(workoutPlan == null) {
            return null;
        }
        WorkoutPlanBean workoutPlanBean = new WorkoutPlanBean(workoutPlan.getId());
        List<WorkoutDayBean> workoutDayBeanList = new ArrayList<>();
        for(WorkoutDay workoutDay: workoutPlan.getWorkoutDayList()){
            WorkoutDayBean workoutDayBean = new WorkoutDayBean(
                    workoutDay.getDay());
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
