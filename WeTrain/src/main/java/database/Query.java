package database;

import model.*;

import java.sql.*;
import java.util.List;

public class Query {

    //TODO gestione duplicate record
    public static ResultSet loadAthlete(Statement stmt, String fc) throws SQLException {
        return stmt.executeQuery(String.format("SELECT *;" +
                "FROM Athlete;" +
                "where fc = '%s'", fc));
    }

    public static int insertAthlete(Statement stmt, Athlete athlete) throws SQLException {
        return stmt.executeUpdate(String.format("INSERT INTO mydb.Athlete (FC, Name, Surname, Birth, Email) VALUES ('%s', '%s', '%s', '%s', '%s');", athlete.getFiscalCode(), athlete.getName(), athlete.getSurname(), Date.valueOf(athlete.getDateOfBirth()), athlete.getEmail()));
    }

    public static int insertCardInfoAthlete(Statement stmt, Athlete athlete) throws SQLException {
        return stmt.executeUpdate(String.format("UPDATE ATHLETE SET CardNumber = '%s', CardExpirationDate = '%s' WHERE Fc = '%s';", athlete.getCardNumber(), Date.valueOf(athlete.getCardExpirationDate()), athlete.getFiscalCode()));
    }

    public static ResultSet loadTrainer(Statement stmt, String fc) throws SQLException {
        return stmt.executeQuery(String.format("SELECT *;" +
                "FROM Trainer;" +
                "where fc = '%s'", fc));
    }

    public static int insertTrainer(Statement stmt, Trainer trainer) throws SQLException {
        return stmt.executeUpdate(String.format("INSERT INTO mydb.Trainer (FC, Name, Surname, Birth, Email) VALUES ('%s', '%s', '%s', '%s', '%s');", trainer.getFiscalCode(), trainer.getName(), trainer.getSurname(), Date.valueOf(trainer.getDateOfBirth()), trainer.getEmail()));
    }

    public static int insertIbanTrainer(Statement stmt, Trainer trainer) throws SQLException {
        return stmt.executeUpdate(String.format("UPDATE TRAINER SET Iban = '%s' WHERE Fc = '%s';", trainer.getIban(), trainer.getFiscalCode()));
    }

    public static ResultSet loadCourse(Statement stmt, int id) throws SQLException {
        return stmt.executeQuery(String.format("SELECT *;" +
                "FROM Course;" +
                "where idCourse = %s", id));
    }

    public static int insertCourse(Statement stmt, Course course) throws SQLException {
        return stmt.executeUpdate(String.format("INSERT INTO mydb.Course (Name, Description, FitnessLevel, Equipment, Trainer) VALUES ('%s', '%s', '%s', '%s', '%s');", course.getName(), course.getDescription(), course.getFitnessLevel(), course.getEquipment(), course.getOwner().getFiscalCode()));
    }

    public static ResultSet loadExercise(Statement stmt, int idExercise) throws SQLException {
        return stmt.executeQuery(String.format("SELECT *;" +
                "FROM Exercise;" +
                "where idExercise = %s", idExercise));
    }

    public static int insertExercise(Statement stmt, Exercise exercise) throws SQLException {
        return stmt.executeUpdate(String.format("INSERT INTO mydb.Exercise (Name, Information, Trainer) VALUES ('%s', '%s', '%s');", exercise.getName(), exercise.getInfo(), exercise.getTrainer().getFiscalCode()));
    }

    public static int insertLesson(Statement stmt, Lesson lesson) throws SQLException {
        return stmt.executeUpdate(String.format("INSERT INTO mydb.Lesson (LessonDay, Course) VALUES ('%s', %s);", Timestamp.valueOf(lesson.getLessonDate()), lesson.getCourse().getId()));
    }

    public static int insertNotification(Statement stmt, Notification notification) throws SQLException {
        if(notification.getUser() instanceof Athlete) {
            return stmt.executeUpdate(String.format("INSERT INTO mydb.Notification (Type, Description, NotificationDate, Athlete) " +
                    "VALUES (%s,'%s','%s','%s');", notification.getType(), notification.getDescription(), Timestamp.valueOf(notification.getNotificationDate()), notification.getUser()));
        }
         else {
            return stmt.executeUpdate(String.format("INSERT INTO mydb.Notification (Type, Description, NotificationDate, Trainer) " +
                    "VALUES (%s,'%s','%s','%s');", notification.getType(), notification.getDescription(), Timestamp.valueOf(notification.getNotificationDate()), notification.getUser()));
        }
    }

    public static int insertRequest(Statement stmt, Request request) throws SQLException {
        return stmt.executeUpdate(String.format("INSERT INTO mydb.Request (RequestDate, Athlete, Trainer) VALUES ('%s', '%s', '%s');", Timestamp.valueOf(request.getRequestDate()), request.getAthlete().getFiscalCode(), request.getTrainer().getFiscalCode()));
    }

    private static int addExerciseToWorkoutDay(Statement stmt, Exercise exercise, int workoutDayKey) throws SQLException{
        return stmt.executeUpdate(String.format("INSERT INTO mydb.Contains (WorkoutDay, Exercise) VALUES (%s, %s);", workoutDayKey, exercise.getId()));
    }

    private static int addWorkoutDayToWorkoutPlan(Statement stmt, WorkoutDay workoutDay, int workoutPlanKey) throws SQLException{
        try (PreparedStatement statement2 = DatabaseConnection.getInstance().conn.prepareStatement(String.format("INSERT INTO mydb.WorkoutDay (WorkoutPlan) VALUES (%s)", workoutPlanKey), Statement.RETURN_GENERATED_KEYS);) {
            statement2.executeUpdate();
            try (ResultSet generatedKeys2 = statement2.getGeneratedKeys()) {
                if (generatedKeys2.next()) {
                    int workoutDayKey = generatedKeys2.getInt(1);
                    List<Exercise> exerciseList = workoutDay.getListExercise();
                    for (Exercise exercise : exerciseList) {
                        addExerciseToWorkoutDay(stmt, exercise, workoutDayKey);
                    }
                }
            }
        }
        return 0;
    }

    public static int insertWorkoutPlan(Statement stmt, WorkoutPlan workoutPlan) throws SQLException {

        try(PreparedStatement statement = DatabaseConnection.getInstance().conn.prepareStatement(String.format("INSERT INTO mydb.WorkoutPlan (Athlete) VALUES ('%s');", workoutPlan.getAthlete().getFiscalCode()), Statement.RETURN_GENERATED_KEYS);) {
            int ret = statement.executeUpdate();
            Integer workoutPlanKey = null;
            try (ResultSet generatedKeys = statement.getGeneratedKeys();) {
                if (generatedKeys.next()) {
                    workoutPlanKey = generatedKeys.getInt(1);

                    List<WorkoutDay> workoutDayList = workoutPlan.getWorkoutDayList();
                    for (WorkoutDay workoutDay : workoutDayList) {
                        addWorkoutDayToWorkoutPlan(stmt, workoutDay, workoutPlanKey);
                    }
                }
            }
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return 0;
    }

    public static int insertSubscribe(Statement stmt, Course course, Athlete athlete) throws SQLException {
        return stmt.executeUpdate(String.format("INSERT INTO mydb.Subscribe (Course, Athlete) VALUES (%s, '%s');", course.getId(), athlete.getFiscalCode()));
    }

}
