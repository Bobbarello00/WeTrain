package viewone.engeneering;

public class UserInfoCarrier {
    private String username;
    private String userType;
    private String fiscalCode;
    private char gender;


    public UserInfoCarrier(String username, String userType, String fiscalCode, char gender) {
        this.username = username;
        this.userType = userType;
        this.fiscalCode = fiscalCode;
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

    public String getFiscalCode() {
        return fiscalCode;
    }
}
