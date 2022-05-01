package controller;

import database.dao_classes.RequestDAO;
import exception.DBConnectionFailedException;
import viewone.bean.RequestBean;

import java.sql.SQLException;

public class RequestWorkoutPlanController {

    public void sendRequest(RequestBean requestBean) throws DBConnectionFailedException, SQLException {
        //TODO richiedi il pagamento
        new RequestDAO().saveRequest(
                requestBean.getRequestDate(),
                requestBean.getInfo(),
                requestBean.getAthleteFc(),
                requestBean.getTrainer()
        );
    }
}
