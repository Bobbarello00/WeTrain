package controller;

import model.Athlete;
import model.Exercise;
import model.WorkoutDay;
import model.WorkoutPlan;
import viewone.bean.ExerciseBean;
import viewone.bean.WorkoutDayBean;
import viewone.bean.WorkoutPlanBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkoutPlanController {

    private final LoginController loginController = new LoginController();

    public WorkoutPlanBean getWorkoutPlan() throws SQLException {
        WorkoutPlan workoutPlan = ((Athlete) loginController.getLoggedUser()).getWorkoutPlan();
        if(workoutPlan == null) {
            return null;
        }
        WorkoutPlanBean workoutPlanBean = new WorkoutPlanBean(workoutPlan.getId());
        List<WorkoutDayBean> workoutDayBeanList = new ArrayList<>();
        for(WorkoutDay workoutDay: workoutPlan.getWorkoutDayList()){
            WorkoutDayBean workoutDayBean = new WorkoutDayBean(
                    workoutDay.getDay(),
                    workoutDay.getInfo());
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
