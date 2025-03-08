package models;

import personnelManagement.*;

public class Personnel {
    private final int uniqueID;
    private String fullName;
    private String address;
    private String telephoneNumber;

    public Personnel(int uniqueID, String fullName, String address, String telephoneNumber) {
        this.uniqueID = uniqueID_generator.generateUniqueID();
        this.fullName = fullName;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
    }

    public String getUniqueID() {
        if (this instanceof Physiotherapist) {
            return "physio" + uniqueID;
        }
        else if (this instanceof Patient) {
            return "pat" + uniqueID;
        }
        return String.valueOf(uniqueID);
    }

    public String getFullName() {
        return fullName;
    }


    @Override
    public String toString()
    {
        return
                "ID: " +this.uniqueID+ "\n " +
                "Full Name: " +this.fullName + "\n " +
                "Address : " +this.address + "\n " +
                "Telephone Number : " +this.telephoneNumber;
    }
}
