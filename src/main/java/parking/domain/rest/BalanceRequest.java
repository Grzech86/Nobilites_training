package parking.domain.rest;

import pl.nobilites.parking.domain.database.user.Identifier;

public record BalanceRequest(Identifier identifier) {
}
