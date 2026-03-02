package com.bankaccount.model;

public abstract class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;

    public BankAccount(String accountNumber, String accountHolder, double initialBalance) throws InvalidAmountException {
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Account number cannot be empty");
        }
        if (accountHolder == null || accountHolder.trim().isEmpty()) {
            throw new IllegalArgumentException("Account holder cannot be empty");
        }
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        setBalance(initialBalance);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    protected void setBalance(double balance) throws InvalidAmountException {
        if (balance < 0) {
            throw new InvalidAmountException("Balance cannot be negative");
        }
        this.balance = balance;
    }

    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive");
        }
        setBalance(this.balance + amount);
    }

    public abstract void withdraw(double amount) throws InsufficientFundsException, InvalidAmountException;

    @Override
    public String toString() {
        return String.format("Account[%s] Holder: %s, Balance: $%.2f", accountNumber, accountHolder, balance);
    }
}
