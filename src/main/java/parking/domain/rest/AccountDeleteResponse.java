package parking.domain.rest;

public sealed interface AccountDeleteResponse {
    record AccountNotDeleted(String message) implements AccountDeleteResponse {}
    record AccountDeletedSuccessfully(String message) implements AccountDeleteResponse {}

}
