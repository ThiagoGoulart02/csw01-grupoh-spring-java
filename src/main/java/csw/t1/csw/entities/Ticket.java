package csw.t1.csw.entities;

import csw.t1.csw.enums.TicketStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long ticketId;

    @ManyToOne
    @JoinColumn(name = "event_id",
            nullable = false)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "tenant_id",
            nullable = false)
    private Tenant tenant;

    @Column(name = "original_price",
            precision = 6,
            scale = 2,
            nullable = false)
    private BigDecimal originalPrice;

    @ManyToOne
    @JoinColumn(name = "user_id",
                nullable = false)
    private User user;

    @Column(name = "unique_verification_code",
            nullable = false)
    private String uniqueVerificationCode;

    @Column(nullable = false)
    private TicketStatus status;
}
