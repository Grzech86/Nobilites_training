package parking.domain.database.car;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import parking.domain.database.user.Identifier;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@IdClass(CarId.class)
public class Car {
    @Id
    private Identifier identifier;
    @Id
    private Plate plate;
}
