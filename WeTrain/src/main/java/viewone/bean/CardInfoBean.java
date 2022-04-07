package viewone.bean;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CardInfoBean {
    private String cardNumber;
    private YearMonth expirationDate;
    private String type;

    public CardInfoBean() {}

    public CardInfoBean(String cardNumber, YearMonth expirationDate) {
        setCardNumber(cardNumber);
        this.expirationDate = expirationDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public boolean setCardNumber(String cardNumber) {
        if(isValidCardNumber(cardNumber)) {
            cardNumber = cardNumber.replaceAll("-", "");
            cardNumber =cardNumber.replaceAll(" ","");
            this.cardNumber = cardNumber;
            return true;
        }
        return false;
    }

    private boolean isValidCardNumber(String card) {
        String regex = "^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|" +
                "(?<mastercard>5[1-5][0-9]{14})|" +
                "(?<discover>6(?:011|5[0-9]{2})[0-9]{12})|" +
                "(?<amex>3[47][0-9]{13})|" +
                "(?<diners>3(?:0[0-5]|[68][0-9])?[0-9]{11})|" +
                "(?<jcb>(?:2131|1800|35[0-9]{3})[0-9]{11}))$";
        Pattern pattern = Pattern.compile(regex);
        card = card.replaceAll("-", "");
        card =card.replaceAll(" ","");
        Matcher matcher = pattern.matcher(card);
        if(matcher.matches()) {
            String group = "";
            if(matcher.group("visa") != null) group = "VISA";
            else if(matcher.group("mastercard") != null) group = "MASTERCARD";
            else if(matcher.group("discover") != null) group = "DISCOVER";
            else if(matcher.group("amex") != null) group = "AMEX";
            else if(matcher.group("diners") != null) group = "DINERS";
            else if(matcher.group("jcb") != null) group = "JCB";
            setType(group);
            return true;
        }
        System.out.println("numero carta sbagliata");
        return false;
    }

    public YearMonth getExpirationDate() {
        return expirationDate;
    }

    public boolean setExpirationDate(String expirationDate) {
        if(isValidDate(expirationDate)){
            this.expirationDate = YearMonth.parse(expirationDate, DateTimeFormatter.ofPattern("MM/yyyy"));
            return true;
        }
        return false;
    }

    private boolean isValidDate(String myDateString) {
        //TODO FORMATO SOLO CON MESE E ANNO
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        try {
            YearMonth yearMonth = YearMonth.parse(myDateString, formatter);
            String result = yearMonth.format(formatter);
            return result.equals(myDateString);
        } catch (DateTimeParseException exp) {
            exp.printStackTrace();
        }
        System.out.println("data carta sbagliata");
        return false;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
