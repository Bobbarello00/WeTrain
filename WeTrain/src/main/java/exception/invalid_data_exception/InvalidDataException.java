package exception.invalid_data_exception;

import exception.PersonalizedException;

public abstract class InvalidDataException extends PersonalizedException {

    protected InvalidDataException(String title, String header, String content) {
        super(title, header, content);
    }
}
