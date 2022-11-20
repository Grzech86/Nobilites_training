package Nobilites.ex38;

import Nobilities.ex38.Account;
import Nobilities.ex38.LoggingAccountDecorator;
import org.junit.jupiter.api.Test;


class MainCurrencyResponseTest {
    @Test
    public void testAccount() {
        final var account = new Account();
        doOperation(account);
    }

    @Test
    public void testDecorator() {
        final var account = new Account();
        doOperation(new LoggingAccountDecorator(account));
    }

    private void doOperation(Account account) {
        account.deposit(100);
        account.deposit(200);
        account.deposit(300);
    }

}