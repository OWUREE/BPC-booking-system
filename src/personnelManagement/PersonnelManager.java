package personnelManagement;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

import models.Date;
import services.*;
import models.*;

public class PersonnelManager {

//    private static PersonnelManager instance;

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

        Physiotherapist physio2 = (new Physiotherapist("Dr. Mark Gold", "321 Mosquito St", "557-272-4333",
                new Expertise[] { new Expertise("Osteopathy", new String[]{"Spinal Manipulation", "Massage", "Acupuncture", "Stretching"}), new Expertise("Physiotherapy", new String[]{"Inversion therapy", "Mobilisation", "Manipulation"})}));

        Physiotherapist physio3 = (new Physiotherapist("Dr. Hui Cheng", "152 Harrow St", "174-252-3373",
                new Expertise[] { new Expertise("Osteopathy", new String[]{"Spinal Manipulation", "Soft Tissue Therapy", "Stretching"}), new Expertise("Rehabilitation", new String[]{"Post-Surgery Recovery"})}));

        Physiotherapist physio4 = (new Physiotherapist("Dr. Steve Brown", "321 Albatrose St", "111-222-3333",
                new Expertise[] { new Expertise("Physiotherapy", new String[]{"Massage", "Acupuncture"})}));

        Physiotherapist physio5 = (new Physiotherapist("Dr. Martin King", "27 Welwyn St", "567-222-8876",
                new Expertise[] { new Expertise("Rehabilitation", new String[]{"Sports Injury Rehab", "Post-Surgery Recovery"})}));

        physioList.add(physio1);
        physioList.add(physio2);
        physioList.add(physio3);
        physioList.add(physio4);
        physioList.add(physio5);

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

    public List<Appointment> createAppointment() {
        List<Appointment> allAppointments = new ArrayList<>();

        String[] weekdays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        String[] timeSlots = {"09:00-10:00", "10:30-11:30", "11:30-12:30", "14:00-15:00", "15:00-16:00", "16:00-17:00"};

        List<Physiotherapist> physiotherapists = getPhysioList();

        int year = 2025, month = 10;
        LocalDate firstDayOfMonday = LocalDate.of(year, month, 1);

        int firstMondayOffset = (DayOfWeek.MONDAY.getValue() - firstDayOfMonday.getDayOfWeek().getValue() + 7) % 7;
        LocalDate firstMonday = firstDayOfMonday.plusDays(firstMondayOffset);

        for (int week = 0; week < 4; week++) {
            // Rotate the physiotherapists for variety (Optional)
            Collections.rotate(physiotherapists, 3);


        for (Physiotherapist physio : physiotherapists) {
            physio.generateTimeTable();  // <-- This ensures every physio has a generated timetable
        }

        for (String day : weekdays) {
            int dayOffset = Arrays.asList(weekdays).indexOf(day);
            LocalDate actualDate = firstMonday.plusWeeks(week).plusDays(dayOffset);

            Collections.shuffle(physiotherapists);

            // Ensure that at least 3 physiotherapist work everyday
            List<Physiotherapist> workingPhysio = physiotherapists.subList(0, 3);
            int physioIndex = 0;

            for (String timeSlot : timeSlots) {
                String[] times = timeSlot.split("-");

                // Assign a physiotherapist in a round-robin fashion
                Physiotherapist physio = workingPhysio.get(physioIndex % 3);
                physioIndex++;

                String treatment = physio.getRandomTreatment(new Random());
                Date appointmentDate = new Date(year, month, actualDate.getDayOfMonth(), times[0], times[1]);

                // Create appointment and add it to physiotherapist timetable
                Appointment appointment = new Appointment(0, physio, appointmentDate, treatment);
                physio.addAppointmentToTimetable(appointmentDate, treatment);
                allAppointments.add(appointment);
                }
            }
        }
        return allAppointments;

    }

    public List<Physiotherapist> getPhysioList() {
        return physioList;
    }
}
