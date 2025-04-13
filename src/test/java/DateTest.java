import models.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DateTest {

    @Test
    public void testToString() {
        Date appointmentDate = new Date(2025, 10, 21, "09:00", "10:00"); // 21st October 2025 is a Tuesday
        String expected = "Tuesday 21st October 2025, 09:00-10:00";

        assertEquals(expected, appointmentDate.toString());
        System.out.println(appointmentDate);
    }
}
