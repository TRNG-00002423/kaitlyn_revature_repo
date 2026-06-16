package banking;

/** Checked — include shortfall. */
public class InsufficientFundsException extends Exception {

    public InsufficientFundsException(String message) {
        super(message);
    }

    public InsufficientFundsException(double shortfall) {
        super("Insufficient funds: " + shortfall + " more needed."); // call the parent Exception constructor
    }
}