package database.dao_classes;

import database.Queries;
import exception.DBConnectionFailedException;
import exception.ElementNotFoundException;
import exception.invalidDataException.ExpiredCardException;
import model.Athlete;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.YearMonth;
import java.util.List;

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

    public AthleteDAO(){}

    public void updateCardInfo(String cardNumber, YearMonth expirationDate, Athlete athlete) throws SQLException, DBConnectionFailedException {
        athlete.changeCardInfo(cardNumber, expirationDate);
        Queries.updateCardInfoAthlete(athlete);

    }

    public void removeCardInfo(String fc) throws SQLException, DBConnectionFailedException {
        Queries.removeCardInfoAthlete(fc);
    }

    public void saveAthlete(Athlete athlete) throws SQLException, DBConnectionFailedException {
        List<PreparedStatement> preparedStatementList = Queries.insertAthlete(athlete);
        preparedStatementList.get(0).executeUpdate();
        preparedStatementList.get(1).executeUpdate();
        preparedStatementList.get(0).close();
        preparedStatementList.get(1).close();
    }

    public Athlete loadAthlete(String fc) throws SQLException, DBConnectionFailedException {
        try (PreparedStatement preparedStatement = Queries.loadUser(fc)) {
            ResultSet rs = preparedStatement.executeQuery();
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

                try (PreparedStatement preparedStatement1 = Queries.loadAthlete(fc)) {
                    ResultSet rs1 = preparedStatement1.executeQuery();
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
                    Queries.removeCardInfoAthlete(fc);
                    return loadAthlete(fc);
                }
            } else {
                throw new ElementNotFoundException();
            }
        }
    }

    public int getNumberOfCourses(String athleteFc) throws SQLException, DBConnectionFailedException {
        try (ResultSet rs = Queries.countAthleteCourses(athleteFc)) {
            if(rs.next()){
                return rs.getInt(1);
            }else{
                return 0;
            }
        }
    }

    public void setTrainer(Athlete athlete, String fc) throws SQLException, DBConnectionFailedException {
        Queries.updateTrainerAthlete(athlete.getFiscalCode(), fc);
    }

    public void removeTrainer(Athlete athlete) throws SQLException, DBConnectionFailedException {
        Queries.removeTrainerAthlete(athlete.getFiscalCode());
    }

    public void removeWorkoutPlan(int idWorkoutPlan) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = Queries.removeWorkoutPlan(idWorkoutPlan)){
            preparedStatement.executeUpdate();
        }
    }

    public void addWorkoutPlan(int idWorkoutPlan, String athleteFc) throws SQLException, DBConnectionFailedException {
            Queries.addWorkoutPlanToAthlete(idWorkoutPlan, athleteFc);
    }

}
