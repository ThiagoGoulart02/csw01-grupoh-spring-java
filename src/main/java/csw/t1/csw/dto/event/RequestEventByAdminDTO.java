package csw.t1.csw.dto.event;

import java.time.LocalDateTime;

public record RequestEventByAdminDTO(

        String eventName,

        String type,

        String location,

        LocalDateTime dateTime
) {
}
