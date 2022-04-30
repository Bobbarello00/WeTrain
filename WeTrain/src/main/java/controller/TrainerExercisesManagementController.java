package controller;

import database.dao_classes.ExerciseDAO;
import exception.DBConnectionFailedException;
import model.Exercise;
import model.Trainer;
import viewone.bean.ExerciseBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainerExercisesManagementController {

    private final LoginController loginController = new LoginController();

    public void addExerciseToTrainer(ExerciseBean exerciseBean) throws SQLException, DBConnectionFailedException {
        new ExerciseDAO().saveExercise(new Exercise(
                exerciseBean.getName(),
                exerciseBean.getInfo(),
                (Trainer) loginController.getLoggedUser()
        ));
    }

    public List<ExerciseBean> getTrainerExercises() throws SQLException, DBConnectionFailedException {
        List<Exercise> exerciseList = new ExerciseDAO().loadTrainerExercises((Trainer) loginController.getLoggedUser());
        List<ExerciseBean> exerciseBeanList = new ArrayList<>();
        for(Exercise exercise: exerciseList){
            exerciseBeanList.add(new ExerciseBean(
                    exercise.getId(),
                    exercise.getName(),
                    exercise.getInfo()
                    ));
        }
        return exerciseBeanList;
    }

    public void removeExerciseFromTrainer(ExerciseBean exerciseBean) throws DBConnectionFailedException, SQLException {
        new ExerciseDAO().removeExercise(new Exercise(
                exerciseBean.getId(),
                exerciseBean.getName(),
                exerciseBean.getInfo(),
                (Trainer) loginController.getLoggedUser()
        ));
    }
}
