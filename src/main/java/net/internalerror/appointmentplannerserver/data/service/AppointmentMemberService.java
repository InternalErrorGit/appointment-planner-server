package net.internalerror.appointmentplannerserver.data.service;

import net.internalerror.appointmentplannerserver.data.AppointmentMember;
import net.internalerror.appointmentplannerserver.data.repo.AppointmentMemberRepository;
import org.springframework.stereotype.Service;

@Service
public class AppointmentMemberService extends DatabaseEntityService<AppointmentMember, AppointmentMemberRepository> {

    public AppointmentMemberService(AppointmentMemberRepository repository) {
        super(repository);
    }
}
