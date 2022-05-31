package test;

import controllers.SatisfyWorkoutRequestsController;
import engeneering.ExerciseCatalogue;
import exceptions.DBUnreachableException;
import models.Exercise;
import models.Trainer;
import models.record.Credentials;
import models.record.PersonalInfo;
import org.junit.jupiter.api.Test;
import viewone.beans.ExerciseBean;
import viewone.beans.SearchBean;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SatisfyWorkoutRequestsControllerTest {

    Trainer trainerTest = new Trainer(
            "andreaxdf",
            new PersonalInfo(
                    "Andrea",
                    "De Filippis",
                    LocalDate.of(2000, 5, 21),
                    "DFLNDR00E21D662Q",
                    'm'
            ),
            new Credentials(
                    "andrea@gmail.com",
                    "Ciao1!"
            )
    );

    Exercise exerciseTest1 = new Exercise(
            1,
            "Addominali",
            "Il crunch viene eseguito stendendosi in posizione supina, " +
                    "con le gambe divaricate all'altezza dei fianchi e in posizione " +
                    "flessa, sollevando il busto in direzione del bacino che invece " +
                    "deve restare in saldo appoggio. Durante l'esecuzione, le braccia " +
                    "possono essere posizionate lungo il dorso, attraverso il petto, o " +
                    "accanto alla testa con le dita delle mani che sfiorano le tempie. " +
                    "Nella fase in cui il busto viene sollevato si ha la contrazione dei " +
                    "muscoli addominali, mentre in fase di discesa questi muscoli si allungano.",
            trainerTest
    );

    Exercise exerciseTest2 = new Exercise(
            2,
            "Piegamenti",
            "Per essere eseguiti correttamente, bisogna tenere le scapole in " +
                    "adduzione e i gomiti abdotti di 45° gradi, le mani devono essere " +
                    "all'altezza del petto e ad un'ampiezza leggermente superiore a quella " +
                    "delle spalle. Durante il piegamento stesso il bacino deve essere fermo, " +
                    "mentre è necessario contrarre il core addominale e i glutei. " +
                    "Le gambe vanno tenute distese.",
            trainerTest
    );

    ExerciseCatalogue exerciseCatalogueTest = new ExerciseCatalogue(new ArrayList<>(Arrays.asList(exerciseTest1, exerciseTest2)));

    @Test
    void searchExercise() {
        SatisfyWorkoutRequestsController controller = new SatisfyWorkoutRequestsController(trainerTest, exerciseCatalogueTest);

        Exercise exerciseToSearch = exerciseTest2;

        SearchBean searchBean = new SearchBean(exerciseToSearch.getName());

        List<ExerciseBean> exerciseBeanList = controller.searchExercise(searchBean);

        boolean flag = true;
        if(exerciseBeanList.get(0).getId() != exerciseToSearch.getId())
    }
}