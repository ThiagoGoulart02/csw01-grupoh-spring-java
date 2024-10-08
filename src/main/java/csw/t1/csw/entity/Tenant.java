package csw.t1.csw.entity;

import jakarta.persistence.*;

import java.util.UUID;

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
}