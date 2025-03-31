package models;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.time.LocalDate;

import services.*;

public class Physiotherapist extends Personnel {

    private Expertise[] expertise; // Array of expertise
    private ArrayList<Appointment> workingTimetable;

    public Physiotherapist(String fullName, String address, String telephoneNumber, Expertise[] expertise) {
        super(uniqueID_generator.generateUniqueID(), fullName, address, telephoneNumber);
        this.expertise = expertise;
        this.workingTimetable = new ArrayList<>();
        generateTimeTable();
    }


    public Expertise[] getExpertise() {

        return expertise;
    }

    public ArrayList<Appointment> getWorkingTimetable() {
        if (workingTimetable.isEmpty()) {
            generateTimeTable();
        }
        return workingTimetable;
    }

    // to display a patient's info
    public void displayPhysiotherapistInfo() {
        System.out.println("********Physiotherapist Details ******* \n ");
        System.out.println(this);
        System.out.println(Arrays.toString(expertise));
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

    public void generateTimeTable() {
        if (!workingTimetable.isEmpty()) {
            return; // Don't regenerate if already exists
        }
        String[] weekdays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        String[] timeSlots = {"09:00-10:00", "10:30-11:30", "11:30-12:30", "14:00-15:00", "15:00-16:00", "16:00-17:00"};

        Random random = new Random();
        int year = 2025, month = 10;
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);

        // Find first Monday of the month. To ensure that working days don't fall on weekends
        int firstMondayOffset = (DayOfWeek.MONDAY.getValue() - firstDayOfMonth.getDayOfWeek().getValue() + 7) % 7;
        LocalDate firstMonday = firstDayOfMonth.plusDays(firstMondayOffset);

        for (int week = 0; week < 4; week++) {

            for (String day : weekdays) {

                int dayOffset = Arrays.asList(weekdays).indexOf(day);
                LocalDate actualDate = firstMonday.plusWeeks(week).plusDays(dayOffset);

                for (String timeSlot : timeSlots) {
                        String[] times = timeSlot.split("-");

                    if (workingTimetable.isEmpty()) {
                        // Generate and add appointments only if no appointments exist yet
                        String treatment = getRandomTreatment(random);

                        Date appointmentDate = new Date(year, month, actualDate.getDayOfMonth(), times[0], times[1]);

                        addAppointmentToTimetable(appointmentDate, treatment); // Add to workingTimetable
                    }

                    }
                }
            }
        }

    public String getRandomTreatment(Random random) {
        ArrayList<String> allTreatments = new ArrayList<>();
        for (Expertise exp : expertise) {
            allTreatments.addAll(Arrays.asList(exp.getTreatmentList()));
        }
        return allTreatments.get(random.nextInt(allTreatments.size()));
    }


    public void addAppointmentToTimetable(Date date, String treatment) {
        Appointment newAppointment = new Appointment("", this, date, treatment); // 0 = no patient yet
        workingTimetable.add(newAppointment);
    }

    public void displayAvailableAppointmentsByID() {
        if (workingTimetable.isEmpty()) {
            System.out.println(getFullName() + " has no available appointments.");
            return;
        }
        System.out.println("Available appointments for " + getFullName() + ":");
        for (Appointment appointment : workingTimetable) {
            System.out.println(appointment);
        }
    }

}

