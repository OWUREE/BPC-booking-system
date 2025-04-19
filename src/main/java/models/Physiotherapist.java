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
    }


    public Expertise[] getExpertise() {

        return expertise;
    }

    public ArrayList<Appointment> getWorkingTimetable() {
        return workingTimetable;
    }

    // to display a Physiotherapist's info
    public void displayPhysiotherapistInfo() {
        System.out.println("********Physiotherapist Details ******* \n ");
        System.out.println(this);
        System.out.println(Arrays.toString(expertise));
    }

    public void generateTimeTable(LocalDate startDate, int numberOfDays) {
        if (!workingTimetable.isEmpty()) {
            return; // Avoid regenerating if timetable already exists
        }

        String[] timeSlots = {
                "09:00-10:00", "10:30-11:30", "11:30-12:30",
                "14:00-15:00", "15:00-16:00", "16:00-17:00"
        };

        Random random = new Random();
        LocalDate endDate = startDate.plusDays(30);  // One month ahead

        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            if (date.getDayOfWeek().getValue() >= 1 && date.getDayOfWeek().getValue() <= 5) {
                for (String timeSlot : timeSlots) {
                    String[] times = timeSlot.split("-");

                    String treatment = getRandomTreatment(random);
                    Date appointmentDate = new Date(
                            date.getYear(),
                            date.getMonthValue(),
                            date.getDayOfMonth(),
                            times[0],
                            times[1]
                    );

                    addAppointmentToTimetable(appointmentDate, treatment);

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

}

