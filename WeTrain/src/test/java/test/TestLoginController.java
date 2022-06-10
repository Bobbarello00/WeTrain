package test;

import controllers.LoginController;
import exceptions.DBUnreachableException;
import exceptions.UserNotFoundException;
import org.junit.jupiter.api.Test;
import beans.AthleteBean;
import beans.CredentialsBean;

import java.sql.SQLException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class TestLoginController {

    /**Author of the test:  Edoardo Manenti
     *                  Matricola 0278821
     */

    private static final String EMAIL = "edo@gmail.com";
    private static final String PASSWORD = "Ciaociao00!";

    /**
     * Nel database è stato precedentemente registrato l'atleta
     *  con email 'edo@gmail.com' e password 'Ciaociao00!'.
     * Lo scopo del test è di verificare se il login con tali credenziali
     *  vada effettivamente a buon fine e restituisca l'atleta corretto.
     */
    @Test void testLogin() {
        int flag = 1;
        LoginController loginController = new LoginController();
        try {
            CredentialsBean credentialsBean = CredentialsBean.ctorWithoutSyntaxCheck(EMAIL, PASSWORD);
            AthleteBean returnedAthlete = (AthleteBean) loginController.login(credentialsBean);
            if(!Objects.equals(returnedAthlete.getEmail(), credentialsBean.getEmail()) || !Objects.equals(returnedAthlete.getPassword(), credentialsBean.getPassword())){
                flag = 0;
            }
        } catch (SQLException | DBUnreachableException | UserNotFoundException e) {
            /*
             * NB: DBUnreachableException si verifica in assenza di connessione internet.
             * Quindi in caso di errore (valore di flag = -1) verificare
             * di essere connessi a internet prima di rieseguire il test.
             */
            flag = -1;
            e.printStackTrace();
        }
        assertEquals(1, flag);
    }

}