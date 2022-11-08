package Nobilities.ex38;

import java.time.Instant;

public class LoggingAccountDecorator extends Account {
    private final Account account;

    public LoggingAccountDecorator(Account account) {
        this.account = account;
    }

    @Override
    public void deposit(int amount) {
        try {
            System.out.println(Instant.now() + ": deposit operation for amount " + amount);
            account.deposit(amount);
            System.out.println("operation completed successfully");
        } catch (Exception ex) {
            System.out.println("operation failed");
        }
    }
}
