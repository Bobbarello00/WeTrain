package database.dao_classes;

import database.DatabaseConnectionSingleton;
import database.Queries;
import exception.DBConnectionFailedException;
import exception.ElementNotFoundException;
import exception.invalidDataException.ExpiredCardException;
import model.Athlete;

import java.sql.*;
import java.time.YearMonth;

public class AthleteDAO {

    public static final String TRAINER = "Trainer";
    private static final String NAME = "Name";
    private static final String SURNAME = "Surname";
    private static final String USERNAME = "Username";
    private static final String BIRTH = "Birth";
    private static final String FC = "FC";
    private static final String GENDER = "Gender";
    private static final String EMAIL = "Email";
    private static final String PASSWORD = "Password";
    private static final String CARD_NUMBER = "CardNumber";
    private static final String CARD_EXPIRATION_DATE = "CardExpirationDate";
    private static final String WORKOUT_PLAN = "WorkoutPlan";

    Connection conn = DatabaseConnectionSingleton.getInstance().getConn();

    public AthleteDAO() throws DBConnectionFailedException {}

    public void updateCardInfo(String cardNumber, YearMonth expirationDate, Athlete athlete) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            athlete.changeCardInfo(cardNumber, expirationDate);
            Queries.updateCardInfoAthlete(stmt, athlete);
        }
    }

    public void removeCardInfo(String fc) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            Queries.removeCardInfoAthlete(stmt, fc);
        }
    }

    public void saveAthlete(Athlete athlete) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            Queries.insertAthlete(stmt, athlete);
        }
    }

    public Athlete loadAthlete(String fc) throws SQLException, DBConnectionFailedException {
        try (Statement stmt = conn.createStatement(); ResultSet rs = Queries.loadUser(stmt, fc)) {
            if (rs.next()) {
                Athlete athlete = new Athlete(rs.getString(NAME),
                        rs.getString(SURNAME),
                        rs.getString(USERNAME),
                        rs.getDate(BIRTH).toLocalDate(),
                        rs.getString(FC),
                        rs.getString(GENDER).charAt(0),
                        rs.getString(EMAIL),
                        rs.getString(PASSWORD)
                );

                try (ResultSet rs1 = Queries.loadAthlete(stmt, fc)) {
                    if (rs1.next()) {
                        athlete.setCardNumber(rs1.getString(CARD_NUMBER));
                        Date temp = rs1.getDate(CARD_EXPIRATION_DATE);
                        YearMonth cardExpirationDate = null;
                        if (temp != null) {
                            cardExpirationDate = YearMonth.from(temp.toLocalDate());
                        }
                        athlete.setCardExpirationDate(cardExpirationDate);
                        if(rs1.getString(TRAINER) != null) {
                            athlete.setTrainer(new TrainerDAO().loadTrainer(rs1.getString(TRAINER)));
                        }
                        if (rs1.getInt(WORKOUT_PLAN) != 0 && athlete.getTrainer() != null) {
                            athlete.setWorkoutPlan(new WorkoutPlanDAO().loadWorkoutPlan(rs1.getInt(WORKOUT_PLAN), athlete.getTrainer()));
                        } else {
                            athlete.setWorkoutPlan(null);
                        }
                        athlete.setCourseList(new CourseDAO().loadAllCoursesAthlete(athlete));
                        return athlete;
                    } else {
                        return null;
                    }
                } catch (ExpiredCardException e) {
                    Queries.removeCardInfoAthlete(stmt, fc);
                    return loadAthlete(fc);
                }
            } else {
                throw new ElementNotFoundException();
            }
        }
    }

    public int getNumberOfCourses(String athleteFc) throws SQLException {
        try (Statement stmt = conn.createStatement(); ResultSet rs = Queries.countAthleteCourses(stmt, athleteFc)) {
            if(rs.next()){
                return rs.getInt(1);
            }else{
                return 0;
            }
        }
    }

    public void setTrainer(Athlete athlete, String fc) {
        try (Statement stmt = conn.createStatement()) {
            Queries.updateTrainerAthlete(stmt, athlete.getFiscalCode(), fc);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeTrainer(Athlete athlete){
        try (Statement stmt = conn.createStatement()) {
            Queries.removeTrainerAthlete(stmt, athlete.getFiscalCode());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeWorkoutPlan(int idWorkoutPlan) {
        try (Statement stmt = conn.createStatement()) {
            Queries.removeWorkoutPlan(stmt, idWorkoutPlan);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addWorkoutPlan(int id, String athleteFc) throws SQLException {
        try(Statement stmt = conn.createStatement()) {
            Queries.addWorkoutPlanToAthlete(stmt, id, athleteFc);
        }
    }

}
