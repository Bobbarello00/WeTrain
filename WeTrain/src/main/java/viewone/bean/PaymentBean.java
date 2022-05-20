package viewone.bean;

import java.time.YearMonth;
public class PaymentBean {

    private final String iban;
    private final String cardNumber;
    private final YearMonth cardExpirationDate;
    private final float priceToPay;

    public PaymentBean(String iban, String cardNumber, YearMonth cardExpirationDate, float priceToPay) {
        this.iban = iban;
        this.cardNumber = cardNumber;
        this.cardExpirationDate = cardExpirationDate;
        this.priceToPay = priceToPay;
    }

    public String getIban() {
        return iban;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public YearMonth getCardExpirationDate() {
        return cardExpirationDate;
    }

    public float getPriceToPay() {
        return priceToPay;
    }
}