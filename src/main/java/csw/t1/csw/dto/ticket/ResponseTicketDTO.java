package csw.t1.csw.dto.ticket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class ResponseTicketDTO {

    private Long ticketId;

    private Long eventId;

    private Long tenantId;

    private BigDecimal originalPrice;

    private Long userId;

    private String uniqueVerificationCode;

    private String status;

}