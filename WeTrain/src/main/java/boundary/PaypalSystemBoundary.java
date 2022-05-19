package boundary;

import exception.PaymentFailedException;

import java.time.YearMonth;

public class PaypalSystemBoundary {

    public void pay(String iban, String cardNumber, YearMonth cardExpirationDate, float subscriptionFee) throws PaymentFailedException {
        /*
         This is a dummy payment implementation
         */
        if(cardNumber == null || cardExpirationDate == null || iban == null || subscriptionFee == -1){
            throw new PaymentFailedException();
        }
        if (Math.random() > 0.75) {
            throw new PaymentFailedException();/* <-- 1/4 of the time.*/
        }
    }
}
