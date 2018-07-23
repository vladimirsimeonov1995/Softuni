package weekdays;

public class Main {

    public static void main(String[] args) {

        WeeklyCalendar wc = new WeeklyCalendar();

        wc.addEntry("Friday", "sleep");
        wc.addEntry("Monday", "sport");

        for (WeeklyEntry weeklyEntry : wc.getWeeklySchedule()) {
            System.out.println(weeklyEntry);
        }

    }

}
