import java.util.*;

public class bpc_system {

    // patient and physiotherapist data here
    List<Patient> patientList;
    List<Physiotherapist> physioList;
    Scanner input = new Scanner(System.in);

    public bpc_system() {
        patientList = new ArrayList<>();
        physioList = new ArrayList<>();


        // Hardcoded patients
        patientList.add(new Patient("Alice Johnson", "123 Main St", "555-1234"));
        patientList.add(new Patient("Bob Smith", "456 Oak St", "555-5678"));
        patientList.add(new Patient("Charlie Brown", "789 Pine St", "555-9101"));

        // Hardcoded physiotherapist
        physioList.add(new Physiotherapist("Dr. Isaac Lee", "789 Maple St", "777-888-9999",
                Arrays.asList("Physiotherapy", "Rehabilitation"), "Monday - Friday, 9 AM - 5 PM"));

        physioList.add(new Physiotherapist("Dr. Mark Gold", "321 Mosquito St", "111-222-3333",
                Arrays.asList("Osteopathy", "Physiotherapy"), "Tuesday - Saturday, 10 AM - 6 PM"));

        physioList.add(new Physiotherapist("Dr. Hui Cheng", "152 Harrow St", "174-252-3373",
                Arrays.asList("Osteopathy", "Rehabilitation"), "Tuesday - Saturday, 10 AM - 6 PM"));

        physioList.add(new Physiotherapist("Dr. Steve Brown", "321 Albatrose St", "111-222-3333",
                Arrays.asList("Osteopathy", "Physiotherapy", "Rehabilitation"), "Tuesday - Saturday, 10 AM - 6 PM"));
    }

    public static void main(String[] args) {
        bpc_system system = new bpc_system();

        while(true) {
            System.out.println("\u001B[35mWELCOME TO THE BPC BOOKING SYSTEM \u001B[0m");
            System.out.println("\nMENU: ");
            System.out.println("1. Patient");
            System.out.println("2. Physiotherapist");
            System.out.println("3. Display patient");
            System.out.println("4. Add Patient");
            System.out.println("5. Remove Patient");
            System.out.println("6. View treatment history");
            System.out.println("7. Exit");
            System.out.println("please select an option:");

            int choice = system.input.nextInt();
            system.input.nextLine(); // Consume newline

            switch (choice) {
                case 3:
                    system.displayAllPatients();
                    break;
                case 4:
                    system.addNewPatient();
                    break;
                case 5:
                    system.removePatients();
                    break;
                case 7:
                    system.exitProgram();
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public void addNewPatient() {

        System.out.println("Enter patient's fullname: ");
        String full_name = input.nextLine();

        System.out.println("Enter patient's address: ");
        String address = input.nextLine();

        System.out.println("Enter patient's telephone number: ");
        String phoneNumber = input.nextLine();

        Patient newPatient = new Patient(full_name, address, phoneNumber);
        patientList.add(newPatient);

        System.out.println("Patient added successfully.\n ID is: " + newPatient.getUniqueID());
    }

    // Display all patients
    public void displayAllPatients() {
        System.out.println("The Patient List is:");
        int patientCounter = 1;
        for (Patient patients : patientList) {
            System.out.println(patientCounter + ". \n" + patients);
            patientCounter++;
        }
    }

    // Remove patient
    public void removePatients() {
        if (patientList.isEmpty()) {
            System.out.println("No patients to remove.");
            return;
        }

        System.out.print("\nEnter the Unique ID of the patient: ");
        int idToRemove = input.nextInt();
        input.nextLine(); // Consume newline

        // ✅ Find and remove the patient with the given unique ID
        boolean removed = patientList.removeIf(patient -> patient.getUniqueID() == idToRemove);

        if (removed) {
            System.out.println("Patient with ID " + idToRemove + " has been removed.");
        } else {
            System.out.println("No patient found with that ID. Please try again!");
        }
    }

    public void exitProgram() {
        input.close();  // Closes the Scanner to free resources
        System.out.println("Exiting program... Goodbye!");
        System.exit(0); // Terminates the program
    }

    }
