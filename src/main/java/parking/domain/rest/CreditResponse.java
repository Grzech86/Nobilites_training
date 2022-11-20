package parking.domain.rest;

public sealed interface CreditResponse {
    final class SomethingWentWrong implements CreditResponse{}
    final class Success implements CreditResponse{}
}
