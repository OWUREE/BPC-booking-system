package models;

import services.uniqueID_generator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Appointment {
    Scanner input = new Scanner(System.in);

    private Date date;

    private final int ID;
    private String treatment;
    private Physiotherapist physio;
    private String patientID;
    private AppointmentStatus status;

    private static List<Appointment> allAppointments = new ArrayList<>();
    private static List<Appointment> canceledAppointments = new ArrayList<>();// stores all appointments


    public Appointment (String patientID, Physiotherapist physio, Date date, String treatment) {
          this.date = date;
          this.ID = uniqueID_generator.generateUniqueID();
          this.treatment = treatment;
          this.patientID = patientID;
          this.physio = physio;
          this.status = AppointmentStatus.AVAILABLE;

    }

    @Override
    public String toString() {
        return "ID: " + ID +  " | " + treatment + " with " + physio.getFullName() + " on " + date + " ";
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public int getID() { return ID; }
    public Date getDate() { return date; }
    public boolean isBooked() {
//        return patientID != null && !patientID.isEmpty(); // If patientID is set, it's booked
        return this.status == AppointmentStatus.BOOKED;
    }
    public String getTreatment() { return treatment; }
    public Physiotherapist getPhysiotherapist() { return physio; }
    public String getPatientID() { return patientID; }

    public static List<Appointment> getAllAppointment() {
        return allAppointments;
    }

    public static List<Appointment> getCanceledAppointments() {
        return canceledAppointments;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    // Appointment Management

    public static Appointment findAppointmentByID(int id) {
        for (Appointment appointment : Appointment.getAllAppointment()) {
            if (appointment.getID() == id) {
                return appointment;
            }
        }
        return null;
    }

    public void bookAnAppointment(Patient patient) {
        LocalDate appointmentDay = this.getDate().getLocalDate();
        if (appointmentDay.isBefore(LocalDate.now())) {
            System.out.println("You cannot book an appointment in the past!");
        }
        else if (this.isBooked()) {
            System.out.println("\u001B[35mOops, this appointment has already been booked \u001B[0m");
        } else if (patientHasConflict(patient)) {
            System.out.println("\u001B[35mOops, you already have an appointment at this time on this day. Please choose a different time.\u001B[0m");
        } else {
            this.setPatientID(patient.getUniqueID());
            this.status = AppointmentStatus.BOOKED;
            System.out.println("\u001B[35m" + this + "successfully booked for " + patient.getFullName() + "!\u001B[0m");
        }

    }

    public void cancelABooking() {
        if(this.status != AppointmentStatus.BOOKED) {
            System.out.println("\u001B[35mThis appointment is not currently booked \u001B[0m");
        }
        else {

            this.status = AppointmentStatus.CANCELED;
            canceledAppointments.add(this);
            System.out.println("\u001B[35m" + this + "successfully canceled. \u001B[0m");
        }
    }

    public void attendAppointment() {
        if (this.status == AppointmentStatus.CANCELED) {
            System.out.println("\u001B[35mYou cannot attend a canceled appointment.\u001B[0m");
        } else {
            this.status = AppointmentStatus.ATTENDED;
            System.out.println("\u001B[35mAppointment with " + this.physio.getFullName() + " on " + this.date + " has been marked as attended.\u001B[0m");
        }
    }

    public boolean patientHasConflict(Patient patient) {
        List<Appointment> patientAppointments = patient.getPatientBookings();
        for (Appointment appointment : patientAppointments) {

            if (appointment.getDate().equals(this.date)) {
                    return true;
            }
        }
        return false;
    }
    public void updateABooking(Patient patient, Appointment currentAppointment) {

        if (patient == null || currentAppointment == null) {
            System.out.println("Invalid patient ID. Try again.");
            return;
        }

        // Get treatment name from the selected appointment
        String treatmentName = currentAppointment.getTreatment();

        // Fetch all available appointments with the same treatment
        List<Appointment> availableAppointments = Appointment.getAppointmentsByTreatment(treatmentName);

        if (availableAppointments.isEmpty()) {
            System.out.println("No other available appointments for " + treatmentName);
            return;
        }

        System.out.println("\u001B[35mAvailable " + treatmentName + " appointments: \u001B[0m");
        for (int i = 0; i < availableAppointments.size(); i++) {
            Appointment a = availableAppointments.get(i);
            System.out.println((i + 1) + ". " + a);
        }

        System.out.print("\u001B[35mEnter an Appointment ID to book: \u001B[0m");
        int newAppointmentID = input.nextInt();
        input.nextLine();

        Appointment newAppointment = Appointment.findAppointmentByID(newAppointmentID);

        if (newAppointment == null || newAppointment.isBooked()) {
            System.out.println("Invalid selection. Try again.");
            return;
        }



        // Cancel old appointment and book the new one
        currentAppointment.cancelABooking();
        newAppointment.bookAnAppointment(patient);

    }

    public static List<Appointment> getAppointmentByExpertise(String expertiseName) {
        List<Appointment> filteredAppointments = new ArrayList<>();

        for (Appointment appointment : allAppointments){
           Physiotherapist physio = appointment.getPhysiotherapist();
           for (Expertise expertise : physio.getExpertise()) {
               if (expertise.getExpertiseName().equalsIgnoreCase(expertiseName)) {
                   for(String treatment : expertise.getTreatmentList()){
                       if(appointment.getTreatment().equalsIgnoreCase(treatment)){
                           filteredAppointments.add(appointment);
                           break;
                       }
                   }
               }
           }
        }

        return filteredAppointments;
    }

    public static List<Appointment> getAppointmentByPhysioName(String physioName) {
        List<Appointment> filteredAppointments = new ArrayList<>();

        for (Appointment appointment : allAppointments) {
            if (appointment.getPhysiotherapist().getFullName().equalsIgnoreCase(physioName)) {
                filteredAppointments.add(appointment);
            }
        }
        return filteredAppointments;
    }

    public static List<Appointment> getAppointmentsByTreatment(String treatmentName) {
        List<Appointment> filteredAppointments = new ArrayList<>();

        for (Appointment appointment : allAppointments) {
            if (appointment.getTreatment().equalsIgnoreCase(treatmentName) && !appointment.isBooked()) {
                filteredAppointments.add(appointment);
            }
        }

        return filteredAppointments;
    }

}
