package database.dao_classes;

import database.DatabaseConnection;
import database.Query;
import model.Athlete;
import model.WorkoutPlan;

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
    public Athlete loadAthlete(String fc) throws SQLException{
        try(Statement stmt = conn.createStatement(); ResultSet rs = Query.loadAthlete(stmt, fc)) {
            if (rs.next()) {
                ResultSet rs1 = Query.loadUser(stmt, fc);
                Athlete athlete = new Athlete(rs1.getString("Name"),
                        rs1.getString("Surname"),
                        rs1.getString("Username"),
                        rs1.getDate("Birth").toLocalDate(),
                        rs1.getString("FC"),
                        rs1.getString("Gender").charAt(0),
                        rs1.getString("Email"),
                        rs1.getString("Password"),
                        rs.getString("CardNumber"),
                        rs.getDate("CardExpirationDate").toLocalDate());
                athlete.setWorkoutPlan(new WorkoutPlanDAO().loadWorkoutPlan(athlete));
            } else {
                return null;
            }
        }
        return null;
    }
}
