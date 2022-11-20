package parking.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.nobilites.parking.domain.database.user.Account;
import pl.nobilites.parking.domain.database.user.Identifier;

@Repository

public interface AccountRepository extends CrudRepository<Account, Identifier> {
}
