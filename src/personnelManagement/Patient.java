package personnelManagement;
import models.*;
import services.Appointment;

import java.util.List;

public class Patient extends Personnel {

    private List<Appointment> bookings;

    // constructor -  to create a patient
    public Patient(String fullName, String address, String telephoneNumber) {
        super(uniqueID_generator.generateUniqueID(), fullName, address, telephoneNumber);
    }


    // to display a patient's info
    public void displayPatientInfo() {
        System.out.println("********Patient Details ******* \n ");
        System.out.println(this);
//        super.toString();
    }

}