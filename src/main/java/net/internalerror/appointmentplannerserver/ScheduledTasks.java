package net.internalerror.appointmentplannerserver;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.internalerror.appointmentplannerserver.data.service.AppointmentService;
import net.internalerror.appointmentplannerserver.data.service.AuthTokenService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class ScheduledTasks {

    private final AuthTokenService authTokenService;

    private final AppointmentService appointmentService;

    @Scheduled(fixedRate = 60_000L)
    public void removeExpiredTokens() {
        log.info("Executing Task: Remove Expired Appointments");
        int count = authTokenService.removeExpiredTokens();
        log.info("Removed {} appointments", count);
    }

    @Scheduled(fixedRate = 60_000L)
    public void removeExpiredAppointments() {
        log.info("Executing Task: Remove Expired Appointments");
        int count = appointmentService.removeExpiredAppointments();
        log.info("Removed {} appointments", count);
    }

}
