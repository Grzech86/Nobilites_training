package parking.domain.rest;

import parking.domain.database.user.Identifier;

import java.math.BigDecimal;

public record DebitRequest(Identifier identifier, BigDecimal amount) {
}
