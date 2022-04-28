package viewone.bean;

import exception.InvalidCardInfoException;
import viewone.engeneering.FatalCaseManager;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CardInfoBean {
    private String cardNumber;
    private YearMonth expirationDate;
    private String type;

    public CardInfoBean(String cardNumber, String expirationDate) throws InvalidCardInfoException {
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

    private void setCardNumber(String cardNumber) throws InvalidCardInfoException {
        this.cardNumber = checkAndReturnValidCardNumber(cardNumber);
    }

    private String setType(String card) {
        String regex = "^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|" +
                "(?<mastercard>5[1-5][0-9]{14}))";
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

    private String checkAndReturnValidCardNumber(String card) throws InvalidCardInfoException {
        String cardNumber = setType(card);
        if(cardNumber == null) {
            System.out.printf("""
                    numero carta sbagliata -> %s
                    """, card);
            throw new InvalidCardInfoException();
        }
        return cardNumber;
    }

    public YearMonth getExpirationDate() {
        return expirationDate;
    }

    private void setExpirationDate(String expirationDate) throws InvalidCardInfoException {
        this.expirationDate = checkAndReturnValidDate(expirationDate);
    }

    private YearMonth checkAndReturnValidDate(String myDateString) throws InvalidCardInfoException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        try {
            return YearMonth.parse(myDateString, formatter);
        } catch (DateTimeParseException e) {
            System.out.printf("""
                    data carta sbagliata -> %s
                    """,myDateString);
            throw new InvalidCardInfoException();
        }
    }

    public String getType() {
        return type;
    }
}
