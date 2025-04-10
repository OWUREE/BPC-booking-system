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
        System.out.println("********Patient Details ******* \n ");
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