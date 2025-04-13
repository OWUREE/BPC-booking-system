package services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import models.*;
import models.Date;

public class PersonnelManager {

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

    public void addNewPatient(String fullName, String address, String telephoneNumber) {
        // Assuming that the inputs passed to this method are already validated in the runner class.

        // Create a new patient instance using the validated data
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

    public Patient findPatientByID(String IDInput) {
        for (Patient patient : patientList) {
            if (patient.getUniqueID().equalsIgnoreCase(IDInput)) {
                return patient;
            }
        }
        return  null;
    }

    public void displayAPatientUsingIDorFullName() {
        System.out.println("Enter the Unique ID or Full name of the patient:");
        String inputString = input.nextLine();

        Patient patientToShow = patientList.stream().filter(patient -> patient.getUniqueID().equalsIgnoreCase(inputString) || patient.getFullName().equalsIgnoreCase(inputString)).findFirst().orElse(null);
        if (patientToShow == null) {
            System.out.println("No patient found with that ID or Name.");
        } else {
            patientToShow.displayPatientInfo();
        }
    }

    public boolean removePatientByID(String idToRemove) {
        boolean removed = patientList.removeIf(patient -> patient.getUniqueID().equals(idToRemove));
        if (removed) {
            System.out.println("Patient with ID " + idToRemove + " removed successfully.");
        } else {
            System.out.println("No patient found with that ID.");
        }
        return removed;
    }

    // Physiotherapist methods
    public List<Expertise> getAllExpertise() {
        Set<Expertise> expertiseSet = new HashSet<>();  // A Set to store unique expertise
        for (Physiotherapist physio : physioList) {
            expertiseSet.addAll(Arrays.asList(physio.getExpertise()));  // Add all expertise to the Set
        }

        // Convert the Set back to a List if you need to return a List
        return new ArrayList<>(expertiseSet);
    }

    private Physiotherapist findPhysiotherapistByIDOrName(String inputString) {
        return physioList.stream()
                .filter(physio -> physio.getUniqueID().equalsIgnoreCase(inputString) ||
                        physio.getFullName().equalsIgnoreCase(inputString))
                .findFirst()
                .orElse(null);
    }

    public void displayAllPhysiotherapists() {
        if (physioList.isEmpty()) {
            System.out.println("No physiotherapist found.");
            return;
        }

        System.out.println("********Physiotherapist List *******\n");
        int physioCounter = 1;
        for (Physiotherapist physio : physioList) {
            System.out.println(physioCounter);
            System.out.println(physio);
            System.out.println(" Expertise:");
            for (Expertise e : physio.getExpertise()) {
                System.out.println("     - " + e.getExpertiseName() + ": " + String.join(", ", e.getTreatmentList()));
            }
            physioCounter++;
        }
    }

    public List<Appointment> createAppointment() {

        Appointment.getAllAppointment().clear(); // clear existing appointments to avoid duplicates

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

        for (String day : weekdays) {
            int dayOffset = Arrays.asList(weekdays).indexOf(day);
            LocalDate actualDate = firstMonday.plusWeeks(week).plusDays(dayOffset);

            Collections.shuffle(physiotherapists);

            // Ensure that at least 3 physiotherapist work everyday
            List<Physiotherapist> workingPhysio = physiotherapists.subList(0, 3);
//            int physioIndex = 0;

            for (String timeSlot : timeSlots) {
                String[] times = timeSlot.split("-");

                for (Physiotherapist physio : workingPhysio) {

                    String treatment = physio.getRandomTreatment(new Random());
                   Date appointmentDate = new Date(year, month, actualDate.getDayOfMonth(), times[0], times[1]);

                  // Create appointment and add it to physiotherapist timetable
                  Appointment appointment = new Appointment("", physio, appointmentDate, treatment);

                        Appointment.getAllAppointment().add(appointment);
                    physio.getWorkingTimetable().add(appointment);
                    }
                }
                }
            }
        return new ArrayList<>(Appointment.getAllAppointment());  // return a copy
    }

    public void displayPhysioTimetableByIDorFullName() {
        System.out.println("Enter Physiotherapist's unique ID or full name:");
        String inputString = input.nextLine();
        Physiotherapist physioToShow = findPhysiotherapistByIDOrName(inputString);
        if (physioToShow == null) {
            System.out.println("No physiotherapist found with that name or ID.");
        }
        else {
            List<Appointment> timetable = physioToShow.getWorkingTimetable();

            if (timetable.isEmpty()) {
                System.out.println(physioToShow.getFullName() + " has no available appointments.");
                return;
            }
            System.out.println("Available appointments for " + physioToShow.getFullName() + ":");
            for (Appointment appointment : timetable) {
                System.out.println(appointment.getDate() + " - " + appointment.getTreatment());
            }
        }
    }

    public void displayAPhysioAppointments() {
        System.out.println("Enter Physiotherapist's unique ID or full name:");
        String inputString = input.nextLine();
        Physiotherapist physioToShow = findPhysiotherapistByIDOrName(inputString);
        if (physioToShow == null) {
            System.out.println("No physiotherapist found with that name or ID.");
        }
        else {
            // get and display physiotherapist timetable
            List<Appointment> bookedAppointment = physioToShow.getWorkingTimetable().stream().filter(appointment -> !appointment.getPatientID().isEmpty()).toList();

            if (bookedAppointment.isEmpty()) {
                System.out.println(physioToShow.getFullName() + " has no booked appointments.");
                return;
            }
            System.out.println("********Booked appointments for " + physioToShow.getFullName() + "***********:");
            int appointmentCounter = 1;

            for (Appointment appointment : bookedAppointment) {
                Patient patient =  findPatientByID(appointment.getPatientID());
                String patientName = (patient != null) ? patient.getFullName() : "Unknown Patient";

                System.out.println(appointmentCounter + "." + patientName + " - "  + appointment.getTreatment() + " on " + appointment.getDate());
                appointmentCounter++;
            }
        }
    }

    public void printAppointmentReport() {
        for (Physiotherapist physio : physioList) {
            System.out.println("\nPhysiotherapist: " + physio.getFullName());

            List<Appointment> appointments = physio.getWorkingTimetable();

            for (AppointmentStatus status : AppointmentStatus.values()) {
                if (status == AppointmentStatus.AVAILABLE) {
                    continue; // Skip the AVAILABLE status
                }

                System.out.println("\n" + status + " appointments:");

                // Filter appointments based on the given status (Booked, Cancelled, or Attended)
                List<Appointment> filteredAppointments = appointments.stream()
                        .filter(appointment -> appointment.getStatus() == status)
                        .toList();

                if (!filteredAppointments.isEmpty()) {
                    // Print each appointment details
                    for (Appointment appointment : filteredAppointments) {
                        Patient patient = findPatientByID(appointment.getPatientID()); // Get the patient details
                        String patientName = (patient != null) ? patient.getFullName() : "Unknown Patient";

                        System.out.println("  Treatment: " + appointment.getTreatment());
                        System.out.println("  Patient: " + patientName);
                        System.out.println("  Time: " + appointment.getDate());
                        System.out.println("  Status: " + status);
                        System.out.println();
                    }
                } else {
                    System.out.println("  No " + status + " appointments found.");
                }
            }
        }
    }


    public void printPhysiotherapistsByAttendedAppointments() {
        System.out.println("********* Physiotherapist Ranking by Attended Appointments *********");

        // Create a map to count attended appointments per physiotherapist
        Map<Physiotherapist, Long> attendedCounts = new HashMap<>();

        for (Physiotherapist physio : physioList) {
            long attendedCount = physio.getWorkingTimetable().stream()
                    .filter(appointment -> appointment.getStatus() == AppointmentStatus.ATTENDED)
                    .count();
            attendedCounts.put(physio, attendedCount);
        }

        // Sort physiotherapists by attended count in descending order
        List<Map.Entry<Physiotherapist, Long>> sortedList = new ArrayList<>(attendedCounts.entrySet());
        sortedList.sort((a, b) -> Long.compare(b.getValue(), a.getValue()));

        for (int i = 0; i < sortedList.size(); i++) {
            Physiotherapist physio = sortedList.get(i).getKey();
            long count = sortedList.get(i).getValue();
            System.out.println((i + 1) + ". " + physio.getFullName() + " - " + count);
        }
    }

    public void setPhysioList(List<Physiotherapist> physioList) {
        this.physioList = physioList;
    }

    public List<Physiotherapist> getPhysioList() {
        return physioList;
    }

    public List<Patient> getPatientList() {
        return patientList;
    }

}
