package parking.domain.database.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class Details {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private Integer phoneNumber;
}
