import java.util.*;
import patients.*;

public class bpc_system {

    private PatientManager patientManager = new PatientManager();
    Scanner input = new Scanner(System.in);

    // Hard-coded expertise data
    private Expertise physiotherapy = new Expertise("Physiotherapy", new String[]{"Massage", "Electrotherapy", "Acupuncture", "Inversion therapy", "Mobilisation", "Manipulation"});
    private Expertise osteopathy = new Expertise("Osteopathy", new String[]{"Spinal Manipulation", "Soft Tissue Therapy", "Massage", "Acupuncture", "Stretching"});
    private Expertise rehabilitation = new Expertise("Rehabilitation", new String[]{"Post-Surgery Recovery", "Sports Injury Rehab"});

    public void displayExpertiseAndTreatments() {
        System.out.println("\nAvailable Expertise and Treatments:");
        System.out.println("- " + physiotherapy.getExpertiseName() + ": " + physiotherapy.displayTreatments());
        System.out.println("- " + osteopathy.getExpertiseName() + ": " + osteopathy.displayTreatments());
        System.out.println("- " + rehabilitation.getExpertiseName() + ": " + rehabilitation.displayTreatments());
    }

    public static void main(String[] args) {
        bpc_system system = new bpc_system();

        while (true) {
            System.out.println("\u001B[35mWELCOME TO THE BPC APPOINTMENT BOOKING SYSTEM \u001B[0m");
            System.out.println("\nMENU: ");
            System.out.println("1. Book an Appointment");
            System.out.println("2. View Appointments");
            System.out.println("3. Display Patients");
            System.out.println("4. Add Patient");
            System.out.println("5. Remove Patient");
            System.out.println("6. View treatment history");
            System.out.println("7. Exit");
            System.out.print("Please select an option: ");

            int choice = system.input.nextInt();
            system.input.nextLine(); // Consume newline

            switch (choice) {
                case 3:
                    system.patientManager.displayAllPatients();
                    break;
                case 4:
                    system.patientManager.addNewPatient();
                    break;
                case 5:
                    system.patientManager.removePatient();
                    break;
                case 6:
                    system.displayExpertiseAndTreatments();
                    break;
                case 7:
                    system.exitProgram();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public void exitProgram() {
        input.close();
        System.out.println("Exiting program... Goodbye!");
        System.exit(0);
    }
}