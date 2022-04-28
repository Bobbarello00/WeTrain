package controller;

import database.dao_classes.ExerciseDAO;
import exception.DBConnectionFailedException;
import model.Exercise;
import model.Trainer;
import viewone.bean.ExerciseBean;

import java.sql.SQLException;

public class TrainerExercisesManagementController {

    private final LoginController loginController = new LoginController();

    public void addExerciseToTrainer(ExerciseBean exerciseBean) throws SQLException, DBConnectionFailedException {
        new ExerciseDAO().saveExercise(new Exercise(
                exerciseBean.getName(),
                exerciseBean.getInfo(),
                (Trainer) loginController.getLoggedUser()
        ));
    }
}
