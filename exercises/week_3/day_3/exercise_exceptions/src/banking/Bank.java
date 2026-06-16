package banking;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    // TODO HashMap<String, Account>
    Map<String, Account> bankAccounts = new HashMap<String, Account>();

    public void openAccount(String id, double initialDeposit) throws InvalidAccountException {
        if (id == null || id == "") {
            throw new InvalidAccountException("Cannot open an account with an empty ID.");
        }
        if (this.bankAccounts.containsKey(id)) {
            throw new InvalidAccountException("Account with id " + id + " already exists.");
        }
        Account newAccount = new Account(id);
        newAccount.deposit(initialDeposit); // might throw IllegalArgumentException
        this.bankAccounts.put(id, newAccount);
    }

    public Account getAccount(String id) throws InvalidAccountException {
        if (id == null || id == "") {
            throw new InvalidAccountException("Cannot get an account with an empty ID.");
        }
        if (!this.bankAccounts.containsKey(id)) {
            throw new InvalidAccountException("No account found with id " + id + ".");
        }
        return this.bankAccounts.get(id);
    }

    public void transfer(String fromId, String toId, double amount)
            throws InvalidAccountException, InsufficientFundsException {
        Account fromAccount = this.getAccount(fromId);
        Account toAccount = this.getAccount(toId);
        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
    }
}