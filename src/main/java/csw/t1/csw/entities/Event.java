package csw.t1.csw.entities;

import csw.t1.csw.dto.event.RequestEventDTO;
import csw.t1.csw.enums.EventType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long eventId;

    @ManyToOne
    @JoinColumn(name = "tenant_id",
            nullable = false)
    private Tenant tenant;

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


}