package models;
import services.uniqueID_generator;

import java.util.ArrayList;
import java.util.List;

public class Patient extends Personnel {


    // constructor -  to create a patient
    public Patient(String fullName, String address, String telephoneNumber) {
        super(uniqueID_generator.generateUniqueID(), fullName, address, telephoneNumber);
    }


    // to display a patient's info
    public void displayPatientInfo() {
        System.out.println("\u001B[35m******** Patient Details ******* \u001B[0m");
        System.out.println(this);
    }

   public List<Appointment> getPatientBookings() {
        List<Appointment> patientAppointments = new ArrayList<>();

        for(Appointment appointment : Appointment.getAllAppointment()) {
            if(appointment.getPatientID() != null && appointment.getPatientID().equals(this.getUniqueID())) {
                patientAppointments.add(appointment);
            }
        }
       return patientAppointments;
  }

}