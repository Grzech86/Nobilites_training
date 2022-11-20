package parking.domain.rest;

import pl.nobilites.parking.domain.database.user.Identifier;

public record PlatesClearRequest(Identifier identifier) {
}
