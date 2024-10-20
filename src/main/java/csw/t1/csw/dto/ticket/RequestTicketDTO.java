package csw.t1.csw.dto.ticket;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RequestTicketDTO(

        @NotNull
        Long eventId,

        @NotNull
        Long tenantId,

        @NotNull
        BigDecimal originalPrice,

        @NotNull
        Long userId,

        @NotNull
        String uniqueVerificationCode,

        @NotNull
        String status
) {
}
