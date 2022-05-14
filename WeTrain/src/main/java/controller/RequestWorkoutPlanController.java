package controller;

import database.dao_classes.RequestDAO;
import exception.DBUnreachableException;
import viewone.bean.RequestBean;

import java.sql.SQLException;

public class RequestWorkoutPlanController {

    public void sendRequest(RequestBean requestBean) throws DBUnreachableException, SQLException {
        new RequestDAO().saveRequest(
                requestBean.getRequestDate(),
                requestBean.getInfo(),
                requestBean.getAthleteFc(),
                requestBean.getTrainer()
        );
    }
}
