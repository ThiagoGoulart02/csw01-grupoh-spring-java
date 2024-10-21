package csw.t1.csw.repositories;

import csw.t1.csw.entities.Event;
import csw.t1.csw.enums.EventType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByType(EventType type);
}