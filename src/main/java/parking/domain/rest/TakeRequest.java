package parking.domain.rest;

import pl.nobilites.parking.domain.database.car.Plate;

public record TakeRequest(Integer number, Plate plate) {
}
