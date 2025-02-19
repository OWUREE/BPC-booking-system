import java.util.List;

public class Physiotherapist {

    private int uniqueID;
    private String fullName;
    private String address;
    private String telephoneNumber;
    private List<String> expertise; // Areas of expertise
    private String workingTimetable;

    public Physiotherapist(String fullName, String address, String phoneNumber, List<String> expertise, String workingTimetable) {
        this.uniqueID = uniqueID_generator.generateUniqueID();
        this.fullName = fullName;
        this.address = address;
        this.telephoneNumber = phoneNumber;
        this.expertise = expertise;
        this.workingTimetable = workingTimetable;
    }

    public int getUniqueID() {
        return uniqueID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return telephoneNumber;
    }

    public List<String> getExpertise() {
        return expertise;
    }

    public String getWorkingTimetable() {
        return workingTimetable;
    }

    public void displayPhysiotherapistInfo() {
        System.out.println("Physiotherapist ID: " + uniqueID);
        System.out.println("Full Name: " + fullName);
        System.out.println("Address: " + address);
        System.out.println("Telephone Number: " + telephoneNumber);
        System.out.println("Area of Expertise: " + String.join(", ", expertise));
        System.out.println("Timetable: " + workingTimetable);
        System.out.println("----------------------");
    }
}
