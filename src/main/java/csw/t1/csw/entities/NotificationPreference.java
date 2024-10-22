package csw.t1.csw.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "notification_preferences")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_preference_id")
    private Long notificationPreferenceId;

    @OneToOne
    @JoinColumn(name = "user_id",
                nullable = false)
    private User user;

    @Column(name = "receive_emails",
            nullable = false)
    private boolean receiveEmails;

    @Column(name = "receive_push_notifications",
            nullable = false)
    private boolean receivePushNotifications;
}