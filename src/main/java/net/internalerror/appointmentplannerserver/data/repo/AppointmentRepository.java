package net.internalerror.appointmentplannerserver.data.repo;

import net.internalerror.appointmentplannerserver.data.Appointment;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends DatabaseEntityRepository<Appointment> {
    List<Appointment> findByDateBefore(LocalDateTime date);


}