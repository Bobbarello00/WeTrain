package exception;

import java.util.Arrays;
import java.util.List;

public class PersonalizedException extends Exception{
    protected static String TITLE;
    protected static String HEADER;
    protected static String CONTENT;

    protected PersonalizedException(String title, String header, String content) {
        TITLE = title;
        HEADER = header;
        CONTENT = content;
    }

    public List<String> getErrorStrings() {
        return Arrays.asList(TITLE, HEADER, CONTENT);
    }
}
