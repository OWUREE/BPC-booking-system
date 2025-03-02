import java.util.*;
import personnelManagement.*;
import services.*;

public class bpc_system {

    private PersonnelManager personnel = new PersonnelManager();
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

    public void displayAllPhysioAppointments(PersonnelManager personnel) {
        System.out.println("\n==== Available Physiotherapist Appointments ====\n");
        for (Physiotherapist physio : personnel.getPhysioList()) {
            physio.displayAvailableAppointments(); // Display available appointments
        }
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
            System.out.println("5. Add Physiotherapist");
            System.out.println("6. Remove Patient");
            System.out.println("7. View Appointment history");
            System.out.println("8. Exit");
            System.out.print("Please select an option: ");

            int choice = system.input.nextInt();
            system.input.nextLine(); // Consume newline

            switch (choice) {
                case 2:
                    System.out.println("enter your unique id: ");
                    String id = system.input.nextLine();
//                    system.personnel.displayAppointmentByUniqueID();
                case 3:
                    system.personnel.displayAllPatients();
                    break;
                case 4:
                    system.personnel.addNewPatient();
                    break;
                case 5:
                    system.personnel.removePatient();
                    break;
                case 6:
                    system.displayExpertiseAndTreatments();
                    break;
                case 7:
                    system.displayAllPhysioAppointments(new PersonnelManager());
                    break;
                case 8:
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