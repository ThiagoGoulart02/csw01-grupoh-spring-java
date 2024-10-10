package csw.t1.csw.dto.sample;


import jakarta.validation.constraints.NotBlank;

public record RequestSampleDTO(
        @NotBlank
        String text
) {
}
