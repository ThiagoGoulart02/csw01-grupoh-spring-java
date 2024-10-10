package csw.t1.csw.service;

import csw.t1.csw.dto.sample.RequestSampleDTO;
import csw.t1.csw.dto.sample.ResponseSampleDTO;
import csw.t1.csw.entity.Sample;
import csw.t1.csw.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

    @Autowired
    private SampleRepository repository;

    public ResponseEntity<ResponseSampleDTO> createSample(RequestSampleDTO sample) {
        var response = repository.save(new Sample(sample));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseSampleDTO.builder()
                        .sampleId(response.getSampleId())
                        .text(response.getText())
                        .build()
                );
    }

    public ResponseEntity<ResponseSampleDTO> getSample(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                repository.findById(id)
                        .map(sample -> ResponseSampleDTO.builder()
                                .sampleId(sample.getSampleId())
                                .text(sample.getText())
                                .build()
                        )
                        .orElse(null)
        );
    }

    public ResponseEntity<ResponseSampleDTO> updateSample(Long id, RequestSampleDTO dto) {
        var sample = repository.findById(id).orElse(null);

        if(sample != null) {
            sample.setText(dto.text());
            repository.save(sample);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ResponseSampleDTO.builder()
                            .sampleId(sample.getSampleId())
                            .text(sample.getText()).build()
                    );
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    public ResponseEntity<Boolean> deleteSample(Long id) {
        var sample = repository.findById(id).orElse(null);

        if(sample != null) {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(true);
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
    }
}