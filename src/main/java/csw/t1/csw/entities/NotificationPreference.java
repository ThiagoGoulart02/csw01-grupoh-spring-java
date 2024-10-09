package csw.t1.csw.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "notification_preferences")
public class NotificationPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_preference_id")
    private Long notificationPreferenceId;

    /*@JoinColumn(name = "user_id",  (n tem relacionamento)
                nullable = false)
    private User user;*/

    @Column(name = "receive_emails",
            nullable = false)
    private boolean receiveEmails;

    @Column(name = "receive_push_notifications",
            nullable = false)
    private boolean receivePushNotifications;
}