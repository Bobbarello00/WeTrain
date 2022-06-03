package engineering;

import beans.*;
import controllers.LoginController;
import exceptions.DBUnreachableException;
import exceptions.invalid_data_exception.NoCardInsertedException;
import exceptions.runtime_exception.FatalErrorException;
import models.Athlete;
import models.Trainer;
import models.User;

import java.sql.SQLException;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class LoggedUserSingleton {

    private static String fc;
    private static UserInfoCarrier userInfoCarrier;
    private static final LoginController loginController = new LoginController();

    private LoggedUserSingleton() {}

    public static String getFc() {
        return fc;
    }

    public static void setFc(String fc) {
        LoggedUserSingleton.fc = fc;
    }

    public static UserInfoCarrier getUserInfo() throws DBUnreachableException, NoCardInsertedException {
        if(userInfoCarrier == null){
            UserBean userBean = getInstance();
            userInfoCarrier = new UserInfoCarrier(
                    Objects.requireNonNull(userBean).getUsername(),
                    userBean.getType(),
                    userBean.getFiscalCode(),
                    userBean.getGender());
        }
        return userInfoCarrier;
    }

    public static List<UserBean> getAthleteAndTrainer() throws DBUnreachableException, SQLException, NoCardInsertedException {
        Athlete usr = (Athlete) loginController.getLoggedUser();
        Trainer trainer = usr.getTrainer();
        AthleteBean athleteBean = new AthleteBean(
                usr.getUsername(),
                new PersonalInfoBean(
                        usr.getName(),
                        usr.getSurname(),
                        usr.getDateOfBirth(),
                        usr.getFiscalCode(),
                        usr.getGender()
                ),
                CredentialsBean.ctorWithoutSyntaxCheck(
                        usr.getEmail(),
                        usr.getPassword()
                ),
                new CardInfoBean(
                        usr.getCardNumber(),
                        usr.getCardExpirationDate()
                ));
        TrainerBean trainerBean = new TrainerBean(
                trainer.getUsername(),
                new PersonalInfoBean(
                        trainer.getName(),
                        trainer.getSurname(),
                        trainer.getDateOfBirth(),
                        trainer.getFiscalCode(),
                        trainer.getGender()
                ),
                CredentialsBean.ctorWithoutSyntaxCheck(
                        trainer.getEmail(),
                        trainer.getPassword()
                ),
                trainer.getIban());
        return Arrays.asList(athleteBean, trainerBean);
    }

    public static UserBean getUserBean(User usr){
        if (usr instanceof Athlete) {
            try {
                return new AthleteBean(
                        usr.getUsername(),
                        new PersonalInfoBean(
                                usr.getName(),
                                usr.getSurname(),
                                usr.getDateOfBirth(),
                                usr.getFiscalCode(),
                                usr.getGender()
                        ),
                        CredentialsBean.ctorWithoutSyntaxCheck(
                                usr.getEmail(),
                                usr.getPassword()
                        ),
                        new CardInfoBean(
                                ((Athlete) usr).getCardNumber(),
                                ((Athlete) usr).getCardExpirationDate()
                        ));
            } catch (NoCardInsertedException e) {
                return new AthleteBean(
                        usr.getUsername(),
                        new PersonalInfoBean(
                                usr.getName(),
                                usr.getSurname(),
                                usr.getDateOfBirth(),
                                usr.getFiscalCode(),
                                usr.getGender()
                        ),
                        CredentialsBean.ctorWithoutSyntaxCheck(
                                usr.getEmail(),
                                usr.getPassword()
                        ),
                        new CardInfoBean(
                                null, (YearMonth) null
                        ));
            }
        } else {
            return new TrainerBean(
                    usr.getUsername(),
                    new PersonalInfoBean(
                            usr.getName(),
                            usr.getSurname(),
                            usr.getDateOfBirth(),
                            usr.getFiscalCode(),
                            usr.getGender()
                    ),
                    CredentialsBean.ctorWithoutSyntaxCheck(
                            usr.getEmail(),
                            usr.getPassword()
                    ),
                    ((Trainer) usr).getIban());
        }
    }

    public static UserBean getInstance() throws DBUnreachableException{
        try{
            User usr = loginController.getLoggedUser();
            return getUserBean(usr);
        } catch (SQLException e){
            e.printStackTrace();
            throw new FatalErrorException();
        }
    }

    public static void resetUserInfo() {
        userInfoCarrier = null;
    }
}
