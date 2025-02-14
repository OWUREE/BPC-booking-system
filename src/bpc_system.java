import java.util.ArrayList;

public class bpc_system {

    public static void main(String[] args) {

        // hardcoding patient data here
        ArrayList<Patient> patientList = new ArrayList<>();

// Hardcoded patients
        patientList.add(new Patient(1, "Alice Johnson", "123 Main St", "555-1234"));
        patientList.add(new Patient(2, "Bob Smith", "456 Oak St", "555-5678"));
        patientList.add(new Patient(3, "Charlie Brown", "789 Pine St", "555-9101"));

// Display all patients
        System.out.println("The Patient List is:");
        int counter = 1;
        for (Patient patients : patientList) {
            System.out.print(counter + ". " + "\n");
            patients.displayPatientInfo();
            counter++;
        }
    }
}
