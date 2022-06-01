package exceptions.invalid_data_exception;

import exceptions.invalid_data_exception.InvalidDataException;

public class NoCardInsertedException extends InvalidDataException {
    public NoCardInsertedException(){
        super(
                "OOPS, SOMETHING WENT WRONG!",
                "No card Inserted!",
                "Be sure to insert a payment method, thanks for your collaboration!");
    }
}
