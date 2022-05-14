package exception;

public class PaymentFailedException extends PersonalizedException{

    public PaymentFailedException() {
        super(
                "PAYMENT ERROR",
                "Payment failed",
                "Couldn't complete the payment. Try again later..."
        );
    }
}
