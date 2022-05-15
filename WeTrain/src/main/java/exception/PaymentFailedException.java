package exception;

public class PaymentFailedException extends PersonalizedException{

    public PaymentFailedException() {
        super(
                "PAYMENT FAILED",
                "Couldn't complete the payment.",
                "Be sure to have set a payment method and try again later...\n" +
                        "(It is also possible that the Trainer hasn't inserted his iban yet)"
        );
    }
}
