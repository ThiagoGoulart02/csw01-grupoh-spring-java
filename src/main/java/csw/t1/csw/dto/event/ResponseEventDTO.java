package csw.t1.csw.dto.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ResponseEventDTO {

    private Long eventId;

    private Long tenantId;

    private String eventName;

    private String type;

    private String location;

    private LocalDateTime dateTime;

}