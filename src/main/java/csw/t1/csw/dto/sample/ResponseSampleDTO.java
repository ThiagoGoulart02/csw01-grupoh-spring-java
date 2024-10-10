package csw.t1.csw.dto.sample;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResponseSampleDTO {

    private Long sampleId;

    private String text;
}