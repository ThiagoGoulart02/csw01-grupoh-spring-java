package csw.t1.csw.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "tenant_id",
                nullable = false)
    private Tenant tenant;

    @Column(nullable = false)
    private String name;

    @Column(unique = true,
            nullable = false)
    private String email;

    @Column(nullable = false)
    private String firebaseToken;

    // config de privacidade
}