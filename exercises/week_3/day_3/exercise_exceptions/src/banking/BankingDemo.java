package banking;

public class BankingDemo {
    public static void main(String[] args) throws Exception {
        // demonstrate success path + catch InsufficientFundsException +
        // InvalidAccountException
        // trigger IllegalArgumentException on bad deposit
        Bank veryRealBank = new Bank();

        // Scarlett successfully opens an account with a $900 initial deposit.
        try {
            veryRealBank.openAccount("Scarlett", 900);
            System.out.println("Successfully opened Scarlett's account!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error opening Scarlett's account.");
            System.out.println(e);
        }

        // fail to open account with negative initial balance
        // this will throw an IllegalArgumentException
        try {
            veryRealBank.openAccount("Clementine", -6);
            System.out.println("Successfully opened Clementine's account!"); // unreachable
        } catch (IllegalArgumentException e) {
            System.out.println("Error opening Clementine's account.");
            System.out.println(e);
        }

        // Clementine tries to create an account again, this time with a positive
        // initial deposit.
        try {
            veryRealBank.openAccount("Clementine", 600);
            System.out.println("Successfully opened Clementine's account!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error opening Clementine's account.");
            System.out.println(e);
        }
    }
}