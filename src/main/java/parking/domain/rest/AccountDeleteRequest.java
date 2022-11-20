package parking.domain.rest;

import pl.nobilites.parking.domain.database.user.Identifier;

public record AccountDeleteRequest(Identifier identifier) {
}
