import models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PatientTest {

    private Patient patient1;
    private Patient patient2;
    private Physiotherapist physio;
    private Appointment appointment1;
    private Appointment appointment2;
    private Appointment appointment3;

    @BeforeEach
    void setUp() {
        // Clear any existing appointments before each test
        Appointment.getAllAppointment().clear();

        patient1 = new Patient("Alice Smith", "123 Road", "555-1234");
        patient2 = new Patient("John Ford", "456 Street", "555-5678");

        physio = new Physiotherapist("Glory Dawn", "Mosquito Way", "111-222",  new Expertise[] { new Expertise("Osteopathy", new String[]{"Spinal Manipulation", "Massage", "Acupuncture", "Stretching"})});

        // Appointments: Two for patient1, one for patient2
        appointment1 = new Appointment(patient1.getUniqueID(), physio, new models.Date(2025, 10, 7, "10:00", "11:00"), "Massage");
        appointment2 = new Appointment(patient1.getUniqueID(), physio, new models.Date(2025, 10, 8, "11:00", "12:00"), "Stretching");
        appointment3 = new Appointment(patient2.getUniqueID(), physio, new models.Date(2025, 10, 11, "2:00", "3:00"), "Acupuncture");

        // Add all to static list
        Appointment.getAllAppointment().addAll(List.of(appointment1, appointment2, appointment3));
    }

    @Test
    void testGetPatientBookings() {
        List<Appointment> bookings = patient1.getPatientBookings();

        assertEquals(2, bookings.size());
        assertTrue(bookings.contains(appointment1));
        assertFalse(bookings.contains(appointment3));
    }

}
