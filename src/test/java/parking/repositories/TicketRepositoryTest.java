package parking.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.nobilites.parking.domain.SlotStatus;
import pl.nobilites.parking.domain.database.Ticket;

import java.time.Instant;


@SpringBootTest
public class TicketRepositoryTest {

    @Autowired
    TicketRepository ticketRepository;

    @Test
    public void testSaveTicket() {
        final var ticket = new Ticket(1L, 1, SlotStatus.AVAILABLE, Instant.now());

        final var savedTicket = ticketRepository.save(ticket);

        ticketRepository.findAll().forEach(System.out::println);
    }
}