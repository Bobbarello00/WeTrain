package controller;

import database.dao_classes.RequestDAO;
import exception.DBConnectionFailedException;
import model.Request;
import model.Trainer;
import viewone.bean.RequestBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkoutPlanRequestController {

    private final LoginController loginController = new LoginController();

    public void sendRequest(RequestBean requestBean) throws DBConnectionFailedException, SQLException {
        new RequestDAO().saveRequest(
                requestBean.getRequestDate(),
                requestBean.getInfo(),
                requestBean.getAthleteFc(),
                requestBean.getTrainer()
        );
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
