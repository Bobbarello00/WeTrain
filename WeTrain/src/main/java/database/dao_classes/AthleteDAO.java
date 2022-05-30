package database.dao_classes;

import database.DatabaseConnectionSingleton;
import database.Queries;
import exception.DBConnectionFailedException;
import exception.DBUnreachableException;
import exception.invalid_data_exception.ExpiredCardException;
import exception.runtime_exception.ResultSetIsNullException;
import model.Athlete;
import model.record.Card;
import model.record.Credentials;
import model.record.PersonalInfo;
import org.jetbrains.annotations.Nullable;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public void updateCardInfo(Card card, Athlete athlete) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                Queries.updateCardInfoAthleteQuery)) {
            Queries.updateCardInfoAthlete(preparedStatement, athlete, card);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }

    }

    public void saveAthlete(Athlete athlete) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                Queries.insertAthleteQuery1);
            PreparedStatement preparedStatement1 = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                    Queries.insertAthleteQuery2)) {
            Queries.insertAthlete(preparedStatement, preparedStatement1, athlete);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public Athlete loadAthlete(String fc) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                Queries.loadUser2Query);ResultSet rs = Queries.loadUser(preparedStatement, fc)) {
            if (rs.next()) {
                Athlete athlete = new Athlete(
                        rs.getString(USERNAME),
                        new PersonalInfo(
                                rs.getString(NAME),
                                rs.getString(SURNAME),
                                rs.getDate(BIRTH).toLocalDate(),
                                rs.getString(FC),
                                rs.getString(GENDER).charAt(0)
                        ),
                        new Credentials(
                                rs.getString(EMAIL),
                                rs.getString(PASSWORD)
                        )
                );
                return completeAthleteInfo(fc, athlete);
            } else {
                return null;
            }
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    @Nullable
    private Athlete completeAthleteInfo(String fc, Athlete athlete) throws SQLException, DBUnreachableException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                Queries.loadAthleteQuery);ResultSet rs1 = Queries.loadAthlete(preparedStatement, fc)) {
            if (rs1.next()) {
                String cardNumber = rs1.getString(CARD_NUMBER);
                Date temp = rs1.getDate(CARD_EXPIRATION_DATE);
                YearMonth cardExpirationDate = null;
                if (temp != null) {
                    cardExpirationDate = YearMonth.from(temp.toLocalDate());
                }
                athlete.setCard(new Card(cardNumber, cardExpirationDate));
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
            try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                    Queries.removeCardInfoAthleteQuery)) {
                Queries.removeCardInfoAthlete(preparedStatement, fc);
            }
            return loadAthlete(fc);
        }
    }

    public int getNumberOfCourses(String athleteFc) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                Queries.countAthleteCoursesQuery);ResultSet rs = Queries.countAthleteCourses(preparedStatement, athleteFc)) {
            if(rs.next()){
                return rs.getInt(1);
            }else{
                throw new ResultSetIsNullException();
            }
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public void setTrainer(Athlete athlete, String fc) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                Queries.updateTrainerAthleteQuery)) {
            Queries.updateTrainerAthlete(preparedStatement, athlete.getFiscalCode(), fc);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public void removeTrainer(Athlete athlete) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                Queries.removeTrainerAthleteQuery)) {
            Queries.removeTrainerAthlete(preparedStatement, athlete.getFiscalCode());
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public void removeWorkoutPlan(int idWorkoutPlan) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                Queries.removeWorkoutPlanQuery)) {
            Queries.removeWorkoutPlan(preparedStatement, idWorkoutPlan);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }

    public void addWorkoutPlan(int idWorkoutPlan, String athleteFc) throws SQLException, DBUnreachableException {
        try(PreparedStatement preparedStatement = DatabaseConnectionSingleton.getInstance().getConn().prepareStatement(
                Queries.addWorkoutPlanToAthleteQuery)) {
            Queries.addWorkoutPlanToAthlete(preparedStatement, idWorkoutPlan, athleteFc);
        } catch (DBConnectionFailedException e) {
            e.deleteDatabaseConn();
            throw new DBUnreachableException();
        }
    }
}
