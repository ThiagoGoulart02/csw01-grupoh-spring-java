package csw.t1.csw.dto.notifications;

import jakarta.validation.constraints.NotNull;

public record RequestNotificationDTO(

        @NotNull
        Long userId,

        @NotNull
        boolean receiveEmails,

        @NotNull
        boolean receivePushNotifications

) {
}
