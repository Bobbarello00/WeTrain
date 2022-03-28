package viewone.Bean;

public abstract class UserBean {
    private String username;
    private String name;
    private String surname;
    private String fc;

    public UserBean() {}

    public UserBean(String username, String name, String surname, String fc) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.fc = fc;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFc() {
        return fc;
    }

    public void setFc(String fc) {
        this.fc = fc;
    }
}
