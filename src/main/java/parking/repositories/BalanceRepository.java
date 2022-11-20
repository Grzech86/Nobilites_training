package parking.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.nobilites.parking.domain.database.user.Identifier;
import pl.nobilites.parking.domain.database.user.Operation;

import java.util.List;

@Repository
public interface BalanceRepository extends CrudRepository<Operation, Integer> {
   // Optional<Operation> findOperationByUserIdentifier(UserIdentifier userIdentifier);
    List<Operation> findOperationByIdentifier(Identifier identifier);
//    List<Operation> findAllOperationsByUserIdentifierAndOperationType(UserIdentifier userIdentifier);
}
