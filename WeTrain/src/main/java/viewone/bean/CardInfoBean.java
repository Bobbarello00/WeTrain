package viewone.bean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CardInfoBean {
    private String cardNumber;
    private LocalDate expirationDate;
    private String type;

    public String getCardNumber() {
        return cardNumber;
    }

    public boolean setCardNumber(String cardNumber) {
        if(isValidCardNumber(cardNumber)) {
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
        Matcher matcher = pattern.matcher(card);
        if(matcher.matches()) {
            String group = "";
            if(matcher.group("visa") != null) group = "visa";
            else if(matcher.group("mastercard") != null) group = "mastercard";
            else if(matcher.group("discover") != null) group = "discover";
            else if(matcher.group("amex") != null) group = "amex";
            else if(matcher.group("diners") != null) group = "diners";
            else if(matcher.group("jcb") != null) group = "jcb";
            setType(group);
            return true;
        }
        System.out.println("numero carta sbagliata");
        return false;
    }

    private void setType(String type){
        this.type = type;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public boolean setExpirationDate(String expirationDate) {
        if(isValidDate(expirationDate)){
            this.expirationDate = LocalDate.parse(expirationDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            return true;
        }
        return false;
    }

    private boolean isValidDate(String myDateString) {
        //TODO FORMATO SOLO CON MESE E ANNO
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate ld = LocalDate.parse(myDateString, formatter);
            String result = ld.format(formatter);
            return result.equals(myDateString);
        } catch (DateTimeParseException exp) {
            exp.printStackTrace();
        }
        System.out.println("data carta sbagliata");
        return false;
    }
}
