package csw.t1.csw.service;

import csw.t1.csw.dto.event.RequestEventByAdminDTO;
import csw.t1.csw.dto.event.RequestEventDTO;
import csw.t1.csw.dto.event.ResponseEventDTO;
import csw.t1.csw.entities.Event;
import csw.t1.csw.entities.Ticket;
import csw.t1.csw.enums.EventType;
import csw.t1.csw.repositories.EventRepository;
import csw.t1.csw.repositories.TenantRepository;
import csw.t1.csw.repositories.TicketRepository;
import csw.t1.csw.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TransactionRepository transactionRepository;

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

    public ResponseEntity<List<ResponseEventDTO>> getEventsByType(String type) {

        EventType eventType = EventType.findEventType(type);

        if (eventType != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(repository.findByType(eventType).stream()
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
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    public ResponseEntity<ResponseEventDTO> createEventByAdmin(Long adminId, RequestEventDTO dto) {
        var tenant = tenantRepository.findById(adminId).orElse(null);

        EventType type = EventType.findEventType(dto.type());

        if (tenant != null && type != null) {
            Event event = new Event();
            event.setTenant(tenant);
            event.setEventName(dto.eventName());
            event.setType(type);
            event.setLocation(dto.location());
            event.setDateTime(dto.dateTime());
            repository.save(event);
            return ResponseEntity.status(HttpStatus.OK).body(ResponseEventDTO.builder()
                    .eventId(event.getEventId())
                    .tenantId(event.getTenant().getTenantId())
                    .eventName(event.getEventName())
                    .type(String.valueOf(event.getType()))
                    .location(event.getLocation())
                    .dateTime(event.getDateTime())
                    .build());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    public ResponseEntity<ResponseEventDTO> updateEventByAdmin(Long adminId, Long id, RequestEventByAdminDTO dto) {
        var event = repository.findById(id).orElse(null);
        var tenant = tenantRepository.findById(adminId).orElse(null);

        if (tenant != null && event != null) {
            Optional.ofNullable(dto.eventName()).ifPresent(event::setEventName);
            Optional.ofNullable(dto.location()).ifPresent(event::setLocation);
            Optional.ofNullable(dto.dateTime()).ifPresent(event::setDateTime);


            if (dto.type() != null) {
                EventType type = EventType.findEventType(dto.type());
                if (type == null) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
                } else {
                    event.setType(type);
                }
            }
            repository.save(event);
            return ResponseEntity.status(HttpStatus.OK).body(ResponseEventDTO.builder()
                    .eventId(event.getEventId())
                    .tenantId(event.getTenant().getTenantId())
                    .eventName(event.getEventName())
                    .type(String.valueOf(event.getType()))
                    .location(event.getLocation())
                    .dateTime(event.getDateTime())
                    .build());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    public ResponseEntity<Boolean> deleteEventByAdmin(Long adminId, Long id) {
        var event = repository.findById(id).orElse(null);
        var tenant = tenantRepository.findById(adminId).orElse(null);

        if (event != null && tenant != null && event.getTenant() == tenant) {

            List<Ticket> tickets = ticketRepository.findByEvent(event);
            tickets.forEach(ticketToDelete -> transactionRepository.deleteByTicket(ticketToDelete));
            tickets.forEach(ticketToDelete -> ticketRepository.delete(ticketToDelete));
            repository.delete(event);

            return ResponseEntity.status(HttpStatus.OK).body(true);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
    }
}