package parking.domain.rest;

public sealed interface AccountCreateResponse {
    final record AccountNotCreated(String message) implements AccountCreateResponse{}
    final record AccountCreatedSuccessfully() implements AccountCreateResponse{}

}


