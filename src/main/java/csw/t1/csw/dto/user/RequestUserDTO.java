package csw.t1.csw.dto.user;

import jakarta.validation.constraints.NotNull;

public record RequestUserDTO(

        @NotNull
        Long tenantId,

        @NotNull
        String name,

        @NotNull
        String email,

        @NotNull
        String firebaseToken
) {
}
