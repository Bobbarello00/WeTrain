package exceptions.invalid_data_exception;

public class NoIbanInsertedException extends InvalidDataException{

    public NoIbanInsertedException() {
        super(
                "OOPS, SUBSCRIPTIONS ARE CLOSED",
                "The course owner hasn't inserted any iban",
                "Wait until he does, thanks for your patience!");
    }
}
