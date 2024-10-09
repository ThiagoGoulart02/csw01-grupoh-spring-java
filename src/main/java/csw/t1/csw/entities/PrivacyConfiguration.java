package csw.t1.csw.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "privacy_configurations")
public class PrivacyConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "privacy_configuration_id")
    private Long privacyConfigurationId;

    @OneToOne
    @JoinColumn(name = "user_id",
                nullable = false)
    private User user;

    @Column(name = "allow_data_sharing",
            nullable = false)
    private boolean allowDataSharing;

    @Column(name = "profil_visibility",
            nullable = false)
    private boolean profileVisibility;

    @Column(name = "visible_transaction_history",
            nullable = false)
    private boolean visibleTransactionHistory;

    @Column(name = "receive_marketing_communications",
            nullable = false)
    private boolean receiveMarketingCommunications;
}