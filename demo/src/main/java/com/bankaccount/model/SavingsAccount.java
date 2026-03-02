package com.bankaccount.model;

public class SavingsAccount extends BankAccount {
    private double interestRate;
    private static final double MIN_BALANCE = 100.0;

    public SavingsAccount(String accountNumber, String accountHolder, double initialBalance, double interestRate)
            throws InvalidAmountException {
        super(accountNumber, accountHolder, initialBalance);
        setInterestRate(interestRate);
        if (initialBalance < MIN_BALANCE) {
            throw new InvalidAmountException("Initial balance for SavingsAccount must be at least $100");
        }
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        if (interestRate < 0) {
            throw new IllegalArgumentException("Interest rate cannot be negative");
        }
        this.interestRate = interestRate;
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException, InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive");
        }
        if (getBalance() - amount < MIN_BALANCE) {
            throw new InsufficientFundsException("Cannot withdraw: minimum balance of $100 required");
        }
        setBalance(getBalance() - amount);
    }

    public void applyInterest() throws InvalidAmountException {
        setBalance(getBalance() + getBalance() * interestRate);
    }
}
