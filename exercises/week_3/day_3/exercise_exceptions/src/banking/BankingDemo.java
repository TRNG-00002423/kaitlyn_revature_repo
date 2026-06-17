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

        // Scarlett tries to transfer more money than she has to Clementine's account.
        try {
            veryRealBank.transfer("Scarlett", "Clementine", 1000); // she only has $900
            System.out.println("Successfully completed money transfer!");
        } catch (InsufficientFundsException e) {
            System.out.println("Error transferring funds.");
            System.out.println(e);
        }

        // After depositing funds, this transfer becomes possible.
        try {
            veryRealBank.getAccount("Scarlett").deposit(100);
            veryRealBank.transfer("Scarlett", "Clementine", 1000);
            System.out.println("Sucessfully completed money transfer!");
        } catch (InsufficientFundsException e) {
            System.out.println("Error transferring funds.");
            System.out.println(e);
        }

        // Clementine tries and fails to withdraw a negative amount.
        try {
            veryRealBank.getAccount("Clementine").withdraw(-1000);
            System.out.println("Successfully completed withdrawl!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error withdrawing funds.");
            System.out.println(e);
        }
    }
}