package database.dao_classes;

import database.Queries;
import exception.DBConnectionFailedException;
import model.Athlete;
import model.Trainer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainerDAO {

    public TrainerDAO() {}

    public void saveTrainer(Trainer trainer) throws SQLException, DBConnectionFailedException {
        Queries.insertTrainer(trainer);
    }

    public Trainer loadTrainer(String fc) throws SQLException, DBConnectionFailedException {
        try(PreparedStatement preparedStatement = Queries.loadUser(fc)) {
            ResultSet rs = preparedStatement.executeQuery();
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
                try(ResultSet rs1 = Queries.loadTrainer(fc)) {
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

    public int getNumberOfSubscribers(String trainerFc) throws SQLException, DBConnectionFailedException {
        try (ResultSet rs = Queries.countTrainerSubscribers(trainerFc)) {
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
        return getTrainersList(Queries.searchTrainer(name));
    }

    public List<Trainer> loadAllTrainers() throws SQLException, DBConnectionFailedException {
        return getTrainersList(Queries.loadAllTrainers());
    }

    public List<Athlete> loadAllTrainerSubscribers(String trainerFc) throws SQLException, DBConnectionFailedException {
        return getSubscribersList(Queries.loadAllTrainerSubscribers(trainerFc));
    }

    public void updateIban(String iban, Trainer trainer) throws SQLException, DBConnectionFailedException {
        trainer.setIban(iban);
        Queries.updateIbanTrainer(trainer);
    }
}
