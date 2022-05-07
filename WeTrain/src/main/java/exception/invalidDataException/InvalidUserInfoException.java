package exception.invalidDataException;

import viewone.engeneering.AlertFactory;

public class InvalidUserInfoException extends InvalidDataException{

    @Override public void alert() {
        AlertFactory.newWarningAlert("OOPS, SOMETHING WENT WRONG!",
                "Excessive length in name, surname or username",
                "Be sure that name or surname are under 45 characters and username is under 20 characters, thanks for your collaboration!");
    }
}
