package csw.t1.csw.service;

import csw.t1.csw.dto.notifications.RequestNotificationDTO;
import csw.t1.csw.dto.notifications.ResponseNotificationDTO;
import csw.t1.csw.entities.NotificationPreference;
import csw.t1.csw.repositories.NotificationPreferenceRepository;
import csw.t1.csw.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class NotificationPreferenceService {

    @Autowired
    private NotificationPreferenceRepository repository;

    @Autowired
    private UserRepository userRepository;


    public ResponseEntity<ResponseNotificationDTO> updateNotificationPreference(RequestNotificationDTO dto) {
        var user = userRepository.findById(dto.userId()).orElse(null);

        if (user != null) {
            var notification = repository.findByUser(user);

            NotificationPreference response;

            if (notification == null) {
                NotificationPreference newNotification = new NotificationPreference();
                newNotification.setUser(user);
                newNotification.setReceiveEmails(dto.receiveEmails());
                newNotification.setReceivePushNotifications(dto.receivePushNotifications());
                repository.save(newNotification);
                response = newNotification;
            } else {
                notification.setReceiveEmails(dto.receiveEmails());
                notification.setReceivePushNotifications(dto.receivePushNotifications());
                repository.save(notification);
                response = notification;
            }

            return ResponseEntity.status(HttpStatus.OK)
                    .body(ResponseNotificationDTO.builder()
                            .notificationPreferenceId(response.getNotificationPreferenceId())
                            .userId(response.getUser().getUserId())
                            .receiveEmails(response.isReceiveEmails())
                            .receivePushNotifications(response.isReceivePushNotifications())
                            .build()
                    );

        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

}