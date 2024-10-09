package csw.t1.csw.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    /*@JoinColumn(name = "tenant_id",  (n tem relacionamento)
                nullable = false)
    private Tenant tenant;*/

    @ManyToOne
    @JoinColumn(name = "user_id",
                nullable = false)
    private User buyer;

    @OneToOne
    @JoinColumn(name = "ticket_id",
                nullable = false)
    private Ticket ticket;

    @Column(name = "sale_price",
            precision = 6,
            scale = 2,
            nullable = false)
    private BigDecimal salePrice;

    @Column(name = "transaction_date",
            nullable = false)
    private LocalDateTime transactionDate;

    @Column(nullable = false)
    private TransactionStatus status;

    public enum TransactionStatus {
        PENDENTE,
        CNCLUIDA,
        CANCELADA
    }
}
