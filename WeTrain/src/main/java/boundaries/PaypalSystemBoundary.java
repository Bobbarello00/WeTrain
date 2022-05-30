package boundaries;

import exceptions.PaymentFailedException;
import viewone.beans.PaymentBean;

public class PaypalSystemBoundary {

    public void pay(PaymentBean paymentBean) throws PaymentFailedException {
        /*
         This is a dummy payment implementation
         */
        if(paymentBean.getCardNumber() == null || paymentBean.getCardExpirationDate() == null || paymentBean.getIban() == null || paymentBean.getPriceToPay() == -1){
            throw new PaymentFailedException();
        }
        if (Math.random() > 0.75) {
            throw new PaymentFailedException();/* <-- 1/4 of the time.*/
        }
    }
}
