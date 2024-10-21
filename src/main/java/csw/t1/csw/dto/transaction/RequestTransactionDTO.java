package csw.t1.csw.dto.transaction;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RequestTransactionDTO(

        @NotNull
        Long tenantId,

        @NotNull
        Long buyerId,

        @NotNull
        Long ticketId
) {
}
