package csw.t1.csw.controllers;

import csw.t1.csw.dto.event.RequestEventDTO;
import csw.t1.csw.dto.event.ResponseEventDTO;
import csw.t1.csw.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping
@RestController
public class EventController {

    @Autowired
    private EventService service;

    @PostMapping("/create-event")
    public ResponseEntity<ResponseEventDTO> createEvent(@RequestBody @Valid RequestEventDTO dto) {
        return service.createEvent(dto);
    }

    @GetMapping("/get-events")
    public ResponseEntity<List<ResponseEventDTO>> getEvents() {
        return service.getEvents();
    }
}