package parking.domain.rest;

import pl.nobilites.parking.domain.database.user.Identifier;

public record PlatesAddRequest(Identifier identifier, String...plates) {
}
