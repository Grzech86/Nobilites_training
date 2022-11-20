package parking.domain.rest;

import parking.domain.database.user.Identifier;

public record BalanceRequest(Identifier identifier) {
}
