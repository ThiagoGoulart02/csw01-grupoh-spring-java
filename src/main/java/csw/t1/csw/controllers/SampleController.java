package csw.t1.csw.controllers;

import csw.t1.csw.dto.sample.RequestSampleDTO;
import csw.t1.csw.dto.sample.ResponseSampleDTO;
import csw.t1.csw.service.SampleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping
@RestController
public class SampleController {

    @Autowired
    private SampleService service;

    @PostMapping("/create-sample")
    public ResponseEntity<ResponseSampleDTO> createSample(@RequestBody
                                                         @Valid RequestSampleDTO sample) {
        return service.createSample(sample);
    }

    @GetMapping("/get-sample/{id}")
    public ResponseEntity<ResponseSampleDTO> getSample(@PathVariable Long id) {
        return service.getSample(id);
    }

    @PatchMapping("/update-sample/{id}")
    public ResponseEntity<ResponseSampleDTO> updateSample(@PathVariable Long id,
                                                          @RequestBody @Valid RequestSampleDTO sample) {
        return service.updateSample(id, sample);
    }

    @DeleteMapping("/delete-sample/{id}")
    public ResponseEntity<Boolean> deleteSample(@PathVariable Long id) {
        return service.deleteSample(id);
    }
}