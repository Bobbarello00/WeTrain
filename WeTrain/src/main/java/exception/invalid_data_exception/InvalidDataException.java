package exception.invalid_data_exception;

import java.util.Arrays;
import java.util.List;

public abstract class InvalidDataException extends Exception{
    protected static String TITLE;
    protected static String HEADER;
    protected static String CONTENT;

    protected InvalidDataException(String title, String header, String content) {
        TITLE = title;
        HEADER = header;
        CONTENT = content;
    }

    protected List<String> getErrorStrings() {
        return Arrays.asList(TITLE, HEADER, CONTENT);
    }
}
