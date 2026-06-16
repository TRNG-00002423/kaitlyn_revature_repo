package banking;

public class Account {
    private String id;
    private double balance;

    public Account(String id) {
        this.id = id;
    }

    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot deposit a negative amount.");
        }
        this.balance += amount;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot withdraw a negative amount.");
        }
        if (amount > this.balance) {
            throw new InsufficientFundsException(amount - this.balance);
        }
        this.balance -= amount;
    }

}