package models;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentFactory {
    List<Appointment> createAppointments(LocalDate startDate);
}
