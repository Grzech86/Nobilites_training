package parking.domain.rest;

import pl.nobilites.parking.domain.database.user.Identifier;

import java.math.BigDecimal;

public record CreditRequest(Identifier identifier, BigDecimal amount) {
}
