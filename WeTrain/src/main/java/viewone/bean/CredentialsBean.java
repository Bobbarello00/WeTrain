package viewone.bean;

import exception.InvalidCredentialsException;

import java.util.regex.Pattern;

public class CredentialsBean {
    private String email;
    private String password;

    public CredentialsBean(String email, String password, int a) {}

    public CredentialsBean(String email, String password) throws InvalidCredentialsException {
        setEmail(email);
        setPassword(password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws InvalidCredentialsException {
        if(isValidEmail(email)){
            this.email = email;
            return;
        }
        throw new InvalidCredentialsException();
    }

    private boolean isValidEmail(String email) {
        final Pattern emailRegex = Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", Pattern.CASE_INSENSITIVE);

        return emailRegex.matcher(email).matches();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws InvalidCredentialsException {
        if(isValidPassword(password)){
            this.password = password;
            return;
        }
        throw new InvalidCredentialsException();
    }

    private boolean isValidPassword(String password) {
        return Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@!#$%^&+=])(?=\\S+$).{8,45}$",password);
    }
}
