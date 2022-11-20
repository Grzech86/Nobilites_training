package parking.domain.database;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.nobilites.parking.domain.SlotStatus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Instant;


@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public final class Ticket {
    @Id
    @GeneratedValue
    private Long id;

    private Integer number;
    private SlotStatus slotStatus;
    private Instant timestamp;
}
