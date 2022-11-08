package Nobilities.ex36;

public sealed interface Priceable {
    record Price(double amount) implements Priceable { }
    final class NotFound implements Priceable {}
    final class Empty implements Priceable {}
}
