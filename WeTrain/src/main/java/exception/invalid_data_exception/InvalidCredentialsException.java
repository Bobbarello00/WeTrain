package exception.invalid_data_exception;

import viewone.engeneering.AlertFactory;

public class InvalidCredentialsException extends InvalidDataException{

    @Override public void alert() {
        AlertFactory.newWarningAlert("WARNING!",
                "Email or password not inserted or mistyped.",
                "Be sure to fill all fields correctly, thanks.\n " +
                        "Remember that the password must contain between eight and 45 characters," +
                        " at least one number and both lower and uppercase letters and " +
                        "special characters (e.g. @!#$%^&+=) and must not contain tabs or spaces");
    }
}
