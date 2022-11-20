package parking.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import parking.domain.database.Ticket;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {
}
