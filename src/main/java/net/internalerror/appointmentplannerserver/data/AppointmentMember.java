package net.internalerror.appointmentplannerserver.data;

import jakarta.persistence.*;
import lombok.*;
import net.internalerror.appointmentplannerserver.Status;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "appointment_member")
public class AppointmentMember extends DatabaseEntity {
    @ToString.Exclude
    @OneToOne(cascade = CascadeType.MERGE, optional = false, orphanRemoval = true)
    @JoinColumn(name = "member_id", nullable = false)
    protected User member;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.MERGE, optional = false, orphanRemoval = true)
    @JoinColumn(name = "appointment_id", nullable = false)
    protected Appointment appointment;

    @Column(name = "comment")
    protected String comment;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    protected Status status;

}