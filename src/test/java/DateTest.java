import models.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DateTest {

    @Test
    public void testToString() {
        Date appointmentDate = new Date(2025, 4, 21, "09:00", "10:00");
        String expected = "Monday 21st April 2025, 09:00-10:00";

        assertEquals(expected, appointmentDate.toString());
        System.out.println(appointmentDate);
    }
}
