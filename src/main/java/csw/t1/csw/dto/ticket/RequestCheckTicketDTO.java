package csw.t1.csw.dto.ticket;

import jakarta.validation.constraints.NotNull;

public record RequestCheckTicketDTO(

        @NotNull
        Long ticketId

) {
}
