package parking.domain.database.parking;

import lombok.*;
import pl.nobilites.parking.domain.SlotStatus;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
public class ParkingStatus {
    @Id
    private Integer number;
    private SlotStatus slotStatus;
    private Instant timestamp;
}
