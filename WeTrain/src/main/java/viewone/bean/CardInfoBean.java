package viewone.bean;

import exception.invalid_data_exception.EmptyFieldsException;
import exception.invalid_data_exception.InvalidCardInfoException;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CardInfoBean {
    private String cardNumber;
    private YearMonth expirationDate;
    private String type;

    public CardInfoBean(String cardNumber, String expirationDate) throws InvalidCardInfoException, EmptyFieldsException {
        /*This is a constructor with syntax check and is used by view*/
        setCardNumber(cardNumber);
        setExpirationDate(expirationDate);
    }

    public CardInfoBean(String cardNumber, YearMonth expirationDate) {
        /*This is a constructor without syntax check and is used by controller*/
        if(cardNumber != null){
            this.cardNumber = setType(cardNumber);
        } else {
            this.cardNumber = null;
        }
        this.expirationDate = expirationDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    private void setCardNumber(String cardNumber) throws InvalidCardInfoException, EmptyFieldsException {
        this.cardNumber = checkAndReturnValidCardNumber(cardNumber);
    }

    private String setType(String card) {
        String regex = "^(?:(?<visa>4\\d{12}(?:\\d{3})?)|" +
                "(?<mastercard>5[1-5]\\d{14}))";
        Pattern pattern = Pattern.compile(regex);
        card = card.replace("-", "");
        card = card.replace(" ","");
        Matcher matcher = pattern.matcher(card);
        if(matcher.matches()) {
            String group = "";
            if(matcher.group("visa") != null) group = "VISA";
            else if(matcher.group("mastercard") != null) group = "MASTERCARD";
            this.type = group;
            return card;
        }
        return null;
    }

    private String checkAndReturnValidCardNumber(String card) throws InvalidCardInfoException, EmptyFieldsException {
        if(card.isEmpty()){
            throw new EmptyFieldsException();
        }
        String number = setType(card);
        if(number == null) {
            throw new InvalidCardInfoException();
        }
        return number;
    }

    public YearMonth getExpirationDate() {
        return expirationDate;
    }

    private void setExpirationDate(String expirationDate) throws InvalidCardInfoException, EmptyFieldsException {
        this.expirationDate = checkAndReturnValidDate(expirationDate);
    }

    private YearMonth checkAndReturnValidDate(String myDateString) throws InvalidCardInfoException, EmptyFieldsException {
        if(myDateString.isEmpty()){
            throw new EmptyFieldsException();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        try {
            return YearMonth.parse(myDateString, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidCardInfoException();
        }
    }

    public String getType() {
        return type;
    }
}
