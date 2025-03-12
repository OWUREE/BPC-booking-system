import java.util.*;
import personnelManagement.*;
import services.*;

public class bpc_system {

    PersonnelManager personnel = new PersonnelManager();
    Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        bpc_system system = new bpc_system();
        system.personnel.createAppointment(); // the createAppointment method needs to be called for appointments to be created

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
                    system.input.nextLine();

               switch (patientChoice) {
                   case 1:
                       system.personnel.addNewPatient();
                       break;
                   case 2: {
                       System.out.println("1. Book a treatment appointment");
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

                                       // Display available expertise
                                       List<Appointment> availableAppointments = handleExpertiseAppointments(system);

                                       // After expertise is selected, handle available appointments
                                       if (!availableAppointments.isEmpty()) {
                                           handleBooking(system, availableAppointments);
                                       } else {
                                           System.out.println("No available appointments for the selected expertise.");

                                       }
                                       break;
                                   }
                                   case 2: {
                                       List<Appointment> availableAppointments = handlePhysioAppointments(system);

                                       if (!availableAppointments.isEmpty()) {
                                           handleBooking(system, availableAppointments);
                                       } else {
                                           System.out.println("No available appointments for the selected physiotherapist.");
                                       }
                                       break;
                                   }
                                   case 3: {
                                       break;
                                   }
                                   default:
                                       System.out.println("Invalid choice. Please try again.");
                                       continue;
                               }
                               break;
                           }
                           case 2: {
                               System.out.println("1. View all appointments");
                               System.out.println("2. update an appointment");
                               System.out.println("3. cancel an appointment");
                               System.out.println("4. back");
                               System.out.print("Please select an option: ");

                               int manageAppointmentChoice = system.input.nextInt();
                               system.input.nextLine();

                               switch (manageAppointmentChoice) {
                                   case 1:
                                       promptPatientIDForBookings(system);
                                       break;
                                   case 2: {
                                       Appointment selectedAppointment = handleFindAndSelectAppointment(system, "update");
                                       if (selectedAppointment != null) {
                                           Patient patient = system.personnel.findPatientByID(selectedAppointment.getPatientID());
                                           selectedAppointment.updateABooking(patient, selectedAppointment);
                                       }
                                       break;
                                   }
                                   case 3: {
                                       Appointment selectedAppointment = handleFindAndSelectAppointment(system, "cancel");
                                       if (selectedAppointment != null) {
                                           selectedAppointment.cancelABooking();
                                       }
                                       break;
                                   }
                                   default:
                                       System.out.println("Invalid choice. Please try again.");
                                       continue;
                               }
                           }
                           case 3:
                               break;
                           default:
                               System.out.println("Invalid choice. Please try again.");
                               continue;
                       }
                       break;
                   }

               }
               break;
                }
                case 2: {
                    System.out.println("1. View Timetable");
                    System.out.println("2. View Appointments");
                    System.out.println("3. Back");
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

    private static void handleBooking(bpc_system system, List<Appointment> availableAppointments) {
        for (int i = 0; i < availableAppointments.size(); i++) {
            System.out.println((i + 1) + ". " + availableAppointments.get(i));
        }

        System.out.print("\nSelect an appointment to book (enter the number): ");
        int appointmentChoice = system.input.nextInt();
        system.input.nextLine();

        if (appointmentChoice > 0 && appointmentChoice <= availableAppointments.size()) {
            Appointment selectedAppointment = availableAppointments.get(appointmentChoice - 1);
            System.out.println("You selected: " + selectedAppointment);

            // Prompt for unique patient ID
            System.out.print("Enter your patient ID: ");
            String patientID = system.input.nextLine();

            Patient patient = system.personnel.findPatientByID(patientID);

            if (patient != null) {
                // Book the appointment for the patient
                selectedAppointment.bookAnAppointment(patient);
            } else {
                System.out.println("Invalid patient ID. Could not book the appointment.");
            }
        } else {
            System.out.println("Invalid appointment selection. Please try again.");
        }
    }

    private static void promptPatientIDForBookings(bpc_system system) {
        System.out.println("Please enter your patient ID:");

        String patientID = system.input.nextLine();

        Patient patient = system.personnel.findPatientByID(patientID);

        handlePatientAppointment(patientID, patient);
    }

    private static List<Appointment> handleExpertiseAppointments(bpc_system system ) {
        List<Expertise> expertiseList = system.personnel.getAllExpertise();
        for (int i = 0; i < expertiseList.size(); i++) {
            System.out.println((i + 1) + ". " + expertiseList.get(i).getExpertiseName());
        }
        System.out.print("Select expertise to book an appointment: ");
        int expertiseChoice = system.input.nextInt();
        system.input.nextLine();

        if (expertiseChoice > 0 && expertiseChoice <= expertiseList.size()) {
            String selectedExpertise = expertiseList.get(expertiseChoice - 1).getExpertiseName();
           List<Appointment> availableAppointments = Appointment.getAppointmentByExpertise(selectedExpertise);
            System.out.println(selectedExpertise + " appointments Found -> " + availableAppointments.size());
            return availableAppointments;
        } else {
            System.out.println("Invalid expertise choice.");
            return new ArrayList<>();
        }
    }

    private static List<Appointment> handlePhysioAppointments(bpc_system system) {
        List<Physiotherapist> physioList = system.personnel.getPhysioList();
        for (int i = 0; i < physioList.size(); i++) {
            System.out.println((i + 1) + ". " + physioList.get(i).getFullName());
        }
        System.out.println("Select a physiotherapist to book an appointment:");
        int physioNameChoice = system.input.nextInt();
        system.input.nextLine();

        if (physioNameChoice > 0 && physioNameChoice <= physioList.size()) {
            String selectedPhysio = physioList.get(physioNameChoice - 1).getFullName();
            List<Appointment> availableAppointments = Appointment.getAppointmentByPhysioName(selectedPhysio);
            System.out.println( selectedPhysio + " appointments Found -> " + availableAppointments.size());
            return availableAppointments;
        }
        else {
            System.out.println("Invalid physiotherapist choice. Try again.");
            return new ArrayList<>();
        }
    }

    private static void handlePatientAppointment(String patientID, Patient patient) {
        if (patient != null) {
            // return patient's bookings
            List<Appointment> bookings = patient.getPatientBookings();
            if (bookings.isEmpty()) {
                System.out.println("No appointments found for patient ID: " + patientID);
            } else {
                System.out.println("Appointments for patient ID " + patientID + ":");
                    for (int i = 0; i<bookings.size(); i++) {
                        System.out.println((i + 1) + ". " + bookings.get(i));
                    }
            }
        }else {
            System.out.println("Invalid patient ID. Could not book the appointment.");
        }
    }

//    public void handleUpdateAppointment(bpc_system system, List<Appointment> availableAppointments) {
//        List<Expertise> expertiseList = system.personnel.getAllExpertise();
//        System.out.println("Available treatments");
//        for (int i = 0; i < expertiseList.size(); i++) {
//            System.out.println((i + 1) + ". " + expertiseList.get(i).getExpertiseName());
//        }
//        System.out.print("Select a new : ");
//        int expertiseChoice = system.input.nextInt();
//        system.input.nextLine();
//
//        if (expertiseChoice > 0 && expertiseChoice <= expertiseList.size()) {
//            String selectedExpertise = expertiseList.get(expertiseChoice - 1).getExpertiseName();
//            List<Appointment> availableAppointments = Appointment.getAppointmentByExpertise(selectedExpertise);
//            System.out.println(selectedExpertise + " appointments Found -> " + availableAppointments.size());
//            return availableAppointments;
//        } else {
//            System.out.println("Invalid expertise choice.");
//            return new ArrayList<>();
//        }
//    }

    private static Appointment handleFindAndSelectAppointment(bpc_system system, String action) { // action is either to update or to cancel
        System.out.println("Please enter your patient ID:");
        String patientID = system.input.nextLine();
        Patient patient = system.personnel.findPatientByID(patientID);

        if (patient == null) {
            System.out.println("Invalid patient ID. Try again.");
            return null;
        }

        List<Appointment> bookings = patient.getPatientBookings();

        if (bookings.isEmpty()) {
            System.out.println("No appointments found for patient ID: " + patientID);
            return null;
        }

        System.out.println("Appointments for patient ID " + patientID + ":");
        for (int i = 0; i < bookings.size(); i++) {
            System.out.println((i + 1) + ". " + bookings.get(i));
        }

        System.out.print("Select an appointment to " + action + " (enter number): ");
        int appointmentChoice = system.input.nextInt();
        system.input.nextLine();

        if (appointmentChoice <= 0 || appointmentChoice > bookings.size()) {
            System.out.println("Invalid selection. Try again.");
            return null;
        }
        Appointment selectedAppointment = bookings.get(appointmentChoice - 1);
        System.out.println("You selected: " + selectedAppointment);
        return selectedAppointment;
    }

    public void exitProgram() {
        input.close();
        System.out.println("Exiting program... Goodbye!");
        System.exit(0);
    }
}