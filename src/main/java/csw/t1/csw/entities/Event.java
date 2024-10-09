package csw.t1.csw.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long eventId;


    /*@JoinColumn(name = "tenant_id", (n tem relacionamento)
            nullable = false)
    private Tenant tenant;*/

    @Column(name = "event_name",
            nullable = false)
    private String eventName;

    @Column(nullable = false)
    private EventType type;

    @Column(nullable = false)
    private String location;

    @Column(name = "date_time",
            nullable = false)
    private LocalDateTime dateTime;

    public enum EventType {
        FESTA,
        SHOW,
        TEATRO
    }
}