package parking.domain;

public sealed interface CreditResult {
    record Error(String message) implements CreditResult {}
    final class SomethingWentWrong implements CreditResult {}
    final class Success implements CreditResult {}
}