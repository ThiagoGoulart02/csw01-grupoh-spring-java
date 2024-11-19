package csw.t1.csw.service;

import csw.t1.csw.dto.ticket.RequestCheckTicketDTO;
import csw.t1.csw.dto.ticket.RequestTicketDTO;
import csw.t1.csw.dto.ticket.ResponseTicketDTO;
import csw.t1.csw.entities.Ticket;
import csw.t1.csw.enums.TicketStatus;
import csw.t1.csw.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    @Autowired
    private TransactionRepository transactionRepository;

    public ResponseEntity<ResponseTicketDTO> createTicket(RequestTicketDTO dto) {
        var event = eventRepository.findById(dto.eventId())
                .orElse(null);
        var tenant = tenantRepository.findById(dto.tenantId())
                .orElse(null);
        var user = userRepository.findById(dto.userId())
                .orElse(null);

        TicketStatus status = TicketStatus.findTicketType(dto.status());

        if (event != null && tenant != null &&
                user != null && status != null &&
                dto.originalPrice().compareTo(BigDecimal.ZERO) >= 0) {
            Ticket ticket = new Ticket();
            ticket.setEvent(event);
            ticket.setTenant(tenant);
            ticket.setOriginalPrice(dto.originalPrice());
            ticket.setUser(user);
            ticket.setUniqueVerificationCode(dto.uniqueVerificationCode());
            ticket.setStatus(status);
            repository.save(ticket);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ResponseTicketDTO.builder()
                            .ticketId(ticket.getTicketId())
                            .eventId(ticket.getEvent().getEventId())
                            .tenantId(ticket.getTenant().getTenantId())
                            .originalPrice(ticket.getOriginalPrice())
                            .userId(ticket.getUser().getUserId())
                            .uniqueVerificationCode(ticket.getUniqueVerificationCode())
                            .status(String.valueOf(ticket.getStatus())
                            ).build()
                    );
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    public ResponseEntity<List<ResponseTicketDTO>> getTicketsByEvent(Long id) {
        var event = eventRepository.findById(id).orElse(null);

        if (event != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(repository.findByEvent(event)
                            .stream()
                            .map(ticket -> ResponseTicketDTO.builder()
                                    .ticketId(ticket.getTicketId())
                                    .eventId(ticket.getEvent().getEventId())
                                    .tenantId(ticket.getTenant().getTenantId())
                                    .originalPrice(ticket.getOriginalPrice())
                                    .userId(ticket.getUser().getUserId())
                                    .uniqueVerificationCode(ticket.getUniqueVerificationCode())
                                    .status(String.valueOf(ticket.getStatus())
                                    )
                                    .build()
                            )
                            .toList()
                    );
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    public ResponseEntity<List<ResponseTicketDTO>> getTicketsByUser(Long userId) {
        var user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(transactionRepository.findByBuyer(user)
                            .stream()
                            .map(transaction -> ResponseTicketDTO.builder()
                                    .ticketId(transaction.getTicket().getTicketId())
                                     .eventId(transaction.getTicket().getEvent().getEventId())
                                    .tenantId(transaction.getTicket().getTenant().getTenantId())
                                    .originalPrice(transaction.getTicket().getOriginalPrice())
                                    .userId(transaction.getTicket().getUser().getUserId())
                                    .uniqueVerificationCode(transaction.getTicket().getUniqueVerificationCode())
                                    .status(String.valueOf(transaction.getTicket().getStatus()))
                                    .build())
                            .toList()
                    );


        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

    }

    public ResponseEntity<ResponseTicketDTO> checkTicket(RequestCheckTicketDTO dto) {
        var ticket = repository.findById(dto.ticketId()).orElse(null);


        if (ticket != null && ticket.getStatus() == TicketStatus.VENDIDO) {
            ticket.setStatus(TicketStatus.USADO);
            repository.save(ticket);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(ResponseTicketDTO.builder()
                            .ticketId(ticket.getTicketId())
                            .eventId(ticket.getEvent().getEventId())
                            .tenantId(ticket.getTenant().getTenantId())
                            .originalPrice(ticket.getOriginalPrice())
                            .userId(ticket.getUser().getUserId())
                            .uniqueVerificationCode(ticket.getUniqueVerificationCode())
                            .status(String.valueOf(ticket.getStatus()))
                            .build()
                    );
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

}