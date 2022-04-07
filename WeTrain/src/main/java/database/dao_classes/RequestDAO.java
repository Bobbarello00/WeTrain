package database.dao_classes;

import database.DatabaseConnectionSingleton;
import database.Query;
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

    public Request loadRequest(int requestCode, Trainer trainer, Athlete athlete) throws SQLException{
        try(Statement stmt = conn.createStatement(); ResultSet rs = Query.loadRequest(stmt, requestCode)) {
            if(rs.next()) {
                return new Request(rs.getInt("idRequest"), rs.getTimestamp("RequestDate").toLocalDateTime(),
                        athlete, trainer);
            }
            else{
                throw new Exception("Request not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
                ResultSet rs1 = Query.loadAthlete(stmt, rs.getString("Athlete"));
                Athlete myAthlete = null;
                if (rs1.next()) {
                    myAthlete = new Athlete(rs1.getString("Name"),
                            rs1.getString("Surname"),
                            rs1.getString("Username"),
                            rs1.getDate("Birth").toLocalDate(),
                            rs1.getString("FC"),
                            rs1.getString("Gender").charAt(0),
                            rs1.getString("Email"),
                            rs1.getString("Password"),
                            rs1.getString("CardNumber"),
                            YearMonth.from((rs1.getDate("CardExpirationDate")).toInstant()
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDate()));
                }
                myList.add(new Request(rs.getInt("idRequest"), rs.getTimestamp("RequestDate").toLocalDateTime(),
                        myAthlete, trainer));
            }
        }
        return null;
    }
}
