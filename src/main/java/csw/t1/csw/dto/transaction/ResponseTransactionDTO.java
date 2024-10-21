package csw.t1.csw.dto.transaction;

import csw.t1.csw.entities.Event;
import csw.t1.csw.entities.Ticket;
import csw.t1.csw.entities.User;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTransactionDTO {
    private Long transactionId;
    private Long tenantId;
    private BuyerDTO buyer;
    private TicketDTO ticket;
    private BigDecimal salePrice;
    private LocalDateTime transactionDate;
    private String status;
}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class BuyerDTO {
    private Long userId;
    private Long tenantId;
    private String name;
    private String email;
    private String firebaseToken;
}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class TicketDTO {
    private Long ticketId;
    private Long tenantId;
    private BigDecimal originalPrice;
    private Long sellerId;
    private String uniqueVerificationCode;
    private String status;
    private EventDTO event;
}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class EventDTO {
    private Long eventId;
    private Long tenantId;
    private String eventName;
    private String type;
    private String location;
    private LocalDateTime dateTime;
}