package controllers;

import boundaries.EmailSystemBoundary;
import database.dao_classes.AthleteDAO;
import database.dao_classes.ExerciseDAO;
import database.dao_classes.RequestDAO;
import database.dao_classes.WorkoutPlanDAO;
import exceptions.BrowsingNotSupportedException;
import exceptions.DBUnreachableException;
import exceptions.ElementNotFoundException;
import models.*;
import org.jetbrains.annotations.NotNull;
import viewone.beans.*;
import engeneering.ExerciseCatalogue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SatisfyWorkoutRequestsController {

    private final NotificationsController notificationsController = new NotificationsController();
    private final WorkoutPlan workoutPlan;
    private final Trainer trainer;
    private final ExerciseCatalogue exerciseCatalogue;

    public SatisfyWorkoutRequestsController() throws DBUnreachableException, SQLException {
        workoutPlan = new WorkoutPlan();
        trainer = (Trainer) new LoginController().getLoggedUser();
        exerciseCatalogue = new ExerciseCatalogue(new ExerciseDAO().loadTrainerExercises(trainer));
    }

    public void rejectRequest(RequestBean requestBean) throws DBUnreachableException, SQLException {
        new RequestDAO().deleteRequest(requestBean.getId());
        notificationsController.sendRejectRequestNotification(requestBean.getAthleteBean().getFiscalCode());
    }

    private WorkoutDay getWorkoutDay(String day) {
        for(WorkoutDay workoutDay: workoutPlan.getWorkoutDayList()){
            if(Objects.equals(workoutDay.getDay(), day)) {
                return workoutDay;
            }
        }
        return null;
    }

    public void addExerciseToWorkoutDay(ExerciseForWorkoutPlanBean bean) throws SQLException, DBUnreachableException {
        WorkoutDay workoutDay = getWorkoutDay(bean.getDay());
        if(workoutDay == null) {
            workoutDay = new WorkoutDayObserver(
                    bean.getDay(),
                    exerciseCatalogue,
                    workoutPlan);
            workoutPlan.addWorkoutDay(workoutDay);
        }
        workoutDay.addExercise(new Exercise(
                bean.getId(),
                bean.getName(),
                bean.getInfo(),
                trainer
        ));
    }

    public void removeExerciseFromWorkoutDay(ExerciseForWorkoutPlanBean bean) throws ElementNotFoundException {
        WorkoutDay workoutDay = getWorkoutDay(bean.getDay());
        if(workoutDay == null){
            throw new ElementNotFoundException();
        }
        workoutDay.removeExercise(bean.getName(), bean.getInfo());
    }

    public void sendWorkoutPlan(RequestBean requestBean) throws DBUnreachableException, SQLException {
        Athlete receiver = new AthleteDAO().loadAthlete(requestBean.getAthleteBean().getFiscalCode());
        if(receiver.getWorkoutPlan() != null){
            new AthleteDAO().removeWorkoutPlan(receiver.getWorkoutPlan().getId());
        }
        new WorkoutPlanDAO().saveWorkoutPlan(
                this.workoutPlan,
                requestBean.getAthleteBean().getFiscalCode()
        );
        new RequestDAO().deleteRequest(requestBean.getId());
        notificationsController.sendWorkoutPlanReadyNotification(receiver);
    }

    public WorkoutDayBean getWorkoutDayBean(DayBean dayBean) {
        for(WorkoutDay workoutDay: workoutPlan.getWorkoutDayList()){
            if(Objects.equals(workoutDay.getDay(), dayBean.getDay())){
                WorkoutDayBean workoutDayBean = new WorkoutDayBean(workoutDay.getDay());
                for(Exercise exercise: workoutDay.getExerciseList()){
                    workoutDayBean.addExerciseBean(new ExerciseBean(
                            exercise.getName(),
                            exercise.getInfo())
                    );
                }
                return workoutDayBean;
            }
        }
        return new WorkoutDayBean(dayBean.getDay());
    }

    public List<RequestBean> getTrainerRequests() throws SQLException, DBUnreachableException {
        List<Request> requestList = new RequestDAO().loadTrainerRequests(trainer);
        List<RequestBean> requestBeanList = new ArrayList<>();
        for(Request request: requestList) {
            Athlete usr = request.getAthlete();
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
            requestBeanList.add(new RequestBean(
                    request.getId(),
                    request.getRequestDate(),
                    request.getInfo(),
                    athleteBean,
                    request.getTrainer().getFiscalCode()
            ));

        }
        return requestBeanList;
    }

    public List<ExerciseBean> searchExercise(SearchBean searchBean) {
        List<Exercise> exerciseList = new ArrayList<>();
        for(Exercise exercise: exerciseCatalogue.getExerciseList()) {
            if((exercise.getName().toLowerCase()).contains(searchBean.getName().toLowerCase())) {
                exerciseList.add(exercise);
            }
        }
        return getExerciseBeanList(exerciseList);
    }

    public void addExerciseToTrainer(ExerciseBean exerciseBean) throws SQLException, DBUnreachableException {
        Exercise exercise = new Exercise(
                exerciseBean.getName(),
                exerciseBean.getInfo(),
                trainer
        );
        exercise.setId(new ExerciseDAO().saveExercise(exercise));
        exerciseCatalogue.addExercise(exercise);
    }

    public void sendClarificationEmail(UserBean sender, UserBean receiver, String object, String content) throws BrowsingNotSupportedException, URISyntaxException, IOException, DBUnreachableException, SQLException {
        new EmailSystemBoundary().sendEmail(new EmailBean(
                sender,
                receiver,
                object,
                content
        ));
        notificationsController.sendEmailReceivedNotification(
                sender.getFiscalCode(),
                receiver.getFiscalCode()
        );
    }

    public List<ExerciseBean> getTrainerExercises() {
        return getExerciseBeanList(exerciseCatalogue.getExerciseList());
    }

    @NotNull private List<ExerciseBean> getExerciseBeanList(List<Exercise> exerciseList) {
        List<ExerciseBean> exerciseBeanList = new ArrayList<>();
        for(Exercise exercise: exerciseList){
            exerciseBeanList.add(new ExerciseBean(
                    exercise.getId(),
                    exercise.getName(),
                    exercise.getInfo()
            ));
        }
        return exerciseBeanList;
    }

    public void removeExerciseFromTrainer(ExerciseBean exerciseBean) throws DBUnreachableException, SQLException {
        Exercise exerciseToDelete = new Exercise(
                exerciseBean.getId(),
                exerciseBean.getName(),
                exerciseBean.getInfo(),
                trainer);
        new ExerciseDAO().removeExercise(exerciseToDelete);
        exerciseCatalogue.removeExercise(exerciseToDelete);
    }
}
