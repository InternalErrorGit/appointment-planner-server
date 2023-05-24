package net.internalerror.appointmentplannerserver.data.repo;

import net.internalerror.appointmentplannerserver.data.AppointmentMember;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentMemberRepository extends DatabaseEntityRepository<AppointmentMember> {
}