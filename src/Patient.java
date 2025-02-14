public class Patient {

    private final int uniqueID;
    private String fullName;
    private String address;
    private String telephoneNumber;

    // constructor -  to create a patient
    public Patient(int uniqueID, String fullName, String address, String telephoneNumber) {
        this.uniqueID = uniqueID;
        this.fullName = fullName;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
    }

    // Getters
    public int getUniqueID() {
        return uniqueID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    // method to display patients
    public void displayPatientInfo() {
        System.out.println("Patient's ID: " + uniqueID);
        System.out.println("Full Name: " + fullName);
        System.out.println("Address: " + address);
        System.out.println("Telephone Number: " + telephoneNumber);
        System.out.println("----------------------");
    }
}
