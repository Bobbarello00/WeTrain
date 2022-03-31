package database.dao_classes;

import database.DatabaseConnection;
import database.Query;
import model.Athlete;

import java.sql.*;

public class AthleteDAO {
    Connection conn = DatabaseConnection.getInstance().getConn();

    public void saveAthlete(Athlete athlete) throws SQLException {
        try(Statement stmt = conn.createStatement();){
            Query.insertAthlete(stmt, athlete);
        }catch(SQLException sqlEx){
            sqlEx.printStackTrace();
        }
    }
    public Athlete loadAthlete(String fc) throws SQLException {
        try(Statement stmt = conn.createStatement(); ResultSet rs = Query.loadAthlete(stmt, fc)){
            if(rs.next()) {
                return new Athlete(rs.getString("Name"),
                        rs.getString("Surname"),
                        rs.getString("Username"),
                        rs.getDate("Birth").toLocalDate(),
                        rs.getString("FC"),
                        rs.getString("Gender").charAt(0),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getString("CardNumber"),
                        rs.getDate("CardExpirationDate").toLocalDate());
            }else{
                throw new Exception("Athlete not found!");
            }
        } catch(Exception sqlEx){
            sqlEx.printStackTrace();
        }
        return null;
    }
}
