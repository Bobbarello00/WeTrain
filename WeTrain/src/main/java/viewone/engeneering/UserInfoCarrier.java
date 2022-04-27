package viewone.engeneering;

public class UserInfoCarrier {
    private String username;
    private String userType;
    private char gender;

    public UserInfoCarrier(String username, String userType, char gender) {
        this.username = username;
        this.userType = userType;
        this.gender = gender;
    }


    public String getUsername() {
        return username;
    }

    public String getUserType() {
        return userType;
    }

    public char getGender() {
        return gender;
    }
}
