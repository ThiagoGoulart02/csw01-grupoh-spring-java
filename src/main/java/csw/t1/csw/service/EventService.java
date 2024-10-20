package csw.t1.csw.service;

import csw.t1.csw.dto.event.RequestEventDTO;
import csw.t1.csw.dto.event.ResponseEventDTO;
import csw.t1.csw.entities.Event;
import csw.t1.csw.enums.EventType;
import csw.t1.csw.repositories.EventRepository;
import csw.t1.csw.repositories.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    @Autowired
    private TenantRepository tenantRepository;

    public ResponseEntity<ResponseEventDTO> createEvent(RequestEventDTO dto) {
        var tenant = tenantRepository.findById(dto.tenant())
                .orElse(null);

        EventType eventType = EventType.findEventType(dto.type());

        if (tenant != null && eventType != null) {
            Event event = new Event();
            event.setTenant(tenant);
            event.setEventName(dto.eventName());
            event.setType(eventType);
            event.setLocation(dto.location());
            event.setDateTime(dto.dateTime());
            repository.save(event);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ResponseEventDTO.builder()
                            .eventId(event.getEventId())
                            .tenantId(event.getTenant().getTenantId())
                            .eventName(event.getEventName())
                            .type(String.valueOf(event.getType()))
                            .location(event.getLocation())
                            .dateTime(event.getDateTime())
                            .build()
                    );
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    public ResponseEntity<List<ResponseEventDTO>> getEvents() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(repository.findAll()
                        .stream()
                        .map(event -> ResponseEventDTO.builder()
                                .eventId(event.getEventId())
                                .tenantId(event.getTenant().getTenantId())
                                .eventName(event.getEventName())
                                .type(String.valueOf(event.getType()))
                                .location(event.getLocation())
                                .dateTime(event.getDateTime())
                                .build()
                        )
                        .toList()
                );
    }
}