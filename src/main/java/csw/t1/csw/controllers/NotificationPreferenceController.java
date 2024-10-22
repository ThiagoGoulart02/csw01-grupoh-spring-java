package csw.t1.csw.controllers;

import csw.t1.csw.dto.notifications.RequestNotificationDTO;
import csw.t1.csw.dto.notifications.ResponseNotificationDTO;
import csw.t1.csw.service.NotificationPreferenceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping("/notifications")
@RestController
public class NotificationPreferenceController {

    @Autowired
    private NotificationPreferenceService service;

    @PatchMapping("/update-notification-preference")
    public ResponseEntity<ResponseNotificationDTO> updateNotificationPreference (@RequestBody
                                                                                 @Valid RequestNotificationDTO dto){
        return service.updateNotificationPreference(dto);
    }
}