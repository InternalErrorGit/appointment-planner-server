package net.internalerror.appointmentplannerserver.rest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class AppointmentModel {

    protected String name;

    protected String description;

    protected LocalDateTime date;

    protected String duration;

    protected String recurringType;



}
