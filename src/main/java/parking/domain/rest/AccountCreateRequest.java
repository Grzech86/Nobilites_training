package parking.domain.rest;

public record AccountCreateRequest(String firstName,
                                   String lastName,
                                   String emailAddress,
                                   Integer phoneNumber,
                                   String...plates) {
}
