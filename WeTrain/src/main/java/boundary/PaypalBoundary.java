package boundary;

import exception.PaymentFailedException;

import java.time.YearMonth;

public class PaypalBoundary {

    public void pay(String iban, String cardNumber, YearMonth cardExpirationDate, float subscriptionFee) throws PaymentFailedException {
        /*
        This is a dummy payment implementation
         */
        String string = iban + cardNumber + cardExpirationDate + subscriptionFee + (Math.random()*100);
        if(string.length() % 2 == 0) {
            throw new PaymentFailedException();
        }
    }
}
