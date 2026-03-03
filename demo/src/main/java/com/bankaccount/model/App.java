package com.bankaccount.model;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        Bank bank = new Bank();
        boolean running = true;

        while (running) {
            System.out.println("=== Bank Account System ===");
            System.out.println("1. Create Savings Account");
            System.out.println("2. Create Checking Account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Check Balance");
            System.out.println("6. Apply Interest (Savings)");
            System.out.println("7. Display All Accounts");
            System.out.println("8. Exit");
            System.out.print("Select option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1: {
                        System.out.print("Enter account number: ");
                        String accNum = scanner.nextLine();
                        System.out.print("Enter account holder: ");
                        String holder = scanner.nextLine();
                        System.out.print("Enter initial balance: ");
                        double initBal = scanner.nextDouble();
                        System.out.print("Enter interest rate (e.g. 0.02): ");
                        double rate = scanner.nextDouble();
                        scanner.nextLine();
                        SavingsAccount sa = new SavingsAccount(accNum, holder, initBal, rate);
                        bank.addAccount(sa);
                        System.out.println("Savings account created.");
                        break;
                    }
                    case 2: {
                        System.out.print("Enter account number: ");
                        String accNum = scanner.nextLine();
                        System.out.print("Enter account holder: ");
                        String holder = scanner.nextLine();
                        System.out.print("Enter initial balance: ");
                        double initBal = scanner.nextDouble();
                        System.out.print("Enter overdraft limit: ");
                        double limit = scanner.nextDouble();
                        scanner.nextLine();
                        com.bankaccount.model.CheckingAccount ca = new com.bankaccount.model.CheckingAccount(accNum, holder, initBal, limit);
                        bank.addAccount(ca);
                        System.out.println("Checking account created.");
                        break;
                    }
                    case 3: {
                        System.out.print("Enter account number: ");
                        String accNum = scanner.nextLine();
                        com.bankaccount.model.BankAccount acc = bank.findAccount(accNum);
                        if (acc == null) {
                            System.out.println("Account not found.");
                            break;
                        }
                        System.out.print("Enter deposit amount: ");
                        double amt = scanner.nextDouble();
                        scanner.nextLine();
                        acc.deposit(amt);
                        System.out.println("Deposit successful. New balance: $" + acc.getBalance());
                        break;
                    }
                    case 4: {
                        System.out.print("Enter account number: ");
                        String accNum = scanner.nextLine();
                        com.bankaccount.model.BankAccount acc = bank.findAccount(accNum);
                        if (acc == null) {
                            System.out.println("Account not found.");
                            break;
                        }
                        System.out.print("Enter withdrawal amount: ");
                        double amt = scanner.nextDouble();
                        scanner.nextLine();
                        acc.withdraw(amt);
                        System.out.println("Withdrawal successful. New balance: $" + acc.getBalance());
                        break;
                    }
                    case 5: {
                        System.out.print("Enter account number: ");
                        String accNum = scanner.nextLine();
                        com.bankaccount.model.BankAccount acc = bank.findAccount(accNum);
                        if (acc == null) {
                            System.out.println("Account not found.");
                        } else {
                            System.out.println(acc);
                        }
                        break;
                    }
                    case 6: {
                        bank.applyInterestToSavingsAccounts();
                        System.out.println("Interest applied to all savings accounts.");
                        break;
                    }
                    case 7: {
                        bank.displayAllAccounts();
                        break;
                    }
                    case 8: {
                        running = false;
                        System.out.println("Exiting...");
                        break;
                    }
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (com.bankaccount.model.InsufficientFundsException | com.bankaccount.model.InvalidAmountException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
            System.out.println();
        }
        scanner.close();
    }
}
