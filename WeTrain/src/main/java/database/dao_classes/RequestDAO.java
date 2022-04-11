package database.dao_classes;

import database.DatabaseConnectionSingleton;
import database.Query;
import exception.ExpiredCardException;
import model.Athlete;
import model.Request;
import model.Trainer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class RequestDAO {
    Connection conn = DatabaseConnectionSingleton.getInstance().getConn();

    public Request loadRequest(int requestCode) throws SQLException{
        try(Statement stmt = conn.createStatement(); ResultSet rs = Query.loadRequest(stmt, requestCode)) {
            if(rs.next()) {
                return new Request(
                        rs.getInt("idRequest"),
                        rs.getTimestamp("RequestDate").toLocalDateTime(),
                        rs.getString("Info"),
                        new AthleteDAO().loadAthlete(rs.getString("Athlete")),
                        new TrainerDAO().loadTrainer(rs.getString("Trainer")));
            } else {
                return null;
            }
        }
    }
    public void deleteRequest(Request request) throws SQLException {
        try(Statement stmt = conn.createStatement()){
            Query.deleteRequest(stmt, request);
        }
    }
    public List<Request> loadTrainerRequests(Trainer trainer) throws SQLException {
        try(Statement stmt = conn.createStatement(); ResultSet rs = Query.loadTrainerRequests(stmt, trainer)){
            List<Request> myList = new ArrayList<>();
            while(rs.next()) {
                myList.add(new Request(
                        rs.getInt("idRequest"),
                        rs.getTimestamp("RequestDate").toLocalDateTime(),
                        rs.getString("Info"),
                        new AthleteDAO().loadAthlete(rs.getString("Athlete")),
                        trainer));
            }
            return myList;
        }
    }
}
