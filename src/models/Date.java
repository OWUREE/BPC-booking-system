package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Date
{
    private LocalDate date;
    private String startTime, endTime;

    public Date(int year, int month, int day, String startTime, String endTime) {
        this.date = LocalDate.of(year, month, day);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE d MMMM yyyy");

        int day = date.getDayOfMonth();
        return date.format(DateTimeFormatter.ofPattern("EEEE")) +  " "
                + day + getDaySuffix(day) + " " + date.format(DateTimeFormatter.ofPattern("MMMM yyyy")) +
                ", " + startTime + "-" + endTime;
    }

    public int getDayOfTheMonth () {
        return date.getDayOfMonth();
    }

    // Get suffix for the day (1st, 2nd, 3rd, etc.)
    private String getDaySuffix(int day) {
        if (day >= 11 && day <= 13) {
            return "th";
        }
        return switch (day % 10) {
            case 1 -> "st";
            case 2 -> "nd";
            case 3 -> "rd";
            default -> "th";
        };
    }

    public String getStartTime() { return startTime; }
    public String getEndTime() { return endTime; }
}

