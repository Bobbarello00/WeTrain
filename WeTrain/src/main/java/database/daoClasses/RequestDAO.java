package database.daoClasses;

import database.DatabaseConnection;
import database.Query;
import model.Athlete;
import model.Request;
import model.Trainer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RequestDAO {
    Connection conn = DatabaseConnection.getInstance().conn;

    public Request loadRequest(int requestCode, Trainer trainer, Athlete athlete) throws SQLException{
        try(Statement stmt = conn.createStatement(); ResultSet rs = Query.loadRequest(stmt, requestCode)) {
            if(rs.next()) {
                return new Request(rs.getInt("idRequest"),rs.getTimestamp("RequestDate").toLocalDateTime(), athlete, trainer);
            }
            else{
                throw new Exception("Request not found!");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    public void deleteRequest(Request request) throws SQLException {
        try(Statement stmt = conn.createStatement()){
            Query.deleteRequest(stmt, request);
        }
    }
}
