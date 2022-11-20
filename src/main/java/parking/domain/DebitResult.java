package parking.domain;

public sealed interface DebitResult {
    final class SomethingWentWrong implements DebitResult {}
    final class Success implements DebitResult {}
}
