package services;

import models.*;
import personnelManagement.*;



public class Appointment {

    private Date date;

    private final int ID;
    private String treatment;
    private Physiotherapist physio;
    private int patientID;

    public Appointment (int patientID, Physiotherapist physio, Date date, String treatment) {
//        this.date = date;
          this.ID = uniqueID_generator.generateUniqueID();
          this.treatment = treatment;
          this.patientID = patientID;
          this.physio = physio;
    }

    @Override
    public String toString() {
       String status = isBooked() ? "(Booked)" : "(Available)";
        return treatment + " with " + physio.getFullName() + " on " + date + " " + status;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public int getID() { return ID; }
    public Date getDate() { return date; }
    public boolean isBooked() {
        return patientID != 0;  // If patientID is set, it's booked
    }
    public String getTreatment() { return treatment; }
    public Physiotherapist getPhysiotherapist() { return physio; }
    public int getPatientID() { return patientID; }

}
