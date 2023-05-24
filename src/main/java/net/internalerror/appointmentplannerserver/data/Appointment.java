package net.internalerror.appointmentplannerserver.data;

import jakarta.persistence.*;
import lombok.*;
import net.internalerror.appointmentplannerserver.RecurringType;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "appointment")
public class Appointment extends DatabaseEntity {
    @Column(name = "name", nullable = false)
    protected String name;

    @Column(name = "description")
    protected String description;

    @Column(name = "date", nullable = false)
    protected LocalDateTime date;

    @Column(name = "duration")
    protected String duration;

    @Enumerated(EnumType.STRING)
    @Column(name = "recurring_type", nullable = false)
    protected RecurringType recurringType = RecurringType.SINGLE;

}