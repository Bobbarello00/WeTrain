package viewone.bean;

import java.util.regex.Pattern;

public class CredentialsBean {
    private String email;
    private String password;

    public CredentialsBean() {}

    public CredentialsBean(String email, String password) {
        setEmail(email);
        setPassword(password);
    }

    public String getEmail() {
        return email;
    }

    public boolean setEmail(String email) {
        if(isValidEmail(email)){
            this.email = email;
            return true;
        }
        return false;
    }

    private boolean isValidEmail(String email) {
        final Pattern emailRegex = Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", Pattern.CASE_INSENSITIVE);

        return emailRegex.matcher(email).matches();
    }

    public String getPassword() {
        return password;
    }

    public boolean setPassword(String password) {
        if(isValidPassword(password)){
            this.password = password;
            return true;
        }
        return false;
    }

    private boolean isValidPassword(String password) {
        return Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@!#$%^&+=])(?=\\S+$).{8,45}$",password);
    }
}
