package csw.t1.csw.enums;

import csw.t1.csw.entities.Ticket;

import java.util.Arrays;

public enum TicketStatus {
    DISPONIVEL,
    RESERVADO,
    VENDIDO,
    USADO;

    public static TicketStatus findTicketType(String type) {
        return Arrays.stream(TicketStatus.values())
                .filter(eventType -> eventType.name()
                        .equalsIgnoreCase(type))
                .findFirst()
                .orElse(null);
    }

    public static boolean isTicketAvailable(Ticket ticket) {
        return ticket.getStatus() == TicketStatus.DISPONIVEL;
    }
}
