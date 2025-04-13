import services.PersonnelManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonnelManagerTest {

    PersonnelManager instance;

    @BeforeEach
    void setUp() {

        instance = new PersonnelManager();
    }

    @Test
    void testAddNewPatient() {

        String fullName = "Hanifat Owuri";
        String address = "30 Main St";
        String telephoneNumber = "07423187612";

        instance.addNewPatient(fullName, address, telephoneNumber);
        // there were initially 3 patient data -hardcoded
        assertEquals(4, instance.getPatientList().size());
    }

    @Test
    void testRemovePatient() {

        String fullName = "Hanifat Owuri";
        String address = "30 Main St";
        String telephoneNumber = "07423187612";

        instance.addNewPatient(fullName, address, telephoneNumber);

        assertEquals(4, instance.getPatientList().size());

        String idToRemove = instance.getPatientList().get(3).getUniqueID();
        boolean removed = instance.removePatientByID(idToRemove);
        assertTrue(removed);
    }

}
