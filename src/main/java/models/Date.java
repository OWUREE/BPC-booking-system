package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Date
{
    private LocalDate date;
    private String startTime, endTime;

    public Date(int year, int month, int day, String startTime, String endTime) {
        this.date = LocalDate.of(year, month, day);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getDayOfTheMonth () {
        return date.getDayOfMonth();
    }
    public int getMonth() {
        return date.getMonthValue();
    }
    public int getYear() {
        return date.getYear();
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

    @Override
    public String toString() {

        int day = date.getDayOfMonth();
        return date.format(DateTimeFormatter.ofPattern("EEEE")) +  " "
                + day + getDaySuffix(day) + " " + date.format(DateTimeFormatter.ofPattern("MMMM yyyy")) +
                ", " + startTime + "-" + endTime;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Date date1 = (Date) o;
        return Objects.equals(date, date1.date) && Objects.equals(startTime, date1.startTime) && Objects.equals(endTime, date1.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, startTime, endTime);
    }
}

