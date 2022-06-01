package boundaries;

import exceptions.PaymentFailedException;
import exceptions.invalid_data_exception.NoCardInsertedException;
import exceptions.invalid_data_exception.NoIbanInsertedException;
import viewone.beans.PaymentBean;

public class PaypalSystemBoundary {

    public void pay(PaymentBean paymentBean) throws PaymentFailedException, NoCardInsertedException, NoIbanInsertedException {
        /*
         This is a dummy payment implementation
         */
        if (paymentBean.getCardNumber() ==null || paymentBean.getCardExpirationDate() == null) {
            throw new NoCardInsertedException();
        } else if(paymentBean.getIban() == null){
            throw new NoIbanInsertedException();
        } else if(paymentBean.getPriceToPay() <= 0){
            return;
        }
        if (Math.random() > 0.75) {
            throw new PaymentFailedException();/* <-- 1/4 of the time.*/
        }
    }
}
