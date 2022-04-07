package database.dao_classes;

import database.DatabaseConnectionSingleton;
import database.Query;
import model.Athlete;

import java.sql.*;
import java.time.YearMonth;
import java.time.ZoneId;

public class AthleteDAO {
    Connection conn = DatabaseConnectionSingleton.getInstance().getConn();

    public void updateCardInfo(String cardNumber, YearMonth expirationDate, String type, Athlete athlete) throws SQLException{
        try(Statement stmt = conn.createStatement()){
            //TODO set dei valori prima di fare update ??
            athlete.setCardNumber(cardNumber);
            athlete.setCardExpirationDate(expirationDate);
            athlete.setCardType(type);
            Query.updateCardInfoAthlete(stmt, athlete);
        }
    }

    public void saveAthlete(Athlete athlete) throws SQLException {
        try(Statement stmt = conn.createStatement()){
            Query.insertAthlete(stmt, athlete);
        }
    }

    public Athlete loadAthlete(String fc) throws SQLException{
        try(Statement stmt = conn.createStatement(); ResultSet rs = Query.loadUser(stmt, fc)) {
            if (rs.next()) {
                Athlete athlete = new Athlete(rs.getString("Name"),
                        rs.getString("Surname"),
                        rs.getString("Username"),
                        rs.getDate("Birth").toLocalDate(),
                        rs.getString("FC"),
                        rs.getString("Gender").charAt(0),
                        rs.getString("Email"),
                        rs.getString("Password")
                );

                try(ResultSet rs1 = Query.loadAthlete(stmt, fc)) {
                    if (rs1.next()) {
                        athlete.setCardNumber(rs1.getString("CardNumber"));
                        Date temp = rs1.getDate("CardExpirationDate");
                        YearMonth cardExpirationDate = null;
                        if(temp!=null){
                            cardExpirationDate = YearMonth.from(temp.toLocalDate());
                        }
                        athlete.setCardExpirationDate(cardExpirationDate);
                        if (rs1.getInt("WorkoutPlan") != 0) {
                            athlete.setWorkoutPlan(new WorkoutPlanDAO().loadWorkoutPlan(rs1.getInt("WorkoutPlan"), athlete));
                        } else {
                            athlete.setWorkoutPlan(null);
                        }
                        athlete.setCourseList(new CourseDAO().loadAllCoursesAthlete(athlete));
                        return athlete;
                    }
                }
            } else {
                return null;
            }
        }
        return null;
    }
}
