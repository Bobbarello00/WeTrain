package test;

import engeneering.ExerciseCatalogue;
import models.Exercise;
import models.Trainer;
import models.record.Credentials;
import models.record.PersonalInfo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseCatalogueTest {
    /*@author Testing:  Andrea De Filippis
                        Matricola 0285448
    */

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

    @Test
    void removeExercise() {
        ArrayList<Exercise> list = new ArrayList<>(Arrays.asList(exerciseTest1, exerciseTest2));

        ExerciseCatalogue exerciseCatalogue = new ExerciseCatalogue(list);

        Exercise exerciseToRemove = exerciseTest1;

        exerciseCatalogue.removeExercise(exerciseToRemove);

        boolean flag = true;
        for(Exercise exercise: exerciseCatalogue.getExerciseList()) {
            if (exercise.getId() == exerciseToRemove.getId()) {
                flag = false;
                break;
            }
        }

        if(exerciseCatalogue.getExerciseList().size() == 2) {
            flag = false;
        }

        assertTrue(flag);
    }

    @Test
    void addExercise() {
        ArrayList<Exercise> list = new ArrayList<>(List.of(exerciseTest1));

        ExerciseCatalogue exerciseCatalogue = new ExerciseCatalogue(list);

        Exercise exerciseToAdd = exerciseTest1;

        exerciseCatalogue.addExercise(exerciseToAdd);

        boolean flag = false;
        for(Exercise exercise: exerciseCatalogue.getExerciseList()) {
            if (exercise.getId() == exerciseToAdd.getId()) {
                flag = true;
                break;
            }
        }

        assertTrue(flag);
    }
}