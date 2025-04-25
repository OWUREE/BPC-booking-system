import models.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PhysiotherapistTest {

    @Test
    public void testGenerateTimeTable() {
        Expertise expertise = new Expertise("Sports Therapy", new String[]{"Massage", "Stretching"});
        Physiotherapist physio = new Physiotherapist("Jane Smith", "123 Main St", "555-1234", new Expertise[]{expertise});

        LocalDate startDate = LocalDate.of(2025, 4, 17); // Choose a suitable start date
        int numberOfDays = 10;
        physio.generateTimeTable(startDate, numberOfDays);

        ArrayList<Appointment> timetable = physio.getWorkingTimetable();

        assertFalse(timetable.isEmpty(), "Timetable should be generated and not empty");

        for (Appointment appointment : timetable) {
            assertEquals("Jane Smith", appointment.getPhysiotherapist().getFullName());

            List<String> validTreatments = Arrays.asList(expertise.getTreatmentList());
            assertTrue(validTreatments.contains(appointment.getTreatment()), "Treatment should contain expertise list");

            // Check that a timetable has been generated and the status of appointment is "available""
            assertEquals(AppointmentStatus.AVAILABLE, appointment.getStatus());
        }
    }
}
