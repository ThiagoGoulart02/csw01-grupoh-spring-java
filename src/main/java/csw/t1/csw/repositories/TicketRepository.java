package csw.t1.csw.repositories;

import csw.t1.csw.entities.Event;
import csw.t1.csw.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByEvent(Event event);
}