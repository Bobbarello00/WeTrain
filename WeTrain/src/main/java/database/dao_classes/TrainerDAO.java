package database.dao_classes;

import database.DatabaseConnectionSingleton;
import database.Queries;
import exception.DBConnectionFailedException;
import model.Athlete;
import model.Trainer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TrainerDAO {
    Connection conn = DatabaseConnectionSingleton.getInstance().getConn();

    public TrainerDAO() throws DBConnectionFailedException {
    }

    public void saveTrainer(Trainer trainer) throws SQLException {
        try(Statement stmt = conn.createStatement()){
            Queries.insertTrainer(stmt, trainer);
        }
    }

    public Trainer loadTrainer(String fc) throws SQLException, DBConnectionFailedException {
        try(Statement stmt = conn.createStatement(); ResultSet rs = Queries.loadUser(stmt, fc)) {
            if (rs.next()) {
                Trainer trainer = new Trainer(rs.getString("Name"),
                    rs.getString("Surname"),
                    rs.getString("Username"),
                    rs.getDate("Birth").toLocalDate(),
                    rs.getString("FC"),
                    rs.getString("Gender").charAt(0),
                    rs.getString("Email"),
                    rs.getString("Password")
                );
                try(ResultSet rs1 = Queries.loadTrainer(stmt, fc)) {
                    if (rs1.next()) {
                        trainer.setIban(rs1.getString("Iban"));
                        return trainer;
                    } else {
                        return null;
                    }
                }
            } else {
                return null;
            }
        }
    }

    public int getNumberOfSubscribers(String trainerFc) throws SQLException {
        try (Statement stmt = conn.createStatement(); ResultSet rs = Queries.countTrainerSubscribers(stmt, trainerFc)) {
            if(rs.next()){
                return rs.getInt(1);
            }else{
                return 0;
            }
        }
    }

    private List<Athlete> getSubscribersList(ResultSet rs) throws SQLException, DBConnectionFailedException {
        List<Athlete> subscriberList = new ArrayList<>();
        while(rs.next()){
            Athlete subscriber = new AthleteDAO().loadAthlete(rs.getString("User"));
            subscriberList.add(subscriber);
        }
        return subscriberList;
    }

    private List<Trainer> getTrainersList(ResultSet rs) throws SQLException, DBConnectionFailedException {
        List<Trainer> trainerList = new ArrayList<>();
        while(rs.next()){
            Trainer newTrainer = loadTrainer(rs.getString("User"));
            trainerList.add(newTrainer);
        }
        return trainerList;
    }

    public List<Trainer> searchTrainers(String name) throws SQLException, DBConnectionFailedException {
        try(Statement stmt = conn.createStatement(); ResultSet rs = Queries.searchTrainer(stmt, name)){
            return getTrainersList(rs);
        }
    }

    public List<Trainer> loadAllTrainers() throws SQLException, DBConnectionFailedException {
        try(Statement stmt = conn.createStatement(); ResultSet rs = Queries.loadAllTrainers(stmt)){
            return getTrainersList(rs);
        }
    }

    public List<Athlete> loadAllTrainerSubscribers(String trainerFc) throws SQLException, DBConnectionFailedException {
        try (Statement stmt = conn.createStatement(); ResultSet rs = Queries.loadAllTrainerSubscribers(stmt, trainerFc)) {
            return getSubscribersList(rs);
        }
    }

    public void updateIban(String iban, Trainer trainer) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            trainer.setIban(iban);
            Queries.updateIbanTrainer(stmt, trainer);
        }
    }
}
