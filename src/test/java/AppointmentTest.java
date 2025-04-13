import models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppointmentTest {

    Appointment instance;

    private Patient mockPatient;
    private Physiotherapist mockPhysio;
    private models.Date mockDate;
    private Appointment appointment;

    @BeforeEach
    void setUp() {

        mockPhysio = new Physiotherapist("Brian", "Steve", "1234567890",  new Expertise[] { new Expertise("Osteopathy", new String[]{"Spinal Manipulation", "Massage", "Acupuncture", "Stretching"})});
        mockDate = new models.Date(2025, 10, 8, "1:00", "2:00");
        mockPatient = new Patient("Alice Smith", "40 threshold St", "0987654321");

        appointment = new Appointment(null, mockPhysio, mockDate, "Spinal Manipulation");

        Appointment.getAllAppointment().clear();
        Appointment.getAllAppointment().add(appointment);
    }

    @Test
    void testBookAppointment() {
        // Attempt to book an available appointment
        appointment.bookAnAppointment(mockPatient);

        assertEquals(AppointmentStatus.BOOKED, appointment.getStatus());
        assertEquals(mockPatient.getUniqueID(), appointment.getPatientID());
    }

    @Test
    void testBookAppointment_conflict() {
        // Book appointment1 first
        appointment.bookAnAppointment(mockPatient);

        // Try to book another on same date — should conflict
        instance = new Appointment(null, mockPhysio, mockDate, "Acupuncture");
        instance.bookAnAppointment(mockPatient);

        assertNotEquals(AppointmentStatus.BOOKED, instance.getStatus());
        assertEquals(AppointmentStatus.AVAILABLE, instance.getStatus());
    }


}
