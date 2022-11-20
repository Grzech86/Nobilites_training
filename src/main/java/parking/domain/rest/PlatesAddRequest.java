package parking.domain.rest;

import parking.domain.database.user.Identifier;

public record PlatesAddRequest(Identifier identifier, String...plates) {
}
