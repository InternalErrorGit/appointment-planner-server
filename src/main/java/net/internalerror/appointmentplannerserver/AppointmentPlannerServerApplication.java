package net.internalerror.appointmentplannerserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AppointmentPlannerServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppointmentPlannerServerApplication.class, args);
    }

}
