package database.dao_classes;

import database.DatabaseConnectionSingleton;
import database.Queries.Queries;
import exceptions.DBConnectionFailedException;
import exceptions.DBUnreachableException;
import models.Request;
import models.Trainer;

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

    public void deleteRequest(int idRequest) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                Queries.DELETE_REQUEST_QUERY)){
            Queries.deleteRequest(preparedStatement, idRequest);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public List<Request> loadTrainerRequests(Trainer trainer) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                Queries.LOAD_TRAINER_REQUESTS_QUERY); ResultSet rs = Queries.loadTrainerRequests(trainer.getFiscalCode(), preparedStatement)){
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
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public void saveRequest(LocalDateTime requestDate, String info, String athleteFc, String trainer) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                Queries.INSERT_REQUEST_QUERY)){
            Queries.insertRequest(preparedStatement, requestDate, info, athleteFc, trainer);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }
}
