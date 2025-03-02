package personnelManagement;

import java.util.ArrayList;
import java.util.HashMap;

import models.*;
import services.*;

public class Physiotherapist extends Personnel {

    private Expertise[] expertise; // Array of expertise
    private ArrayList<Appointment> workingTimetable;

    public Physiotherapist(String fullName, String address, String telephoneNumber, Expertise[] expertise) {
        super(uniqueID_generator.generateUniqueID(), fullName, address, telephoneNumber);
        this.expertise = expertise;
        this.workingTimetable = new ArrayList<>();
    }

    public Expertise[] getExpertise() {

        return expertise;
    }

    public String displayExpertise() {
        StringBuilder expertiseNames = new StringBuilder();
        for (Expertise expertise : expertise) {
            expertiseNames.append(expertise.getExpertiseName()).append(", ");
        }
        return !expertiseNames.isEmpty()
                ? expertiseNames.substring(0, expertiseNames.length() - 2)
                : "No Expertise";

    }

    public void addAppointmentToTimetable(Date date, String treatment) {
        Appointment newAppointment = new Appointment(0,this, date, treatment); // 0 = no patient yet
        workingTimetable.add(newAppointment);
        }

    public void displayAvailableAppointments() {
        if (workingTimetable.isEmpty()) {
            System.out.println(getFullName() + " has no available appointments.");
            return;
        }
        System.out.println("Available appointments for " + getFullName() + ":");
        for (int i = 0; i < workingTimetable.size(); i++) {
            System.out.println((i + 1) + ". " + workingTimetable.get(i));
        }
    }

}

