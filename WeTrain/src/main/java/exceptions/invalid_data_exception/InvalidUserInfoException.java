package exceptions.invalid_data_exception;

public class InvalidUserInfoException extends InvalidDataException{

    public InvalidUserInfoException() {
        super(
                "OOPS, SOMETHING WENT WRONG!",
                "Excessive length in name, surname or username",
                "Be sure that name or surname are under 45 characters and username is under 20 characters, thanks for your collaboration!"
        );
    }
}
