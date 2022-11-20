package parking.domain.database.car;

import pl.nobilites.parking.domain.database.user.Identifier;

import java.io.Serializable;

public class CarId implements Serializable {
    private Identifier identifier;
    private Plate plate;
}
