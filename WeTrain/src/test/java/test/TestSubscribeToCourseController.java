package test;

import controllers.SubscribeToCourseController;
import exceptions.DBUnreachableException;
import exceptions.PaymentFailedException;
import exceptions.invalid_data_exception.ExpiredCardException;
import exceptions.invalid_data_exception.NoCardInsertedException;
import models.Athlete;
import models.record.Card;
import models.record.Credentials;
import models.record.PersonalInfo;
import org.junit.jupiter.api.Test;
import viewone.beans.CourseBean;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSubscribeToCourseController {

    private static final String EMAIL = "edo@gmail.com";
    private static final String PASSWORD = "Ciaociao00!";

    private static final String COURSE_OWNER_FC = "RGNMAR00E21D662Q";
    private static final String COURSE_DESCRIPTION = "A form of high intensity interval training, " +
            "CrossFit is a strength and conditioning workout that is made up of functional " +
            "movement performed at a high intensity level. These movements are actions that " +
            "you perform in your day-to-day life, like squatting, pulling, pushing etc... " +
            "but be aware, this course is intended for experienced users.";
    private static final String COURSE_FITNESS_LEVEL = "Advanced";
    private static final String COURSE_EQUIPMENT = "No equipment is required, but be sure to bring a towel... " +
            "you are going to sweat a lot that's for sure!";
    private static final int COURSE_ID = 4;
    private static final String COURSE_NAME = "Crossfit";
    private static final String LOGGED_ATHLETE_USERNAME = "EdoMan000";
    private static final String LOGGED_ATHLETE_NAME = "Edoardo";
    private static final String LOGGED_ATHLETE_SURNAME = "Manenti";
    private static final LocalDate LOGGED_ATHLETE_BIRTH_DATE = LocalDate.of(2000, 11, 21);
    private static final String LOGGED_ATHLETE_FC = "MNNDRD00S21L719A";
    private static final char LOGGED_ATHLETE_GENDER = 'm';
    private static final String LOGGED_ATHLETE_CARD_NUMBER = "1234567887654321";
    private static final YearMonth LOGGED_ATHLETE_CARD_EXPIRATION_DATE = YearMonth.of(2026,11);
    private final CourseBean courseBean = new CourseBean(COURSE_ID, COURSE_NAME,
            COURSE_DESCRIPTION,
            COURSE_FITNESS_LEVEL,
            COURSE_OWNER_FC,
            COURSE_EQUIPMENT);
    private Athlete loggedAthlete;

    {
        try {
            loggedAthlete = new Athlete(
                    LOGGED_ATHLETE_USERNAME,
                    new PersonalInfo(LOGGED_ATHLETE_NAME,
                            LOGGED_ATHLETE_SURNAME,
                            LOGGED_ATHLETE_BIRTH_DATE,
                            LOGGED_ATHLETE_FC,
                            LOGGED_ATHLETE_GENDER),
                    new Credentials(EMAIL, PASSWORD),
                    new Card(LOGGED_ATHLETE_CARD_NUMBER,
                            LOGGED_ATHLETE_CARD_EXPIRATION_DATE));
        } catch (ExpiredCardException e) {
            e.printStackTrace();
        }
    }

    @Test void testSubscribeToCourse(){
        int flag = 0;
        SubscribeToCourseController subscribeToCourseController = new SubscribeToCourseController(loggedAthlete);
        try {
            subscribeToCourseController.subscribeToCourse(courseBean);
            List<CourseBean> loggedAthleteCourseList = subscribeToCourseController.getLoggedAthleteCourseList();
            for(CourseBean course: loggedAthleteCourseList){
                if (course.getId() == courseBean.getId()) {
                    flag = 1;
                    break;
                }
            }
        } catch (SQLException | DBUnreachableException | NoCardInsertedException | PaymentFailedException e) {
            /*
            * Per scelte progettuali, nel mocking del pagamento, il metodo 'pay' che simula il pagamento fallisce un quarto delle volte.
            * In caso di errore con valore di flag = -1 bisogna ripetere il test.
            * NB: DBUnreachableException si verifica in assenza di connessione internet.
            * Verificare quindi inoltre di essere connessi a internet prima di eseguire il test.
             */
            flag = -1;
            e.printStackTrace();
        }
        assertEquals(1, flag);
    }

    //TODO TEST DELL'UNSUBSCRIBE.

}
