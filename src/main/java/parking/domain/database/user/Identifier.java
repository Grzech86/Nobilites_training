package parking.domain.database.user;

import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public final class Identifier implements Serializable {
    private String userId;
}

