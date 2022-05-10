package database.dao_classes;

import database.Queries;
import exception.DBConnectionFailedException;
import model.Request;
import model.Trainer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RequestDAO {
    public static final String ID_REQUEST = "idRequest";
    public static final String REQUEST_DATE = "RequestDate";
    public static final String INFO = "Info";
    public static final String ATHLETE = "Athlete";
    public static final String TRAINER = "Trainer";

    public RequestDAO() {}

    public Request loadRequest(int requestCode) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = Queries.loadRequest(requestCode);
            ResultSet rs = preparedStatement.executeQuery()) {
            if(rs.next()) {
                return new Request(
                        rs.getInt(ID_REQUEST),
                        rs.getTimestamp(REQUEST_DATE).toLocalDateTime(),
                        rs.getString(INFO),
                        new AthleteDAO().loadAthlete(rs.getString(ATHLETE)),
                        new TrainerDAO().loadTrainer(rs.getString(TRAINER)));
            } else {
                return null;
            }
        }
    }
    public void deleteRequest(int idRequest) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = Queries.deleteRequest(idRequest)){
            preparedStatement.executeUpdate();
        }
    }

    public List<Request> loadTrainerRequests(Trainer trainer) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = Queries.loadTrainerRequests(trainer.getFiscalCode()); ResultSet rs = preparedStatement.executeQuery()){
            List<Request> myList = new ArrayList<>();
            while(rs.next()) {
                myList.add(new Request(
                        rs.getInt(ID_REQUEST),
                        rs.getTimestamp(REQUEST_DATE).toLocalDateTime(),
                        rs.getString(INFO),
                        new AthleteDAO().loadAthlete(rs.getString(ATHLETE)),
                        trainer));
            }
            return myList;
        }
    }

    public void saveRequest(LocalDateTime requestDate, String info, String athleteFc, String trainer) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = Queries.insertRequest(requestDate, info, athleteFc, trainer)){
            preparedStatement.executeUpdate();
        }
    }
}
