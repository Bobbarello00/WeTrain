package viewone.bean;

import exception.InvalidCardInfoException;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CardInfoBean {
    private String cardNumber;
    private YearMonth expirationDate;
    private String type;

    public CardInfoBean() {}

    public CardInfoBean(String cardNumber, YearMonth expirationDate) throws InvalidCardInfoException {
        setCardNumber(cardNumber);
        setExpirationDate(expirationDate.getMonthValue() + "/" + expirationDate.getYear());
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) throws InvalidCardInfoException {
        this.cardNumber = checkAndReturnValidCardNumber(cardNumber);
    }

    private String checkAndReturnValidCardNumber(String card) throws InvalidCardInfoException {
        String regex = "^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|" +
                "(?<mastercard>5[1-5][0-9]{14}))";
        Pattern pattern = Pattern.compile(regex);
        card = card.replace("-", "");
        card =card.replace(" ","");
        Matcher matcher = pattern.matcher(card);
        if(matcher.matches()) {
            String group = "";
            if(matcher.group("visa") != null) group = "VISA";
            else if(matcher.group("mastercard") != null) group = "MASTERCARD";
            setType(group);
            return card;
        }
        System.out.printf("""
                    numero carta sbagliata -> %s""",card);
        throw new InvalidCardInfoException();
    }

    public YearMonth getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) throws InvalidCardInfoException {
        this.expirationDate = checkAndReturnValidDate(expirationDate);
    }

    private YearMonth checkAndReturnValidDate(String myDateString) throws InvalidCardInfoException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        try {
            return YearMonth.parse(myDateString, formatter);
        } catch (DateTimeParseException e) {
            System.out.printf("""
                    data carta sbagliata -> %s""",myDateString);
            throw new InvalidCardInfoException();
        }
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
