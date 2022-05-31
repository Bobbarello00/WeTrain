package test;

import controllers.LoginController;
import exceptions.DBUnreachableException;
import exceptions.UserNotFoundException;
import org.junit.jupiter.api.Test;
import viewone.beans.AthleteBean;
import viewone.beans.CredentialsBean;

import java.sql.SQLException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class TestLoginController {
    private static final String EMAIL = "edo@gmail.com";
    private static final String PASSWORD = "Ciaociao00!";

    /*@author Testing:  Edoardo Manenti
                        Matricola 0278821
    */

    @Test void testLogin() {
        /*
        * Nel database è stato precedentemente registrato l'atleta
        *  con email 'edo@gmail.com' e password 'Ciaociao00!'.
        * Lo scopo del test è di verificare se il login con tali credenziali
        *  vada effettivamente a buon fine e restituisca l'atleta corretto.
         */
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
            * Verificare di essere connessi a internet prima di eseguire il test.
             */
            flag = -1;
            e.printStackTrace();
        }
        assertEquals(1, flag);
    }

}