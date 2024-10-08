package csw.t1.csw.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long ticketId;

    @ManyToOne
    @JoinColumn(name = "event_id",
            nullable = false)
    private Event event;

    /*@ManyToOne
    @JoinColumn(name = "tenant_id",  (n tem o relacionamento)
            nullable = false)
    private Tenant tenant;*/

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

    public enum TicketStatus {
        DISPONIVEL,
        RESERVADO,
        VENDIDO
    }
}
