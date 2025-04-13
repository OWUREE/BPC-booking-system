import models.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PhysiotherapistTest {

    @Test
    public void testGenerateTimeTable() {
        Expertise expertise = new Expertise("Sports Therapy", new String[]{"Massage", "Stretching"});
        Physiotherapist physio = new Physiotherapist("Jane Smith", "123 Main St", "555-1234", new Expertise[]{expertise});

        ArrayList<Appointment> timetable = physio.getWorkingTimetable();

        assertFalse(timetable.isEmpty(), "Timetable should be generated and not empty");

        for (Appointment appointment : timetable) {
            assertEquals("Jane Smith", appointment.getPhysiotherapist().getFullName());

            // Check that treatment is from physiotherapist's expertise list
            List<String> validTreatments = Arrays.asList(expertise.getTreatmentList());
            assertTrue(validTreatments.contains(appointment.getTreatment()), "Treatment should contain Physio's expertise");

            // Check that a timetable has been generated and the status of appointment is "available""
            assertEquals(AppointmentStatus.AVAILABLE, appointment.getStatus());
        }
    }
}
