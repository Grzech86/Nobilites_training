package ex39;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommandTest {

    public int calculate(Accountable account) {
        account.apply( new Operation.Deposit(100) );
        account.apply( new Operation.Deposit(200) );
        account.apply( new Operation.Withdraw(50) );

        return account.balance();
    }

    @Test
    public void testOperations() {
        final var account = new AccountImpl();

        final var balance = calculate(account);

        Assertions.assertEquals(250, balance);
    }
}
