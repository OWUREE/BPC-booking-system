package patients;
import models.*;

public class Patient extends Personnel {

//    private List<> bookings;

    // constructor -  to create a patient
    public Patient(String fullName, String address, String telephoneNumber) {
        super(uniqueID_generator.generateUniqueID(), fullName, address, telephoneNumber);
    }


    public void displayPatientInfo() {
        System.out.println("********Patient Details ******* \n ");
        System.out.println(this);
    }



}