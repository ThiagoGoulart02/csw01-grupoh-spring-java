package csw.t1.csw.service;

import csw.t1.csw.dto.ticket.RequestTicketDTO;
import csw.t1.csw.dto.ticket.ResponseTicketDTO;
import csw.t1.csw.entities.Ticket;
import csw.t1.csw.enums.TicketStatus;
import csw.t1.csw.repositories.EventRepository;
import csw.t1.csw.repositories.TenantRepository;
import csw.t1.csw.repositories.TicketRepository;
import csw.t1.csw.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository repository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<ResponseTicketDTO> createTicket(RequestTicketDTO dto) {
        var event = eventRepository.findById(dto.eventId())
                .orElse(null);
        var tenant = tenantRepository.findById(dto.eventId())
                .orElse(null);
        var user = userRepository.findById(dto.eventId())
                .orElse(null);

        TicketStatus status = TicketStatus.findTicketType(dto.status());

        if(event != null && tenant != null &&
                user != null && status != null) {
            Ticket ticket = new Ticket();
            ticket.setEvent(event);
            ticket.setTenant(tenant);
            ticket.setOriginalPrice(dto.originalPrice());
            ticket.setStatus(status);
            return
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    public ResponseEntity<List<ResponseTicketDTO>> getTicketsByEvent(Long id) {
        return null;
    }
}