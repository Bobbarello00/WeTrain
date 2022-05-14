package exception.invalid_data_exception;

public class InvalidCredentialsException extends InvalidDataException{

    public InvalidCredentialsException() {
        super(
                "WARNING!",
                "Email or password not inserted or mistyped.",
                "Remember that the password must contain between eight and 45 characters," +
                        " at least one number and both lower and uppercase letters and " +
                        "special characters (e.g. @!#$%^&+=) and must not contain tabs or spaces"
        );
    }
}
