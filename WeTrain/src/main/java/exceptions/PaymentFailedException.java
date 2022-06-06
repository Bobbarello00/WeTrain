package exceptions;

public class PaymentFailedException extends PersonalizedException{

    public PaymentFailedException() {
        super(
                "PAYMENT FAILED",
                "Paypal System couldn't complete the payment.",
                "try again later..."
        );
    }
}
