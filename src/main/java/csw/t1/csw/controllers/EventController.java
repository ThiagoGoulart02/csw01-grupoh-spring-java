package csw.t1.csw.controllers;

import csw.t1.csw.dto.event.RequestEventByAdminDTO;
import csw.t1.csw.dto.event.RequestEventDTO;
import csw.t1.csw.dto.event.ResponseEventDTO;
import csw.t1.csw.service.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("/events")
@RestController
public class EventController {

    @Autowired
    private EventService service;

    @PostMapping("/create-new-event")
    public ResponseEntity<ResponseEventDTO> createEvent(@RequestBody @Valid RequestEventDTO dto) {
        return service.createEvent(dto);
    }

    @GetMapping("/get-events")
    public ResponseEntity<List<ResponseEventDTO>> getEvents() {
        return service.getEvents();
    }

    @GetMapping("/get-events/by-type/{type}")
    public ResponseEntity<List<ResponseEventDTO>> getEventsByType(@PathVariable String type) {
        return service.getEventsByType(type);
    }

    @PostMapping("/admin/{adminId}/create-new-event")
    public ResponseEntity<ResponseEventDTO> createEventByAdmin(@PathVariable Long adminId,
            @RequestBody @Valid RequestEventDTO dto) {
        return service.createEventByAdmin(adminId, dto);
    }

    @PatchMapping("/admin/{adminId}/update-event/{id}")
    public ResponseEntity<ResponseEventDTO> updateEventByAdmin(@PathVariable Long adminId,
                                                               @PathVariable Long id,
                                                               @RequestBody @Valid RequestEventByAdminDTO dto) {
        return service.updateEventByAdmin(adminId, id, dto);
    }

    @DeleteMapping("/admin/{adminId}/delete-event/{id}")
    public ResponseEntity<Boolean> deleteEventByAdmin(@PathVariable Long adminId, @PathVariable Long id) {
        return service.deleteEventByAdmin(adminId, id);
    }


}