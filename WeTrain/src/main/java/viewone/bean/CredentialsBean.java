package viewone.bean;

import exception.InvalidCredentialsException;

import java.util.regex.Pattern;

public class CredentialsBean {
    private String email;
    private String password;

    private CredentialsBean() {}

    public static CredentialsBean ctorWithSyntaxCheck(String email, String password) throws InvalidCredentialsException {
        /*This is a constructor with syntax check and is used by view*/
        CredentialsBean credentialsBean = new CredentialsBean();
        credentialsBean.setEmail(email);
        credentialsBean.setPassword(password);
        return credentialsBean;
    }

    public static CredentialsBean ctorWithoutSyntaxCheck(String email, String password) {
        /*This is a constructor without syntax check and is used by controller*/
        CredentialsBean credentialsBean = new CredentialsBean();
        credentialsBean.email = email;
        credentialsBean.password = password;
        return credentialsBean;
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
