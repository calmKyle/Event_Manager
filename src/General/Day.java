package General;

public class Day {
    private int day;
    private int month;
    private int year;

    public Day(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Day(String time) {
        String txt[] = time.split("/");
        this.day = Integer.parseInt(txt[0]);
        this.month = Integer.parseInt(txt[1]);
        this.year = Integer.parseInt(txt[2]);
    }

    public String toString() {
        return Integer.toString(day) + "/" + Integer.toString(month) + "/" + Integer.toString(year);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
