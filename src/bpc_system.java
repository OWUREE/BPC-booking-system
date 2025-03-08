import java.util.*;
import personnelManagement.*;
import services.*;

public class bpc_system {

    PersonnelManager personnel = new PersonnelManager();
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
            System.out.println("1. Patient");
            System.out.println("2. Physiotherapist");
            System.out.println("3. Admin");
            System.out.println("4. Exit");
            System.out.print("Please select an option: ");

            int choice = system.input.nextInt();
            system.input.nextLine(); // Consume newline

            switch (choice) {
                case 1: {
                    System.out.println("1. New Patient");
                    System.out.println("2. Existing Patient");
                    System.out.println("3. Back");
                    System.out.print("Please select an option: ");

                    int patientChoice = system.input.nextInt();
                    system.input.nextLine(); // Consume newline

               switch (patientChoice) {
                   case 1:
                       system.personnel.addNewPatient();
                       break;
                   case 2: {
                       System.out.println("1. Book an appointment");
                       System.out.println("2. Manage appointments");
                       System.out.println("3. Back");
                       System.out.print("Please select an option: ");

                       int existingPatientChoice = system.input.nextInt();
                       system.input.nextLine();

                       switch (existingPatientChoice) {
                           case 1:{
                               System.out.println("1. Book by expertise");
                               System.out.println("2. Book by Physiotherapist name");
                               System.out.println("3. Back");
                               System.out.print("Please select an option: ");

                               int bookAppointmentChoice = system.input.nextInt();
                               system.input.nextLine();

                               switch (bookAppointmentChoice) {
                                   case 1: {
                                       List<Expertise> expertiseList = system.personnel.getAllExpertise();
                                       for (int i = 0; i < expertiseList.size(); i++) {
                                           System.out.println((i + 1) + ". " + expertiseList.get(i).getExpertiseName());
                                       }
                                       System.out.println("Select expertise to book an appointment:");
                                       int expertiseChoice = system.input.nextInt();
                                       system.input.nextLine();
                                   }
                                   break;
                                   case 2: {
                                       System.out.println("Select a physiotherapist to book an appointment:");
                                       List<Physiotherapist> physioList = system.personnel.getPhysioList();
                                       for (int i = 0; i < physioList.size(); i++) {
                                           System.out.println((i + 1) + ". " + physioList.get(i).getFullName());
                                       }
                                       System.out.print("Please select an option: ");
                                   }
                                   break;
                                   default:
                                       System.out.println("Invalid choice. Please try again.");
                                       continue;
                               }
                           }
                           break;
                           case 2:
                               System.out.println("manage patient appointments");
                               break;
                           default:
                               System.out.println("Invalid choice. Please try again.");
                               continue;
                       }
                       break;
                   }
                   case 3:
                       break;
                   default:
                       System.out.println("Invalid choice. Please try again.");
                       continue; // Restart the loop if input is invalid
               }

                }

                case 2: {
                    System.out.println("1. Add Patient");
                    System.out.println("2. Remove a Patient");
                    System.out.println("3. View Appointments");
                    System.out.println("4. Back");
                    System.out.print("Please select an option: ");

                    int physiotherapistChoice = system.input.nextInt();
                    system.input.nextLine(); // Consume newline

//                    switch (physiotherapistChoice) {
//                        case 1:
                }
                case 3: {
                    System.out.println("1. Manage Personnel");
                    System.out.println("2. Print appointment history");
                    System.out.println("4. Back");
                    System.out.print("Please select an option: ");


                    int adminChoice = system.input.nextInt();
                    system.input.nextLine(); // Consume newline

//                    switch (adminChoice) {
//                        case 1:
                }
                case 4:
                    system.exitProgram();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public void generatePhysioTimetablesAndCreateAppointments() {
        // Generate timetables for each physiotherapist
        for (Physiotherapist physio : personnel.getPhysioList()) {
            physio.generateTimeTable();
        }
       List<Appointment> appointments = personnel.createAppointment(); // Create appointments based on generated timetables

        if (!appointments.isEmpty()) {
            System.out.println("Appointments have been created successfully!");
            for (Appointment appointment : appointments) {
                System.out.println(appointment); // Assuming Appointment has a suitable toString method
            }
        } else {
            System.out.println("No appointments created.");
        }
    }

    public void exitProgram() {
        input.close();
        System.out.println("Exiting program... Goodbye!");
        System.exit(0);
    }
}