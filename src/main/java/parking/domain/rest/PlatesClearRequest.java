package parking.domain.rest;

import parking.domain.database.user.Identifier;

public record PlatesClearRequest(Identifier identifier) {
}
