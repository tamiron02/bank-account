package com.bankaccount.model;

public class CheckingAccount extends BankAccount {
    private double overdraftLimit;

    public CheckingAccount(String accountNumber, String accountHolder, double initialBalance, double overdraftLimit)
            throws InvalidAmountException {
        super(accountNumber, accountHolder, initialBalance);
        setOverdraftLimit(overdraftLimit);
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        if (overdraftLimit < 0) {
            throw new IllegalArgumentException("Overdraft limit cannot be negative");
        }
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException, InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive");
        }
        double newBalance = getBalance() - amount;
        if (newBalance < -overdraftLimit) {
            throw new InsufficientFundsException("Exceeds overdraft limit");
        }
        setBalance(newBalance);
    }

    @Override
    protected void setBalance(double balance) throws InvalidAmountException {
        // Allow negative balances up to the overdraft limit
        if (balance < -overdraftLimit) {
            throw new InvalidAmountException("Balance cannot be less than overdraft limit");
        }
        this.balance = balance;
    }

    @Override
    public String toString() {
        return String.format("CheckingAccount[%s] Holder: %s, Balance: $%.2f, Overdraft Limit: $%.2f",
                getAccountNumber(), getAccountHolder(), getBalance(), overdraftLimit);
    }
}
