package csw.t1.csw.dto.notifications;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResponseNotificationDTO {

    private Long notificationPreferenceId;

    private Long userId;

    private boolean receiveEmails;

    private boolean receivePushNotifications;

}
