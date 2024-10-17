package csw.t1.csw.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "tenants")
public class Tenant {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "tenant_id")
    private Long tenantId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String contact;

    @Column(name = "specific_configuration",
            nullable = false)
    private String specificConfiguration;

    /*@Column(name = "roles")
    private Set<Role> role;

    public enum Role {
        COMPRADOR,
        VENDEDOR,
        ORGANIZADOR,
        ADMINISTRADOR
    }*/
}