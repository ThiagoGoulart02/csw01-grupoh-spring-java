package csw.t1.csw.entities;

import csw.t1.csw.dto.user.RequestUserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "firebase_token",
            nullable = false)
    private String firebaseToken;

    // config de privacidade

    public User(RequestUserDTO dto, Tenant tenant) {
        this.tenant = tenant;
        this.name = dto.name();
        this.email = dto.email();
        this.firebaseToken = dto.firebaseToken();
    }
}