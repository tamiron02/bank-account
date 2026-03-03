package com.bankaccount.model;


import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<BankAccount> accounts;

    public Bank() {
        accounts = new ArrayList<>();
    }

    public void addAccount(BankAccount account) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }
        accounts.add(account);
    }

    public BankAccount findAccount(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public void displayAllAccounts() {
        for (BankAccount account : accounts) {
            System.out.println(account);
        }
    }

    public double calculateTotalBalance() {
        double total = 0.0;
        for (BankAccount account : accounts) {
            total += account.getBalance();
        }
        return total;
    }

    public void applyInterestToSavingsAccounts() throws InvalidAmountException {
        for (BankAccount account : accounts) {
            if (account instanceof SavingsAccount) {
                ((SavingsAccount) account).applyInterest();
            }
        }
    }
}
