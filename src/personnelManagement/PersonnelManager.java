package personnelManagement;

import java.util.*;

import models.Date;
import services.*;
import models.*;

public class PersonnelManager {
//    Date date = new Date(3, 10, 25, 11, 30);

    private List<Patient> patientList = new ArrayList<>();
    private List<Physiotherapist> physioList = new ArrayList<>();
    Expertise[] expertise;

    Scanner input = new Scanner(System.in);

    public PersonnelManager() {
        // Hard-coded patient data
        patientList.add(new Patient("Alice Johnson", "123 Main St", "555-1234"));
        patientList.add(new Patient("Bob Smith", "456 Oak St", "555-5678"));
        patientList.add(new Patient("Charlie Brown", "789 Pine St", "555-9101"));


        // Hard-coded physiotherapist data
        Physiotherapist physio1 = (new Physiotherapist("Dr. Isaac Lee", "789 Maple St", "777-888-9999",
                new Expertise[] { new Expertise("Physiotherapy", new String[]{"Massage", "Electrotherapy", "Acupuncture"}), new Expertise("Rehabilitation", new String[]{"Post-Surgery Recovery", "Sports Injury Rehab"})}));

        Physiotherapist physio2 = (new Physiotherapist("Dr. Mark Gold", "321 Mosquito St", "111-222-3333",
                new Expertise[] { new Expertise("Osteopathy", new String[]{"Spinal Manipulation", "Massage", "Acupuncture", "Stretching"}), new Expertise("Physiotherapy", new String[]{"Inversion therapy", "Mobilisation", "Manipulation"})}));

        physioList.add(new Physiotherapist("Dr. Hui Cheng", "152 Harrow St", "174-252-3373",
                new Expertise[] { new Expertise("Osteopathy", new String[]{"Spinal Manipulation", "Soft Tissue Therapy", "Stretching"}), new Expertise("Rehabilitation", new String[]{"Post-Surgery Recovery"})}));

        physioList.add(new Physiotherapist("Dr. Steve Brown", "321 Albatrose St", "111-222-3333",
                new Expertise[] { new Expertise("Physiotherapy", new String[]{"Massage", "Acupuncture"}) }));

        Date appointmentDate = new Date(2025, 5, 1, "10:00", "11:00");
        physio1.addAppointmentToTimetable(appointmentDate, "Massage");
        physio2.addAppointmentToTimetable(appointmentDate, "Stretching");

        physioList.add(physio1);
        physioList.add(physio2);

        physio1.displayAvailableAppointments();
        physio2.displayAvailableAppointments();
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
//        System.out.println(date.getDate());
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

    public void displayAPatientUsingID() {
        System.out.println("Enter the Unique ID of the patient:");
        String idToShow = input.next();
        Patient patientToShow = patientList.stream().filter(patient -> patient.getUniqueID().equals(idToShow)).findFirst().orElse(null);
        if (patientToShow == null) {
            System.out.println("No patient found with that ID.");
        } else {
            patientToShow.displayPatientInfo();
        }
    }

    public void removePatient() {
        System.out.println("Enter the Unique ID of the patient to remove:");
        String idToRemove = input.next();

        boolean removed = patientList.removeIf(patient -> patient.getUniqueID().equals(idToRemove));
        if (removed) {
            System.out.println("Patient with ID" + idToRemove + "removed successfully.");
        } else {
            System.out.println("No patient found with that ID.");
        }
    }

    public void addNewPhysiotherapist() {
        System.out.println("Enter physiotherapist's full name: ");
        String fullName = input.nextLine();
        System.out.println("Enter physiotherapist's address: ");
        String address = input.nextLine();
        System.out.println("Enter physiotherapist's telephone number: ");
        String telephoneNumber = input.nextLine();
      
        Expertise[] expertise = new Expertise[2];
        for (int i = 0; i < expertise.length; i++) {
            System.out.println("Enter expertise " + (i + 1) + ": ");
            String expertiseName = input.nextLine();
            System.out.println("Enter the list of treatments for " + expertiseName + ": ");
            String[] treatments = input.nextLine().split(",");
            expertise[i] = new Expertise(expertiseName, treatments);
        }
        Physiotherapist newPhysiotherapist = new Physiotherapist(fullName, address, telephoneNumber, expertise);
        physioList.add(newPhysiotherapist);
        System.out.println("Physiotherapist added successfully. ID: " + newPhysiotherapist.getUniqueID());
        
    }

//    public void addAppointmentToPhysio(int physioID, Appointment appointment) {
//        for (Physiotherapist physio : physioList) {
//            if (physio.getUniqueID().equals(physioID)) {
//                physio.addAppointmentToTimetable(appointment);
//                System.out.println("Appointment added successfully for " + physio.getFullName());
//                return;
//            }
//        }
//        System.out.println("Physiotherapist with ID " + physioID + " not found.");
//    }

    public void displayAllPhysiotherapists() {
        if (physioList.isEmpty()) {
            System.out.println("No physiotherapist found.");
            return;
        }

        System.out.println("\"********Physiotherapist List *******\n");
        int physioCounter = 1;
        for (Physiotherapist physio : physioList) {
            System.out.println(physioCounter);
            System.out.println(physio);
            physioCounter++;
        }
    }

    public void displayPhysioTreatmentsByFullName() {
        System.out.println("Enter Physiotherapist's full name:");
        String name = input.nextLine();
        Physiotherapist physioToShow = physioList.stream().filter(physiotherapist -> physiotherapist.getFullName().equalsIgnoreCase(name)).findFirst().orElse(null);
        if (physioToShow == null) {
            System.out.println("No physiotherapist found with that name.");
        }
        else {
            // get and display treatment list
            expertise = physioToShow.getExpertise();
            for (Expertise expertise : expertise) {
                System.out.println("Expertise: " + expertise.getExpertiseName());
                System.out.println("Treatments: " + String.join(", ", expertise.getTreatmentList()));
            }
        }
    }

    public void removePhysiotherapist() {
        System.out.println("Enter the Unique ID of the physiotherapist to remove:");
        String idToRemove = input.next();
        boolean removed = physioList.removeIf(physiotherapist -> physiotherapist.getUniqueID().equals(idToRemove));
        if (removed) {
            System.out.println("Physiotherapist with ID" + idToRemove + "removed successfully.");
        } else {
            System.out.println("No physiotherapist found with that ID.");
        }
    }

    public List<Physiotherapist> getPhysioList() {
        return physioList;
    }
}
