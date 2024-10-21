package csw.t1.csw.dto.transaction;

import csw.t1.csw.entities.Event;
import csw.t1.csw.entities.Ticket;
import csw.t1.csw.entities.Transaction;
import csw.t1.csw.entities.User;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public ResponseTransactionDTO toDTO(Transaction transaction) {

        BuyerDTO buyerDTO = toBuyerDTO(transaction.getBuyer());
        EventDTO eventDTO = toEventDTO(transaction.getTicket().getEvent());
        TicketDTO ticketDTO = toTicketDTO(transaction.getTicket(), eventDTO);

        return ResponseTransactionDTO.builder()
                .transactionId(transaction.getTransactionId())
                .tenantId(transaction.getTenant().getTenantId())
                .buyer(buyerDTO)
                .ticket(ticketDTO)
                .salePrice(transaction.getSalePrice())
                .transactionDate(transaction.getTransactionDate())
                .status(String.valueOf(transaction.getStatus()))
                .build();
    }

    private BuyerDTO toBuyerDTO(User buyer) {
        return BuyerDTO.builder()
                .userId(buyer.getUserId())
                .tenantId(buyer.getTenant().getTenantId())
                .name(buyer.getName())
                .email(buyer.getEmail())
                .firebaseToken(buyer.getFirebaseToken())
                .build();
    }

    private TicketDTO toTicketDTO(Ticket ticket, EventDTO eventDTO) {
        return TicketDTO.builder()
                .ticketId(ticket.getTicketId())
                .event(eventDTO)
                .tenantId(ticket.getTenant().getTenantId())
                .originalPrice(ticket.getOriginalPrice())
                .sellerId(ticket.getUser().getUserId())
                .uniqueVerificationCode(ticket.getUniqueVerificationCode())
                .status(String.valueOf(ticket.getStatus()))
                .build();
    }

    private EventDTO toEventDTO(Event event) {
        return EventDTO.builder().eventId(event.getEventId())
                .tenantId(event.getTenant().getTenantId())
                .eventName(event.getEventName())
                .type(String.valueOf(event.getType()))
                .location(event.getLocation())
                .dateTime(event.getDateTime())
                .build();
    }
}
