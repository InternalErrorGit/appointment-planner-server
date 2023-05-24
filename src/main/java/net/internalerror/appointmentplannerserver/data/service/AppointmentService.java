package net.internalerror.appointmentplannerserver.data.service;

import net.internalerror.appointmentplannerserver.RecurringType;
import net.internalerror.appointmentplannerserver.data.Appointment;
import net.internalerror.appointmentplannerserver.data.repo.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService extends DatabaseEntityService<Appointment, AppointmentRepository> {
    public AppointmentService(AppointmentRepository repository) {
        super(repository);
    }

    public int removeExpiredAppointments() {
        int count = 0;
        List<Appointment> expiredAppointments = repository.findByDateBefore(LocalDateTime.now());

        for (Appointment expiredAppointment : expiredAppointments) {
            if (expiredAppointment.getRecurringType() == RecurringType.SINGLE) {
                repository.delete(expiredAppointment);
            } else {
                rescheduleAppointment(expiredAppointment);
            }
        }

        return count;
    }

    public void rescheduleAppointment(Appointment appointment) {

        switch (appointment.getRecurringType()) {
            case DAILY -> appointment.setDate(appointment.getDate().plusDays(1L));
            case WEEKLY -> appointment.setDate(appointment.getDate().plusWeeks(1L));
            case MONTHLY -> appointment.setDate(appointment.getDate().plusMonths(1L));
            case YEARLY -> appointment.setDate(appointment.getDate().plusYears(1L));
        }

        repository.save(appointment);


    }
}
