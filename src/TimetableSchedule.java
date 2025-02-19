

public class TimetableSchedule {

    private int timeOfTheDay;
    private String dayOfTheWeek;

    public TimetableSchedule(int timeOfTheDay, String dayOfTheWeek) {
        this.timeOfTheDay = timeOfTheDay;
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public int getTimeOfTheDay() {
        return timeOfTheDay;
    }

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }
}


