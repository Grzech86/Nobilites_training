package parking.domain.database.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.nobilites.parking.domain.OperationType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Operation {
    @Id
    @GeneratedValue
    private Integer id;
    private Identifier identifier;
    private OperationType operationType;
    private BigDecimal amount;
}


