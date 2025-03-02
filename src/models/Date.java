package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Date
{
    private int day, month, year;
    private String startTime, endTime;

    public Date(int year, int month, int day, String startTime, String endTime) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String formatDate() {
        LocalDate localDate = LocalDate.of(year, month, day);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE d' 'MMMM yyyy, HH:mm");
        String formattedDate = String.format(formatter.format(localDate), getDaySuffix(day));
        return formattedDate + ", " + startTime + "-" + endTime;
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

    public int getDay() { return day; }
    public int getMonth() { return month; }
    public int getYear() { return year; }
    public String getStartTime() { return startTime; }
    public String getEndTime() { return endTime; }
}

