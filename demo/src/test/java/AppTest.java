

import org.junit.jupiter.api.Test;

import com.bankaccount.model.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for simple App.
 */
class AppTest {
    /**
     * Rigorous Test.
     */
    @Test
    void testApp() {
        assertEquals(1, 1);
    }

    @Test
    void testCustomerAddAccount() throws InvalidAmountException {
        Customer customer = new Customer("Alice", "C001");
        BankAccount account = new SavingsAccount("A100", "Alice", 200.0, 0.02);
        customer.addAccount(account);
        assertEquals(1, customer.getAccounts().size());
        assertEquals(account, customer.getAccounts().get(0));
    }

    @Test
    void testSavingsAccountDepositWithdraw() throws InvalidAmountException, InsufficientFundsException {
        SavingsAccount sa = new SavingsAccount("S100", "Bob", 200.0, 0.03);
        sa.deposit(100);
        assertEquals(300.0, sa.getBalance(), 0.001);
        sa.withdraw(150);
        assertEquals(150.0, sa.getBalance(), 0.001);
    }

    @Test
    void testCheckingAccountOverdraft() throws InvalidAmountException, InsufficientFundsException {
        CheckingAccount ca = new CheckingAccount("C200", "Carol", 100.0, 50.0);
        ca.withdraw(120.0);
        assertEquals(-20.0, ca.getBalance(), 0.001);
        Exception ex = assertThrows(InsufficientFundsException.class, () -> ca.withdraw(40.1));
        assertTrue(ex.getMessage().contains("overdraft"));
    }

    @Test
    void testBankCalculateTotalBalance() throws InvalidAmountException {
        Bank bank = new Bank();
        bank.addAccount(new SavingsAccount("S1", "Dan", 200.0, 0.01));
        bank.addAccount(new CheckingAccount("C1", "Eve", 100.0, 50.0));
        assertEquals(300.0, bank.calculateTotalBalance(), 0.001);
    }
}
