package viewone.bean;

import exception.InvalidCardInfoException;
import viewone.AlertFactory;

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

    public boolean setCardNumber(String cardNumber) throws InvalidCardInfoException {
        if(isValidCardNumber(cardNumber)) {
            cardNumber = cardNumber.replace("-", "");
            cardNumber =cardNumber.replace(" ","");
            this.cardNumber = cardNumber;
            return true;
        }
        return false;
    }

    private boolean isValidCardNumber(String card) throws InvalidCardInfoException {
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
            return true;
        }
        System.out.println("numero carta sbagliata");
        throw new InvalidCardInfoException();
    }

    public YearMonth getExpirationDate() {
        return expirationDate;
    }

    public boolean setExpirationDate(String expirationDate) throws InvalidCardInfoException {
        if(isValidDate(expirationDate)){
            this.expirationDate = YearMonth.parse(expirationDate, DateTimeFormatter.ofPattern("MM/yyyy"));
            return true;
        }
        return false;
    }

    private boolean isValidDate(String myDateString) throws InvalidCardInfoException {
        //TODO FORMATO SOLO CON MESE E ANNO
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        try {
            YearMonth yearMonth = YearMonth.parse(myDateString, formatter);
            String result = yearMonth.format(formatter);
            return result.equals(myDateString);
        } catch (DateTimeParseException e) {
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
