package Nobilities.ex39;

import java.util.ArrayList;
import java.util.Collection;

public class AccountImpl implements Accountable {
    Collection<Operation> operations = new ArrayList<Operation>();
    Collection<Operation> rejected = new ArrayList<Operation>();

    @Override
    public void apply(Operation operation) {
        int balance = balance();
        int blocked = blocked();

        if (operation instanceof Operation.Withdraw withdraw) {
            if (blocked + withdraw.amount() > balance) {
                rejected.add(operation);
                return;
            }
        }

        operations.add(operation);
    }

    private int blocked() {
        int blocked = 0;

        for(final var operation : operations) {
            if (operation instanceof Operation.Block block) {
                blocked += block.amount();
            }
        }

        return blocked;
    }

    @Override
    public int balance() {
        int balance = 0;

        for (final var operation : operations) {
            if (operation instanceof Operation.Deposit deposit) {
                balance += deposit.amount();
            } else if (operation instanceof Operation.Withdraw withdraw) {
                balance -= withdraw.amount();
            }
        }

        return balance;
    }
}
