package com.custom;

public class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > this.balance) {
            throw new InsufficientFundsException(amount - this.balance);
        }
        this.balance -= amount;
    }

    public static void main(String[] args) {
        BankAccount myAccount = new BankAccount(1000);
        try {
            myAccount.withdraw(1000);
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }

    }
}
