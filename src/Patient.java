public class Patient {

    private final int uniqueID;
    private String fullName;
    private String address;
    private String telephoneNumber;

    // constructor -  to create a patient
    public Patient(String fullName, String address, String telephoneNumber) {
        this.uniqueID = uniqueID_generator.generateUniqueID();
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
    @Override
    public String toString()
    {
        return "********Patient's Details ******* \n ID: " +this.uniqueID+ "\n Fullname: " +this.fullName + "\n Address : " +this.address + "\n Telephone Number : " +this.telephoneNumber;
    }
}
