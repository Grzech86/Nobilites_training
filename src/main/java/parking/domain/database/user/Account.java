package parking.domain.database.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Account {
    @Id
    private Identifier identifier;

    @Embedded
    private Details details;

    // constructor for a new user account
    public Account(Identifier identifier, String firstName, String lastName, String emailAddress, Integer phoneNumber) {
        this.identifier = identifier;
        this.details = new Details(firstName, lastName, emailAddress, phoneNumber);
    }
}







