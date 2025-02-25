package patients;
import java.util.*;

public class PatientManager {

    private List<Patient> patientList = new ArrayList<>();
    Scanner input = new Scanner(System.in);

    public PatientManager() {
        // Hard-coded patient data
        patientList.add(new Patient("Alice Johnson", "123 Main St", "555-1234"));
        patientList.add(new Patient("Bob Smith", "456 Oak St", "555-5678"));
        patientList.add(new Patient("Charlie Brown", "789 Pine St", "555-9101"));
    }

    public void addNewPatient() {
        System.out.println("Enter patient's full name: ");
        String fullName = input.nextLine();
        System.out.println("Enter patient's address: ");
        String address = input.nextLine();
        System.out.println("Enter patient's telephone number: ");
        String telephoneNumber = input.nextLine();

        Patient newPatient = new Patient(fullName, address, telephoneNumber);
        patientList.add(newPatient);
        System.out.println("Patient added successfully. ID: " + newPatient.getUniqueID());
    }

    public void displayAllPatients() {
        if (patientList.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }

        System.out.println("\"********Patient List *******\n");
        int patientCounter = 1;
        for (Patient patient : patientList) {
            System.out.println(patientCounter);
            System.out.println(patient);
            patientCounter++;
        }
    }

//    public void displayAPatient() {
//        System.out.println("Enter the Unique ID of the patient:");
//        int idToShow = input.nextInt();
//        input.nextLine(); // Consume newline
//        boolean show = patientList.contains()
//    }

    public void removePatient() {
        System.out.println("Enter the Unique ID of the patient to remove:");
        int idToRemove = input.nextInt();
//        input.nextLine(); // Consume newline

        boolean removed = patientList.removeIf(patient -> patient.getUniqueID() == idToRemove);
        if (removed) {
            System.out.println("Patient with ID" + idToRemove + "removed successfully.");
        } else {
            System.out.println("No patient found with that ID.");
        }
    }
}
