package viewone.bean;

public class AthleteBean extends UserBean{

    public AthleteBean() {
        super();
        super.type = "Athlete";
    }

    public AthleteBean(String username, String name, String surname, String fc) {
        super(username, name, surname, fc);
        super.type = "Athlete";
    }
}
