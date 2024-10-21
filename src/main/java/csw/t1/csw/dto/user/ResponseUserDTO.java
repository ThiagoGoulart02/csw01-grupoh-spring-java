package csw.t1.csw.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResponseUserDTO {

    private Long userId;

    private Long tenantId;

    private String name;

    private String email;

    private String firebaseToken;
}