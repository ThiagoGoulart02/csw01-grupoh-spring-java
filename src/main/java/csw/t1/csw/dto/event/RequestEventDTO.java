package csw.t1.csw.dto.event;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RequestEventDTO(

        @NotNull
        Long tenant,

        @NotNull
        String eventName,

        @NotNull
        String type,

        @NotNull
        String location,

        @NotNull
        LocalDateTime dateTime

) {
}