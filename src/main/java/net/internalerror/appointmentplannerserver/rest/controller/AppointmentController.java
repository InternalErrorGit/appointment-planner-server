package net.internalerror.appointmentplannerserver.rest.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.internalerror.appointmentplannerserver.data.Appointment;
import net.internalerror.appointmentplannerserver.rest.model.AppointmentModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AppointmentController {

    @PostMapping("/api/appointment/create")
    public void createAppointment(@Valid AppointmentModel appointmentModel){

    }


}
