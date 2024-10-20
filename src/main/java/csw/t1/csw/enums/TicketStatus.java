package csw.t1.csw.enums;

import java.util.Arrays;

public enum TicketStatus {
    DISPONIVEL,
    RESERVADO,
    VENDIDO;

    public static TicketStatus findTicketType(String type) {
        return Arrays.stream(TicketStatus.values())
                .filter(eventType -> eventType.name()
                        .equalsIgnoreCase(type))
                .findFirst()
                .orElse(null);
    }
}
