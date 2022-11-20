package parking.domain.rest;

public sealed interface DebitResponse {
    final class SomethingGetWrong implements DebitResponse{}
    final class Success implements DebitResponse{}
}

