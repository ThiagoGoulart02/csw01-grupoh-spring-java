package csw.t1.csw.service;

import csw.t1.csw.dto.ticket.ResponseTicketDTO;
import csw.t1.csw.dto.transaction.RequestTransactionDTO;
import csw.t1.csw.dto.transaction.ResponseTransactionDTO;
import csw.t1.csw.dto.transaction.TransactionMapper;
import csw.t1.csw.entities.Ticket;
import csw.t1.csw.entities.Transaction;
import csw.t1.csw.enums.EventType;
import csw.t1.csw.enums.TicketStatus;
import csw.t1.csw.enums.TransactionStatus;
import csw.t1.csw.repositories.TenantRepository;
import csw.t1.csw.repositories.TicketRepository;
import csw.t1.csw.repositories.TransactionRepository;
import csw.t1.csw.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TransactionMapper mapper;

    public ResponseEntity<ResponseTransactionDTO> createTransaction(RequestTransactionDTO dto) {
        var tenant = tenantRepository.findById(dto.tenantId()).orElse(null);
        var buyer = userRepository.findById(dto.buyerId()).orElse(null);
        var ticket = ticketRepository.findById(dto.ticketId())
                .filter(ticketStatus -> ticketStatus.getStatus() == TicketStatus.DISPONIVEL)
                .orElse(null);

        if (tenant != null && buyer != null &&
                ticket != null) {
            Transaction transaction = new Transaction();
            transaction.setTenant(tenant);
            transaction.setBuyer(buyer);
            transaction.setTicket(ticket);
            transaction.setSalePrice(ticket.getOriginalPrice());
            transaction.setTransactionDate(LocalDateTime.now());
            transaction.setStatus(TransactionStatus.valueOf("PENDENTE"));

            repository.save(transaction);

            ticket.setStatus(TicketStatus.RESERVADO);

            ticketRepository.save(ticket);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(mapper.toDTO(transaction));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    public ResponseEntity<ResponseTransactionDTO> updateTransactionStatus(Long id, String status) {
        var transaction = repository.findById(id).orElse(null);

        Ticket ticket;

        if (transaction != null && transaction.getStatus() == TransactionStatus.PENDENTE) {
            switch (status.toUpperCase()) {
                case "OK":
                    transaction.setStatus(TransactionStatus.CONCLUIDA);
                    repository.save(transaction);
                    ticket = transaction.getTicket();
                    ticket.setStatus(TicketStatus.VENDIDO);
                    ticketRepository.save(ticket);
                    return ResponseEntity.status(HttpStatus.OK
                    ).body(mapper.toDTO(transaction));
                case "FAIL":
                    transaction.setStatus(TransactionStatus.CANCELADA);
                    repository.save(transaction);
                    ticket = transaction.getTicket();
                    ticket.setStatus(TicketStatus.DISPONIVEL);
                    ticketRepository.save(ticket);
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(mapper.toDTO(transaction));
                default:
                    break;
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    public ResponseEntity<ResponseTicketDTO> refundTicket(Long ticketId) {
        var ticket = ticketRepository.findById(ticketId).orElse(null);
        var transaction = repository.findByTicket(ticket);

        if (ticket != null && transaction != null) {
            repository.delete(transaction);
            ticket.setStatus(TicketStatus.DISPONIVEL);
            ticketRepository.save(ticket);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ResponseTicketDTO.builder()
                            .ticketId(ticketId)
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