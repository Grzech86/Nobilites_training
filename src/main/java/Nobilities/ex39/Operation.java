package Nobilities.ex39;

public sealed interface Operation {
    record Deposit(int amount) implements Operation {}
    record Withdraw(int amount) implements Operation {}

    record Block(int amount) implements Operation {}

    record Save(int amount, int noAccount) implements Operation {};
}
