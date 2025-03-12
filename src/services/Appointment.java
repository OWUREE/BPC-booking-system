package services;

import models.*;
import personnelManagement.*;

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

    private static List<Appointment> allAppointments = new ArrayList<>(); // stores all appointments


    public Appointment (String patientID, Physiotherapist physio, Date date, String treatment) {
          this.date = date;
          this.ID = uniqueID_generator.generateUniqueID();
          this.treatment = treatment;
          this.patientID = patientID;
          this.physio = physio;

    }

    @Override
    public String toString() {
       String status = isBooked() ? "(Booked)" : "(Available)";
        return treatment + " with " + physio.getFullName() + " on " + date + " ";
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public int getID() { return ID; }
    public Date getDate() { return date; }
    public boolean isBooked() {
        return patientID != null && !patientID.isEmpty();  // If patientID is set, it's booked
    }
    public String getTreatment() { return treatment; }
    public Physiotherapist getPhysiotherapist() { return physio; }
    public String getPatientID() { return patientID; }

    public static List<Appointment> getAllAppointment() {
        return allAppointments;
    }

    // Appointment Management
    public void bookAnAppointment(Patient patient) {
        if (this.isBooked()) {
            System.out.println("Ooops, this appointment has already been booked");
        }
        else {
            this.setPatientID(patient.getUniqueID());
            System.out.println(this + "successfully booked for " + patient.getFullName() + "!");
        }

    }

    public void cancelABooking() {
        this.patientID = null;
        System.out.println(this + "successfully canceled.");
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

        System.out.println("Available " + treatmentName + " appointments:");
        for (int i = 0; i < availableAppointments.size(); i++) {
            System.out.println((i + 1) + ". " + availableAppointments.get(i));
        }

        System.out.print("Select a new appointment (enter number): ");
        int newAppointmentChoice = input.nextInt();
        input.nextLine();

        if (newAppointmentChoice <= 0 || newAppointmentChoice > availableAppointments.size()) {
            System.out.println("Invalid selection. Try again.");
            return;
        }

        Appointment newAppointment = availableAppointments.get(newAppointmentChoice - 1);

        // Cancel old appointment and book the new one
        currentAppointment.cancelABooking();
        newAppointment.bookAnAppointment(patient);

        System.out.println("Your appointment has been updated to: " + newAppointment);
    }

    public static List<Appointment> getAppointmentByExpertise(String expertiseName) {
        List<Appointment> filteredAppointments = new ArrayList<>();

        // loop through all appointments
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
