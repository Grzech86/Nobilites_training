package parking.domain.rest;

import parking.domain.database.car.Plate;

public record TakeRequest(Integer number, Plate plate) {
}
