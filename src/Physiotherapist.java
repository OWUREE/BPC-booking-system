import java.util.HashMap;
import java.util.List;
import models.*;

public class Physiotherapist extends Personnel {

    private String[] expertise; // Array of expertise
    private HashMap<String, String> workingTimetable;

    public Physiotherapist(String fullName, String address, String telephoneNumber, String[] expertise) {
        super(uniqueID_generator.generateUniqueID(), fullName, address, telephoneNumber);
        this.expertise = expertise;
        this.workingTimetable = new HashMap<>();
    }

    public String[] getExpertise() {
        return expertise;
    }

    public HashMap<String, String> getWorkingTimetable() {
        return workingTimetable;
    }

    public void displayPhysiotherapistInfo() {

        System.out.println("\"********Physiotherapist Details ******* \\n \":");
        System.out.println(this);

        System.out.println("Area of Expertise: " + String.join(", ", expertise));
        System.out.println("Timetable: " + workingTimetable);
        System.out.println("----------------------");
    }
}
